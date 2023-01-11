package com.godwin.testmusic.ui.fragments.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.godwin.testmusic.R
import com.godwin.testmusic.databinding.FragmentArtistBinding
import com.godwin.testmusic.databinding.FragmentPlaylistBinding
import com.godwin.testmusic.databinding.FragmentSongsBinding
import com.godwin.testmusic.model.CommonError
import com.godwin.testmusic.model.Song
import com.godwin.testmusic.ui.CommonErrorDialog
import com.godwin.testmusic.ui.MainActivityViewModel
import com.godwin.testmusic.ui.OnItemClickListener
import com.godwin.testmusic.ui.adapter.SongAdapter
import com.godwin.testmusic.ui.fragments.songs.SongViewModel
import com.godwin.testmusic.util.extensions.hideSoftInput
import com.godwin.testmusic.util.extensions.setVisibility

class PlaylistFragment : Fragment(), OnItemClickListener<Song> {

    private val mainViewModel by activityViewModels<MainActivityViewModel>()
    private val viewModel by viewModels<PlaylistViewModel>()
    private val adapter = SongAdapter(SongAdapter.ViewType.SONG, this)

    private lateinit var binding: FragmentPlaylistBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adapter = adapter
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        binding.incSearch.tvClose.setOnClickListener {
            viewModel.searchLiveData.postValue("")
            hideSoftInput()
        }
        mainViewModel.commonError.observe(viewLifecycleOwner) {
            if (it != null) {
                when (it.code) {
                    CommonError.NO_INTERNET -> {
                        binding.lottieAnimView.setVisibility(true)
                    }
                    CommonError.FETCHING_ERROR -> {
                        binding.lottieAnimView.setVisibility(true)
                        CommonErrorDialog.showError(
                            requireContext(), "Error", R.raw.tomato_error, it.message
                        )
                    }
                    else -> {
                        binding.lottieAnimView.setVisibility(false)
                    }
                }
            } else {
                binding.lottieAnimView.setVisibility(false)
            }
        }
        mainViewModel.songEntryLiveData.observe(viewLifecycleOwner) {
            viewModel.sortFavourite(it)
        }
        viewModel.favouriteList.observe(viewLifecycleOwner) {
            binding.lottieAnimView.setVisibility(it.isEmpty())
            adapter.setData(it)
        }
        viewModel.searchLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val list =
                    viewModel.filterSearch(it, viewModel.favouriteList.value ?: listOf())
                if (list.isEmpty()) {
                    binding.lottieAnimView.setVisibility(true)
                    binding.lottieAnimView.setAnimation(R.raw.cat_sleep)
                    binding.lottieAnimView.playAnimation()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.msg_no_item_found),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.lottieAnimView.setVisibility(false)
                }
                adapter.setData(list)
            } else {
                viewModel.sortFavourite(mainViewModel.songEntryLiveData.value ?: listOf())
            }
        }
    }

    override fun onClick(t: Song?) {
        t?.let {
            mainViewModel.selectedLiveData.postValue(t)
            findNavController().navigate(R.id.actionFavToDetails)
        }
    }
}