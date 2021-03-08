package com.example.stakasaki.coffeeorder.registerlogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.stakasaki.coffeeorder.R

/**
 * Activity for register and login
 */

class RegisterLoginActivity : AppCompatActivity(R.layout.activity_register_login) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set a title of actionbar each fragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_register_login) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    // upButton
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}