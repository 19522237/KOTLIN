package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*
import kotlinx.android.synthetic.main.activity_exercise.*
 class CategoryActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

//        var btnStart=findViewById<Button>(R.id.btnStart)
//        btnStart.setOnClickListener {
//            val intent = Intent(this,SecoundExercise::class.java)
//            startActivity(intent)
//
//        }
    }

     fun fullBody(view: View) {
         val intent=Intent(this,SecoundExercise::class.java)
         startActivity(intent)
     }
     fun belly(view: View) {
         val intent=Intent(this,ThirdExercise::class.java)
         startActivity(intent)
     }

     fun hand(view: View) {
         val intent=Intent(this,FourthActivity::class.java)
         startActivity(intent)
     }
     fun leg(view: View) {
         val intent=Intent(this,FifthActivity::class.java)
         startActivity(intent)
     }
     fun buttmuscles(view: View) {
         val intent=Intent(this,SixthActivity::class.java)
         startActivity(intent)
     }


 }