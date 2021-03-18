package com.subi.apsubi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_home)
    }

    //block onBackPressed() if it's first fragment
    override fun onBackPressed() {
        val currentFragment = NavHostFragment.findNavController(fragment).currentDestination?.id
        if (currentFragment == R.id.seclectCampusFragment) {
            return
        } else {
            super.onBackPressed()
        }
    }
}