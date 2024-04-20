package com.example.money_pocket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.android.HandlerDispatcher
import java.util.logging.Handler

class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        android.os.Handler().postDelayed({
            var intent = Intent(this,Onbording_Screen::class.java)
            startActivity(intent)

            finish()
        }, 3000)
    }
}