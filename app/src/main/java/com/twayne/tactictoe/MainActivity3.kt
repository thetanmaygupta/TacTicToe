package com.twayne.tactictoe

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        /*Hide Status Bar And Action Bar Coz It Looks Weird With Splash*/
        getSupportActionBar()!!.hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity3, MainActivity2::class.java))
            finish()
        }, 3000)
    }
}