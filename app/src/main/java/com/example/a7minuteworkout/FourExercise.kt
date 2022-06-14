package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FourExercise : AppCompatActivity() {
    var newArray = arrayOf<Int>(R.id.tay_pose1, R.id.tay_pose2, R.id.tay_pose3, R.id.tay_pose4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four_exercise)
        val button = findViewById<Button>(R.id.btnGetStart2);
        button.setOnClickListener{
            val a = historyModel(1,"Tập tay", R.drawable.tay, "1-1-2001");
            storeHistory.addHistory(a);
            val intent = Intent(this, ExerciseTay::class.java)
            startActivity(intent)
        }
    }
}