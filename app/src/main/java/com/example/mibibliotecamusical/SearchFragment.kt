package com.example.mibibliotecamusical

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mibibliotecamusical.databinding.FragmentSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var mSongListAdapter: SongListAdapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    private var filteredSongs: List<Song> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupSearchView()
    }

    private fun loadSongs()
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SongService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.getSongs()
                val songs = result.body()!!

                withContext(Dispatchers.Main)
                {
                    filteredSongs = songs
                    mSongListAdapter.submitList(songs)
                    Log.e("Song", "Submited")
                }
            } catch (e: Exception)
            {
                Log.e("Retrofit song error", e.toString())
            }
        }
    }

    private fun setupRecyclerView()
    {
        mSongListAdapter = SongListAdapter()
        mLinearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.recyclerViewSongs.apply {
            setHasFixedSize(true)
            layoutManager = mLinearLayoutManager
            adapter = mSongListAdapter
        }

        // Load Data
        loadSongs()
    }

    private fun setupSearchView() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchText = s.toString().trim().toLowerCase(Locale.getDefault())
                val filteredSongs = if (searchText.isNotEmpty()) {
                    filteredSongs.filter { song ->
                        song.titulo.toLowerCase(Locale.getDefault()).contains(searchText)
                    }
                } else {
                    filteredSongs
                }
                mSongListAdapter.submitList(filteredSongs)
            }
        })
    }
}