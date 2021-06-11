package com.twayne.tactictoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twayne.tactictoe.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private var binding: ActivityMain2Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnstart?.setOnClickListener {
            startActivity(Intent(this@MainActivity2,ChoosePlayer::class.java))
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}