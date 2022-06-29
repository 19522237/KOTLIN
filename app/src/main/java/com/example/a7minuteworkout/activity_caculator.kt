package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_caculator.*

class activity_caculator : AppCompatActivity() {
    private var weight: TextView?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caculator)
        weight=findViewById(R.id.weight)

    }

    fun decreaseAge(view: View) {
        if (age.text.toString().toInt()>1){
            val age_value=age.text.toString().toInt() +1;
            age.setText(age_value);
        }

    }
    fun increaseAge(view: View) {
        if (age.text.toString().toInt()<100){
            val age_value=age.text.toString().toInt() -1;
            age.setText(age_value);
        }
    }
}