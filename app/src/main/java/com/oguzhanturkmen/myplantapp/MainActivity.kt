package com.oguzhanturkmen.myplantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oguzhanturkmen.myplantapp.basket.BasketFragment
import com.oguzhanturkmen.myplantapp.dashboard.DashboardFragment
import com.oguzhanturkmen.myplantapp.databinding.ActivityMainBinding
import com.oguzhanturkmen.myplantapp.favorite.FavoritePlantFragment
import com.oguzhanturkmen.myplantapp.plants.PlantFragment
import com.oguzhanturkmen.myplantapp.profile.ProfileFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private val dashboardFragment = DashboardFragment()
    private val basketFragment = BasketFragment()
    private val favoritePlantFragment = FavoritePlantFragment()
    private val plantFragment = PlantFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottomNavMenu)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dashboardMenu -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, dashboardFragment).commit()
                return true
            }
            R.id.plantMenu -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, plantFragment).commit()
                return true
            }
            R.id.basketMenu -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, basketFragment).commit()
                return true
            }
            R.id.favMenu -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, favoritePlantFragment).commit()
                return true
            }
            R.id.profileMenu -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, profileFragment).commit()
                return true
            }
        }
        return false
    }

}