package com.example.mibibliotecamusical.loginActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mibibliotecamusical.BibliotecaApplication
import com.example.mibibliotecamusical.homeFragment.MainActivity
import com.example.mibibliotecamusical.databinding.ActivityLoginBinding
import com.example.mibibliotecamusical.services.UserService
import com.example.mibibliotecamusical.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            checkCredentials(username, password)
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkCredentials(username: String, password: String)
    {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.getUsers()
                val users = result.body()!!

                var loginSuccessful = false

                for (user in users)
                {
                    if (username == "" || password == "")
                    {
                        Toast.makeText(this@LoginActivity, "Por favor, rellena los campos.", Toast.LENGTH_SHORT).show()
                    }
                    else if ((user.username == username || user.email == username) && user.password == password) {
                        // Correct login
                        BibliotecaApplication.userID = user.id
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        loginSuccessful = true
                        break
                    }
                }

                // Incorrect login
                if (!loginSuccessful)
                {
                    Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Log.e("Login error", e.toString())
            }
        }
    }
}