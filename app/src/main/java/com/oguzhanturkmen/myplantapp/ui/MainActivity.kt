package com.oguzhanturkmen.myplantapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.oguzhanturkmen.myplantapp.R
import com.oguzhanturkmen.myplantapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavMenu, navHostFragment.navController)


        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.loginFragment -> binding.bottomNavMenu.visibility = View.GONE
                R.id.signUpFragment -> binding.bottomNavMenu.visibility = View.GONE
                else -> binding.bottomNavMenu.visibility = View.VISIBLE
            }

        }


    }

}