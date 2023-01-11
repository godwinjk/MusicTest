package com.godwin.testmusic.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.godwin.testmusic.databinding.LayoutAlbumItemBinding
import com.godwin.testmusic.databinding.LayoutArtistItemBinding
import com.godwin.testmusic.databinding.LayoutSongItemBinding
import com.godwin.testmusic.model.Song
import com.godwin.testmusic.ui.OnItemClickListener

class SongAdapter(
    private val viewType: ViewType,
    private val onItemClickListener: OnItemClickListener<Song>? = null
) :
    RecyclerView.Adapter<SongAdapter.BaseViewHolder>() {
    private val list = mutableListOf<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val holder = when (this.viewType) {
            ViewType.SONG -> {

                SongViewHolder(
                    LayoutSongItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClickListener
                )
            }
            ViewType.ALBUM -> {
                AlbumViewHolder(
                    LayoutAlbumItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClickListener
                )

            }
            ViewType.ARTIST -> {
                ArtistViewHolder(
                    LayoutArtistItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClickListener
                )

            }
            else -> {
                throw java.lang.IllegalStateException("This viewtype is not define: ${this.viewType})")
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(songs: List<Song>) {
        list.clear()
        list.addAll(songs)
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder(bindInternal: ViewDataBinding) :
        RecyclerView.ViewHolder(bindInternal.root) {
        abstract fun bind(song: Song)
    }

    class SongViewHolder(
        private val binding: LayoutSongItemBinding,
        private val onItemClickListener: OnItemClickListener<Song>?
    ) :
        BaseViewHolder(binding) {
        override fun bind(song: Song) {
            binding.imgUrl = song.song.imImage.first().label
            binding.name = song.song.imName.label
            binding.artist = song.song.imArtist.label
            binding.clMain.setOnClickListener { onItemClickListener?.onClick(song) }
        }
    }

    class AlbumViewHolder(
        private val binding: LayoutAlbumItemBinding,
        private val onItemClickListener: OnItemClickListener<Song>?
    ) :
        BaseViewHolder(binding) {
        override fun bind(song: Song) {
            val img = song.song.imImage.find { it.attributes.height.toInt() > 100 }?.label
            binding.imgUrl = img ?: song.song.imImage.first().label
            binding.name = song.song.imName.label
            binding.artist = song.song.imArtist.label
            binding.mcvMain.setOnClickListener { onItemClickListener?.onClick(song) }

        }
    }

    class ArtistViewHolder(
        private val binding: LayoutArtistItemBinding,
        private val onItemClickListener: OnItemClickListener<Song>?
    ) :
        BaseViewHolder(binding) {
        override fun bind(song: Song) {
            val img = song.song.imImage.find { it.attributes.height.toInt() > 100 }?.label
            binding.imgUrl = img ?: song.song.imImage.first().label
            binding.name = song.song.imArtist.label
            binding.clMain.setOnClickListener { onItemClickListener?.onClick(song) }

//            binding.artist = song.imArtist.label
        }
    }

    enum class ViewType {
        SONG,
        ALBUM,
        ARTIST,
        FAV,
    }
}