package com.example.mibibliotecamusical.homeFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mibibliotecamusical.entities.Album
import com.example.mibibliotecamusical.BibliotecaApplication
import com.example.mibibliotecamusical.R
import com.example.mibibliotecamusical.databinding.ItemHomeAlbumsBinding

class AlbumListAdapter: ListAdapter<Album, RecyclerView.ViewHolder>(AlbumDiffCallback())
{
    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val binding = ItemHomeAlbumsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_home_albums, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val album = getItem(position)

        with(holder as ViewHolder)
        {
            with(binding)
            {
                itemTitle.text = album.titulo
                Glide.with(context)
                    .load(BibliotecaApplication.albumImages[position])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(itemImage)
            }
        }
    }

    class AlbumDiffCallback: DiffUtil.ItemCallback<Album>()
    {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }
}