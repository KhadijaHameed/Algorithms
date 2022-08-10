package com.example.algorithms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Splash : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
       window.statusBarColor = ContextCompat.getColor(this,R.color.dark_blue)
        splashThread()
    }

    private fun splashThread() {
        object : Thread() {
            override fun run() {
                sleep(5000)
                startMainActivity()
            }
        }.start()
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}