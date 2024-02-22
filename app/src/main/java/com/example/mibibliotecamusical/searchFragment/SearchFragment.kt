package com.example.mibibliotecamusical.searchFragment

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mibibliotecamusical.BibliotecaApplication
import com.example.mibibliotecamusical.utils.Constants
import com.example.mibibliotecamusical.utils.OnClickListener
import com.example.mibibliotecamusical.R
import com.example.mibibliotecamusical.entities.Song
import com.example.mibibliotecamusical.services.SongService
import com.example.mibibliotecamusical.databinding.FragmentSearchBinding
import com.example.mibibliotecamusical.entities.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var mSongListAdapter: SongListAdapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    private lateinit var originalSongs: List<Song>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadSongs()
        setupSearchView()
    }

    private fun loadSongs() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SongService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.getSongs()
                val songs = result.body()!!

                originalSongs = songs

                withContext(Dispatchers.Main) {
                    mSongListAdapter.submitList(songs)
                }
            } catch (e: Exception) {
                Log.e("Retrofit song error", e.toString())
            }
        }
    }

    private fun setupRecyclerView() {
        mSongListAdapter = SongListAdapter(this)
        mLinearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.recyclerViewSongs.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayoutManager
            adapter = mSongListAdapter
        }

        loadSongs()
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
        val filteredSongs = originalSongs.filter { song ->
            song.titulo.contains(text, ignoreCase = true)
        }
        mSongListAdapter.submitList(filteredSongs)
    }

    override fun addSong(song: Song) {
        BibliotecaApplication.songID = song.id.toString()
        findNavController().navigate(R.id.action_searchOption_to_playlistFragment)
    }

    override fun addPlaylist(playlist: Playlist) {
        TODO("Not yet implemented")
    }
}
