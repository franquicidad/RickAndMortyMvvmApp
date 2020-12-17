package com.franco.rickandmortymvvmapp

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.franco.rickandmortymvvmapp.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:MainViewModel by viewModels()



    //private val customFragmentFactory =CustomFragmentFactory(Repository)
    override fun onCreate(savedInstanceState: Bundle?) {
       // supportFragmentManager.fragmentFactory =
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment,HomeFragment::class.java,null)
            .commit()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController!!, appBarConfiguration)
        navView.setupWithNavController(navController!!)
    }
}