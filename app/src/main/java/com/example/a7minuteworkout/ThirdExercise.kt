package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ThirdExercise : AppCompatActivity() {
    var newArray = arrayOf<Int>(R.id.bung_pose1, R.id.bung_pose2, R.id.bung_pose3, R.id.bung_pose4, R.id.bung_pose5)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_exercise)
        val button = findViewById<Button>(R.id.btnGetStart1);
        button.setOnClickListener{
            val a = historyModel(1,"Tập bụng", R.drawable.bung, "1-1-2001");
            storeHistory.addHistory(a);
            val intent = Intent(this, ExerciseBung::class.java)
            startActivity(intent)
        }
    }
    fun Imagebuttonclicked(view: View) {
        var i=0;
        for (item in newArray){
            i++;
            if (view.id==item)
            {
                if (i==1) {
                    val intent = Intent(this, BowActivity::class.java)
                    startActivity(intent)
                }
                if (i==2){
                    val intent = Intent(this, BowActivity2::class.java)
                    startActivity(intent)
                }
                if (i==3){
                    val intent = Intent(this, BowActivity3::class.java)
                    startActivity(intent)
                }
                if (i==4){
                    val intent = Intent(this, BowActivity4::class.java)
                    startActivity(intent)
                }
            }
        }
    }

}

