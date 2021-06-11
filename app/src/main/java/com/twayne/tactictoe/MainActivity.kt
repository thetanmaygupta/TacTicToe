package com.twayne.tactictoe

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*Hide Status Bar And Action Bar Coz It Looks Weird With Splash*/
        getSupportActionBar()!!.hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
            finish()
        }, 3000)
    }
}