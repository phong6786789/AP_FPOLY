package com.subi.apsubi

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.subi.apsubi.data.model.*
import kotlinx.android.synthetic.main.activity_home.*


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    var currentFragment: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_home)
        setUpNavigation()
    }

    //block onBackPressed() if it's first fragment
    override fun onBackPressed() {
        currentFragment = NavHostFragment.findNavController(fragment).currentDestination?.id

        if (currentFragment == R.id.seclectCampusFragment || currentFragment == R.id.homeFragment ||
            currentFragment == R.id.newPagerFragment || currentFragment == R.id.scheduleFragment ||
            currentFragment == R.id.scoreFragment || currentFragment == R.id.diemDanhFragment
        ) {
            return
        } else {
            super.onBackPressed()
        }
    }

    fun setUpNavigation() {
        currentFragment = NavHostFragment.findNavController(fragment).currentDestination?.id
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            bottom_nav,
            navHostFragment!!.navController
        )
        //hide bottom navigation
        val navController = findNavController(R.id.fragment)
        bottom_nav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.seclectCampusFragment -> bottom_nav.visibility = View.GONE
                R.id.wedViewFragment -> bottom_nav.visibility = View.GONE
                else -> bottom_nav.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        var TOKEN = ""
        var isConnect = false
        var listBD: ArrayList<BangDiem> = ArrayList()
        var listDD: ArrayList<DiemDanh> = ArrayList()
        var listDTK: ArrayList<DiemTheoKy> = ArrayList()
        var listLH: ArrayList<LichHoc> = ArrayList()
        var listN1: ArrayList<News> = ArrayList()
        var listN2: ArrayList<News> = ArrayList()
        var listN3: ArrayList<News> = ArrayList()
    }
}