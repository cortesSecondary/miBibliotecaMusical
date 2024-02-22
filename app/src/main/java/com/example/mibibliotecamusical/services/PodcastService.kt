package com.example.mibibliotecamusical.services

import com.example.mibibliotecamusical.utils.Constants
import com.example.mibibliotecamusical.entities.Podcast
import retrofit2.Response
import retrofit2.http.GET

interface PodcastService {
    @GET(Constants.PODCAST_PATH)
    suspend fun getPodcasts(): Response<MutableList<Podcast>>
}