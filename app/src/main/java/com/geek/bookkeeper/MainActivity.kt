package com.geek.bookkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.geek.bookkeeper.databinding.ActivityMainBinding
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import timber.log.Timber

class MainActivity : AppCompatActivity() {

     var user: User? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val credentials = Credentials.anonymous()

        bookKeeperApp.loginAsync(credentials){
            if(it.isSuccess){
                Timber.d("Successful anonymous authentication")
                setUpViews()
            }else {
                Timber.e("Error in anonymous login %s", it.error.toString())
            }
        }
    }

    private fun setUpViews() {

        //Finding the Navigation Controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        val navController = navHostFragment.navController

        // Setting Navigation Controller with the BottomNavigationView
        NavigationUI.setupWithNavController(binding.bottomNavView, navController)

        // Setting Up ActionBar with Navigation Controller
        // Pass the IDs of top-level destinations in AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.bookListFragment,
                R.id.addBookFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}