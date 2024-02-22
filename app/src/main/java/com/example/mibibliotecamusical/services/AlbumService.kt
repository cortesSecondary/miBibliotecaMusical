package com.example.mibibliotecamusical.services

import com.example.mibibliotecamusical.utils.Constants
import com.example.mibibliotecamusical.entities.Album
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    @GET(Constants.ALBUMS_PATH)
    suspend fun getAlbums(): Response<MutableList<Album>>
}