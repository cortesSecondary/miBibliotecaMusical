package com.example.mibibliotecamusical

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mibibliotecamusical.databinding.ItemFindBinding
import com.example.mibibliotecamusical.databinding.ItemHomeBinding

class SongListAdapter(): ListAdapter<Song, RecyclerView.ViewHolder>(SongDiffCallback())
{
    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val binding = ItemFindBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_find, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val song = getItem(position)

        Log.e("Playlist_data",song.toString())

        with(holder as ViewHolder)
        {
            with(binding)
            {
                itemTitle.text = song.titulo
                itemDuration.text = convertDuration(song.duracion)
                itemDuration.text = song.duracion.toString()
                Glide.with(context)
                    .load("https://source.boomplaymusic.com/group10/M00/04/12/eba714a6f0c445c68e8e23e639a03409_320_320.jpg")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(itemImage)
            }
        }
    }

    class SongDiffCallback: DiffUtil.ItemCallback<Song>()
    {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

    fun convertDuration(seconds: Int): String {
        val minutes = seconds / 60
        val secondsRes = seconds % 60

        Log.e("Tiempo", "$minutes:${String.format("%02d", secondsRes)}")
        return "$minutes:${String.format("%02d", secondsRes)}"
    }
}