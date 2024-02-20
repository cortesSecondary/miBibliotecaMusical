package com.example.mibibliotecamusical

import retrofit2.Response
import retrofit2.http.GET

interface PlaylistService {
    @GET(Constants.PLAYLIST_PATH)
    suspend fun getPlaylists(): Response<MutableList<Playlist>>
}