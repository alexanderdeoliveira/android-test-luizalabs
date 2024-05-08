package com.android_test_luizalabs.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android_test_luizalabs.R

fun AppCompatActivity.navigateToFragment(fragment: Fragment, name: String) {
    navigation(supportFragmentManager, fragment, name)
}

fun Fragment.navigateToFragment(fragment: Fragment, name: String) {
    navigation(requireActivity().supportFragmentManager, fragment, name)
}

fun navigation(fragmentManager: FragmentManager, fragment: Fragment, name: String) {
    fragmentManager
        .beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(name)
        .commit()
}