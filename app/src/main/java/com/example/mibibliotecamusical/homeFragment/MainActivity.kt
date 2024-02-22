package com.example.mibibliotecamusical.homeFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.mibibliotecamusical.R
import com.example.mibibliotecamusical.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupBottomNav()
    }

    private fun setupBottomNav()
    {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)

        mNavController = navHostFragment.navController
        setupWithNavController(bottomNavigationView, mNavController)
    }
}