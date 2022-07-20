package com.mishelk.talia_lior_wedding.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationBarView
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.main_page -> {
                        switchToFragment(MainFragment())
                        return true
                    }
                    R.id.greetings_page -> {
                        switchToFragment(GreetingsFragment())
                        return true
                    }
                    R.id.presents_page -> {
                        switchToFragment(PresentsFragment())
                        return true
                    }
                    R.id.riddles_page -> {
                        switchToFragment(RiddlesFragment())
                        return true
                    }
                    R.id.settings_page -> {
                        switchToFragment(SettingsFragment())
                        return true
                    }
                }
                return false
            }
        })
        // Setting main page as the selected navigation option
        bottomNavigation.selectedItemId = R.id.main_page
    }

    private fun switchToFragment(fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        manager.beginTransaction().replace(R.id.contentFragment, fragment).commit()
    }

    fun onGoToRiddles(view: View) {
        switchToFragment(RiddlesFragment())
        bottomNavigation.selectedItemId = R.id.riddles_page
    }

    fun onGoToPresents(view: View) {
        switchToFragment(PresentsFragment())
        bottomNavigation.selectedItemId = R.id.presents_page
    }

    fun onGoToGreetings(view: View) {
        switchToFragment(GreetingsFragment())
        bottomNavigation.selectedItemId = R.id.greetings_page
    }
}