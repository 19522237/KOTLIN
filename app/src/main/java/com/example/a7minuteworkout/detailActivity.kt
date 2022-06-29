package com.example.a7minuteworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class detailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_history)
        val button = findViewById<LinearLayout>(R.id.cardddd);
        button.setOnClickListener{
            val intent = Intent(this, SecoundExercise::class.java)
            startActivity(intent)
        }
    }
}