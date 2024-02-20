package com.example.mibibliotecamusical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mibibliotecamusical.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val country = binding.countryEditText.text.toString()
            val gendre = binding.gendreEditText.text.toString()
            val code = binding.codeEditText.text.toString()

            addUser(username, password, email, country, gendre, code)
        }
    }

    private fun addUser(username: String, password: String, email: String, country: String, gendre: String, code: String) {

        val user = User(username = username, password = password, email = email, codigoPostal = code, genero = gendre, pais = country)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.postUser(user)
                val userAPI = result.body()!!

                if (userAPI != null)
                {
                    Toast.makeText(this@RegisterActivity, "Usuario registrado", Toast.LENGTH_SHORT).show()

                    // Redirect to MainActivity
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this@RegisterActivity, "Error al registrar el usuario. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Log.e("AddUser Error", e.toString())
            }
        }
    }
}