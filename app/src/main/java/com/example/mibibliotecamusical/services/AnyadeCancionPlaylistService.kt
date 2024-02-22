package com.example.mibibliotecamusical.services

import com.example.mibibliotecamusical.entities.AnyadeCancionPlaylist
import com.example.mibibliotecamusical.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AnyadeCancionPlaylistService {
    @Headers("Content-Type: application/json")
    @POST(Constants.CANCION_PLAYLIST_PATH)
    suspend fun postCancionPlaylist(@Body anyadeCancionPlaylist: AnyadeCancionPlaylist): Response<AnyadeCancionPlaylist>
}