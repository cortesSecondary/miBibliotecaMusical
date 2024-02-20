package com.example.mibibliotecamusical

import retrofit2.Response
import retrofit2.http.GET

interface SongService {
    @GET(Constants.SONGS_PATH)
    suspend fun getSongs(): Response<MutableList<Song>>
}