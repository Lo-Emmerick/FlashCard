package com.example.laecards.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.laecards.presentation.ui.home.HomeActivity
import com.laecards.app.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val imageViewGif = findViewById<ImageView>(R.id.imageView)

        Glide.with(this)
            .asGif()
            .load(R.drawable.icon)
            .into(imageViewGif)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 3000)
    }
}