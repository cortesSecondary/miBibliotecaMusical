package com.example.mibibliotecamusical.searchFragment.playlistFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mibibliotecamusical.BibliotecaApplication
import com.example.mibibliotecamusical.utils.Constants
import com.example.mibibliotecamusical.utils.OnClickListener
import com.example.mibibliotecamusical.R
import com.example.mibibliotecamusical.services.PlaylistService
import com.example.mibibliotecamusical.databinding.FragmentPlaylistBinding
import com.example.mibibliotecamusical.entities.Playlist
import com.example.mibibliotecamusical.entities.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentPlaylistBinding

    private lateinit var mPlaylistFragmentListAdapter: PlaylistFragmentListAdapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    private lateinit var originalPlaylists: List<Playlist>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadPlaylists()
        setupSearchView()
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

                originalPlaylists = playlists

                withContext(Dispatchers.Main)
                {
                    mPlaylistFragmentListAdapter.submitList(playlists)
                }
            } catch (e: Exception)
            {
                Log.e("Retrofit album error", e.toString())
            }
        }
    }

    private fun setupRecyclerView() {
        mPlaylistFragmentListAdapter = PlaylistFragmentListAdapter(this)
        mLinearLayoutManager = LinearLayoutManager(requireContext())

        binding.recyclerViewPlaylists.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayoutManager
            adapter = mPlaylistFragmentListAdapter
        }

        loadPlaylists()
    }

    private fun setupSearchView() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun filter(text: String) {
        val filteredPlaylists = originalPlaylists.filter { playlist ->
            playlist.titulo.contains(text, ignoreCase = true)
        }
        mPlaylistFragmentListAdapter.submitList(filteredPlaylists)
    }

    override fun addSong(song: Song) {
        TODO("Not yet implemented")
    }

    override fun addPlaylist(playlist: Playlist) {
        BibliotecaApplication.playlistID = playlist.id.toString()
        findNavController().navigate(R.id.action_playlistFragment_to_searchOption)
    }
}