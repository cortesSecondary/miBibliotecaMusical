package com.example.mibibliotecamusical

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mibibliotecamusical.databinding.ActivityLoginBinding
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
                    if ((user.username == username || user.email == username) && user.password == password)
                    {
                        // Correct login
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