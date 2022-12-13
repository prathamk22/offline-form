package com.offline.form.builder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val start_btn=findViewById(R.id.btnStart) as Button
        start_btn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }

    }
}