package com.example.mibibliotecamusical

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mibibliotecamusical.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var mAlbumListAdapter: AlbumListAdapter
    private lateinit var mPlaylistListAdapter: PlaylistListAdapter
    private lateinit var mPodcastListAdapter: PodcastListAdapter

    private lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun loadAlbums()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AlbumService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.getAlbums()
                val albums = result.body()!!

                withContext(Dispatchers.Main)
                {
                    mAlbumListAdapter.submitList(albums)
                    Log.e("Album", "Submited")
                }
            } catch (e: Exception)
            {
                Log.e("Retrofit album error", e.toString())
            }
        }
    }

    private fun loadPlaylists()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PlaylistService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.getPlaylists()
                val playlists = result.body()!!

                withContext(Dispatchers.Main)
                {
                    mPlaylistListAdapter.submitList(playlists)
                    Log.e("Playlist", "Submited")
                }
            } catch (e: Exception)
            {
                Log.e("Retrofit playlist error", e.toString())
            }
        }
    }

    private fun loadPodcasts()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PodcastService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.getPodcasts()
                val podcasts = result.body()!!

                withContext(Dispatchers.Main)
                {
                    mPodcastListAdapter.submitList(podcasts)
                    Log.e("Podcast", podcasts.toString())
                    Log.e("Podcast", "Submited")
                }
            } catch (e: Exception)
            {
                Log.e("Retrofit podcast error", e.toString())
            }
        }
    }

    private fun setupRecyclerView()
    {
        mAlbumListAdapter = AlbumListAdapter()
        mPlaylistListAdapter = PlaylistListAdapter()
        mPodcastListAdapter = PodcastListAdapter()

        val mLinearLayoutManagerAlbum = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val mLinearLayoutManagerPlaylist = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val mLinearLayoutManagerPodcast = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerViewAlbums.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayoutManagerAlbum
            adapter = mAlbumListAdapter
        }

        binding.recyclerViewPlaylists.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayoutManagerPlaylist
            adapter = mPlaylistListAdapter
        }

        binding.recyclerViewPodcasts.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayoutManagerPodcast
            adapter = mPlaylistListAdapter
        }

        // Load Data
        loadPlaylists()
        loadAlbums()
        loadPodcasts()
    }
}