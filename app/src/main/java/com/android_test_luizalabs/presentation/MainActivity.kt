package com.android_test_luizalabs.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android_test_luizalabs.R
import com.android_test_luizalabs.core.extensions.navigateToFragment
import com.android_test_luizalabs.databinding.ActivityMainBinding
import com.android_test_luizalabs.presentation.favorite.FavoriteFragment
import com.android_test_luizalabs.presentation.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToHome()
        binding.setupBottomNav()
    }

    private fun ActivityMainBinding.setupBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> navigateToHome()
                R.id.navigation_favorites -> navigateToFavorites()
            }
            true
        }
    }

    private fun navigateToHome() {
        navigateToFragment(ListFragment.newInstance(), ListFragment::class.java.name)
    }

    private fun navigateToFavorites() {
        navigateToFragment(FavoriteFragment.newInstance(), FavoriteFragment::class.java.name)
    }
}