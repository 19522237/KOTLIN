package com.example.a7minuteworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SixthActivity :AppCompatActivity() {
    var newArray = arrayOf<Int>(R.id.boat_pose1, R.id.boat_pose2, R.id.boat_pose3, R.id.boat_pose4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)
    }

    fun Imagebuttonclicked(view: View) {
        var i=0;
        for (item in newArray){
            i++;
            if (view.id==item)
            {
                if (i==1) {
                    val intent = Intent(this, MongActivity::class.java)
                    startActivity(intent)
                }
                if (i==2){
                    val intent = Intent(this, MongActivity2::class.java)
                    startActivity(intent)
                }
                if(i==3){
                    val intent = Intent(this, MongActivity3::class.java)
                    startActivity(intent)
                }
                if(i==4){
                    val intent = Intent(this, MongActivity4::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}



