package com.subi.apsubi

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class PlashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        startActivity(
            Intent(this, HomeActivity::class.java),
            ActivityOptions.makeCustomAnimation(this, R.anim.fade_in, R.anim.fragment_fade_exit).toBundle()
        )


    }
}