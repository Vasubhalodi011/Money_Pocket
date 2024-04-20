package com.example.money_pocket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.money_pocket.databinding.ActivityOnbordingScreenBinding

lateinit var OnbordingBinding:ActivityOnbordingScreenBinding
class Onbording_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OnbordingBinding = ActivityOnbordingScreenBinding.inflate(layoutInflater)
        setContentView(OnbordingBinding.root)

        OnbordingBinding.btnStart.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}