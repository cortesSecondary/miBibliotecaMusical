package com.example.mibibliotecamusical

import retrofit2.Response
import retrofit2.http.GET

interface PodcastService {
    @GET(Constants.PODCAST_PATH)
    suspend fun getPodcasts(): Response<MutableList<Podcast>>
}