package com.example.mibibliotecamusical.homeFragment

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
import com.example.mibibliotecamusical.BibliotecaApplication
import com.example.mibibliotecamusical.entities.Podcast
import com.example.mibibliotecamusical.R
import com.example.mibibliotecamusical.databinding.ItemHomePodcastsBinding

class PodcastListAdapter: ListAdapter<Podcast, RecyclerView.ViewHolder>(PodcastDiffCallback())
{
    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val binding = ItemHomePodcastsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_home_podcasts, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val podcast = getItem(position)

        Log.e("Podcast_data",podcast.toString())

        with(holder as ViewHolder)
        {
            with(binding)
            {
                itemTitle.text = podcast.titulo
                itemDesciption.text = podcast.descripcion
                Glide.with(context)
                    .load(BibliotecaApplication.podcastImages[position])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(itemImage)
            }
        }
    }

    class PodcastDiffCallback: DiffUtil.ItemCallback<Podcast>()
    {
        override fun areItemsTheSame(oldItem: Podcast, newItem: Podcast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Podcast, newItem: Podcast): Boolean {
            return oldItem == newItem
        }
    }
}