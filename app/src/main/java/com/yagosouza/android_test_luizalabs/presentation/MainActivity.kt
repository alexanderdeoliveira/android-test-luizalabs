package com.yagosouza.android_test_luizalabs.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yagosouza.android_test_luizalabs.R
import com.yagosouza.android_test_luizalabs.presentation.list.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment.newInstance())
                .commitNow()
        }
    }
}