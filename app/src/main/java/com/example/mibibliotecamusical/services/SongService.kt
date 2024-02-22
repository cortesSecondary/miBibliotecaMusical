package com.example.mibibliotecamusical.services

import com.example.mibibliotecamusical.utils.Constants
import com.example.mibibliotecamusical.entities.Song
import retrofit2.Response
import retrofit2.http.GET

interface SongService {
    @GET(Constants.SONGS_PATH)
    suspend fun getSongs(): Response<MutableList<Song>>
}