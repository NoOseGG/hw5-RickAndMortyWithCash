package com.example.rickandmortywithcash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.rickandmortywithcash.databinding.ActivityMainBinding
import com.example.rickandmortywithcash.manager.NightMode
import com.example.rickandmortywithcash.manager.SharedPrefsManager
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.container) as NavHost)
            .navController
    }
    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val sharedPref by inject<SharedPrefsManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(
            when (sharedPref.nightMode) {
                NightMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                NightMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                NightMode.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)

        bottomNavigationListener()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }

    private fun bottomNavigationListener() {
        binding.bottomMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.list -> {
                    navController.navigate(R.id.to_listFragment)
                    true
                }
                R.id.setting -> {
                    navController.navigate(R.id.to_settingFragment)
                    true
                }
                else -> {
                    error("error")
                }
            }
        }
    }
}
