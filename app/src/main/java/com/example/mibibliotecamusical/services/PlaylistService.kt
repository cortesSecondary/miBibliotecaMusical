package com.example.mibibliotecamusical.services

import com.example.mibibliotecamusical.utils.Constants
import com.example.mibibliotecamusical.entities.Playlist
import retrofit2.Response
import retrofit2.http.GET

interface PlaylistService {
    @GET(Constants.PLAYLIST_PATH)
    suspend fun getPlaylists(): Response<MutableList<Playlist>>
}