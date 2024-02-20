package com.example.mibibliotecamusical

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @GET(Constants.USERS_PATH)
    suspend fun getUsers(): Response<MutableList<User>>

    @Headers("Content-Type: application/json")
    @POST(Constants.USERS_PATH)
    suspend fun postUser(@Body user: User): Response<User>
}