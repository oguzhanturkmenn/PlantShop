package com.oguzhanturkmen.myplantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oguzhanturkmen.myplantapp.basket.BasketFragment
import com.oguzhanturkmen.myplantapp.dashboard.DashboardFragment
import com.oguzhanturkmen.myplantapp.databinding.ActivityMainBinding
import com.oguzhanturkmen.myplantapp.favorite.FavoritePlantFragment
import com.oguzhanturkmen.myplantapp.plants.PlantFragment
import com.oguzhanturkmen.myplantapp.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavMenu, navHostFragment.navController)

        /*
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.loginFragment -> binding.bottomNavMenu.visibility = View.GONE
                R.id.signUpFragment -> binding.bottomNavMenu.visibility = View.GONE
                else -> binding.bottomNavMenu.visibility = View.GONE
            }

        }

         */
    }

}