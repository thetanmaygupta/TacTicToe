package com.twayne.tactictoe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.twayne.tactictoe.databinding.ActivityChoosePlayerBinding

class ChoosePlayer : AppCompatActivity(){
    var binding:ActivityChoosePlayerBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosePlayerBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.playerx?.setOnClickListener {
            var intentX = Intent(this, tactictoe::class.java)
            intentX.putExtra("SYMBOL", "X")
            startActivity(intentX)
            finish()
        }
        binding?.playero?.setOnClickListener {
            var intentO = Intent(this, tactictoe::class.java)
            intentO.putExtra("SYMBOL", "O")
            startActivity(intentO)
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

