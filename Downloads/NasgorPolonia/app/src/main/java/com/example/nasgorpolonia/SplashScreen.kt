package com.example.nasgorpolonia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.coroutines.delay

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() //untuk menghilangkan appsbar
        setContentView(R.layout.splashscreen_main)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this@SplashScreenActivity, Login::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}