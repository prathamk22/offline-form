package com.offline.form.builder

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.widget.Button
import android.widget.TextView
@Suppress("DEPRECATION")
class Splash : AppCompatActivity() {
    private lateinit var textView:TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        textView=findViewById(R.id.surveyText)
        textView.text=Html.fromHtml("Project Name: <b>Impact Assessment Study</b> || \n<b>Impact Assessment Survey under Rural Finance Expansion Programme</b> || \n<b>Survey By: Ministry of Finance and National Planning</b> ")

        val start_btn=findViewById(R.id.btnStart) as Button
        start_btn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }

    }
}