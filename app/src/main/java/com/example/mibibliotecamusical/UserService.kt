package com.example.mibibliotecamusical

import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET(Constants.USERS_PATH)
    suspend fun getUsers(): Response<MutableList<User>>
}