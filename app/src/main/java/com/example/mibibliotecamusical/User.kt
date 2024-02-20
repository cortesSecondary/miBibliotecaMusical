package com.example.mibibliotecamusical

import com.google.gson.annotations.SerializedName
import java.util.Date

data class User(
    var id: Int = 0,
    @SerializedName("username")
    var username: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("genero")
    var genero: String,
    @SerializedName("pais")
    var pais: String,
    @SerializedName("codigoPostal")
    var codigoPostal: String
)