package com.noyize.deepsky.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.noyize.deepsky.R
import com.noyize.deepsky.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
     //   setInsets()
//        setSupportActionBar(binding.toolbar)
//        setUpNavigation()
    }

//    private fun setInsets(){
//        binding.toolbar.applyInsetter {
//            type(statusBars = true){
//                padding()
//            }
//        }
//    }
//
//    private fun setUpNavigation(){
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        binding.toolbar.setupWithNavController(navController)
//    }
}