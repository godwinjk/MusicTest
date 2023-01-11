package com.godwin.testmusic.ui.fragments.detail

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.godwin.testmusic.R
import com.godwin.testmusic.databinding.FragmentDetailsBinding
import com.godwin.testmusic.model.Song
import com.godwin.testmusic.ui.MainActivityViewModel
import com.godwin.testmusic.util.binding_adapters.setImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val mainViewModel by activityViewModels<MainActivityViewModel>()

    private lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.ivFav.setOnClickListener {
            song.isFav = !song.isFav
            toggleFav()
            Toast.makeText(
                requireContext(),
                if (song.isFav) getString(R.string.msg_added_fav) else getString(R.string.msg_removed_fav),
                Toast.LENGTH_SHORT
            ).show()
        }
        mainViewModel.selectedLiveData.observe(viewLifecycleOwner) {
            this.song = it
            binding.ivImage.setImage(it.song.imImage.find { it.attributes.height.toInt() > 100 }?.label)
            binding.sIvIcon.setImage(it.song.imImage.first().label)
            binding.tvTitle.text = it.song.imName.label
            binding.tvContentType.text =
                "${it.song.imContentType.imContentType.attributes.label}- ${it.song.imContentType.attributes.label}"
            binding.tvArtist.text = it.song.imArtist.label
            binding.tvCategory.text = it.song.category.attributes.label
            binding.tvRelease.text = it.song.imReleaseDate.attributes.label
            binding.btnBuy.text = "Buy ${it.song.imPrice.label}"

            val avd = if (song.isFav) {
                ContextCompat.getDrawable(requireContext(), R.drawable.avd_unfavorite)
            } else {
                ContextCompat.getDrawable(requireContext(), R.drawable.avd_favorite)
            }
            binding.ivFav.setImageDrawable(avd)
        }
    }

    private fun toggleFav() {
        val avd = if (song.isFav) {
            ContextCompat.getDrawable(requireContext(), R.drawable.avd_favorite)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.avd_unfavorite)
        }
        binding.ivFav.setImageDrawable(avd)

        if (avd is AnimatedVectorDrawable) {
            avd.start()
        }
    }
}