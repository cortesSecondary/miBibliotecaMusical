package com.example.mibibliotecamusical

import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    @GET(Constants.ALBUMS_PATH)
    suspend fun getAlbums(): Response<MutableList<Album>>
}