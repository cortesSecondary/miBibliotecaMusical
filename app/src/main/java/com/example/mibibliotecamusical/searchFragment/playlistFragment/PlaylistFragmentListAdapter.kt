package com.example.mibibliotecamusical.searchFragment.playlistFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mibibliotecamusical.BibliotecaApplication
import com.example.mibibliotecamusical.utils.OnClickListener
import com.example.mibibliotecamusical.entities.Playlist
import com.example.mibibliotecamusical.R
import com.example.mibibliotecamusical.databinding.ItemFindPlaylistBinding

class PlaylistFragmentListAdapter(private var listener: OnClickListener): ListAdapter<Playlist, RecyclerView.ViewHolder>(
    PlaylistDiffCallback()
)
{
    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val binding = ItemFindPlaylistBinding.bind(view)

        fun setListener(playlist: Playlist)
        {
            binding.itemCard.setOnClickListener {
                listener.addPlaylist(playlist)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_find_playlist, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val playlist = getItem(position)

        with(holder as ViewHolder)
        {
            setListener(playlist)
            with(binding)
            {
                itemTitle.text = playlist.titulo
                itemSongsNumber.text = playlist.numeroCanciones.toString()
                Glide.with(context)
                    .load(BibliotecaApplication.playlistImages.random().toString())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(itemImage)
            }
        }
    }

    class PlaylistDiffCallback: DiffUtil.ItemCallback<Playlist>()
    {
        override fun areItemsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Playlist, newItem: Playlist): Boolean {
            return oldItem == newItem
        }
    }
}