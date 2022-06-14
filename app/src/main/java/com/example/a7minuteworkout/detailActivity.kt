package com.example.a7minuteworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class detailActivity : AppCompatActivity() {
    private var historyList: ArrayList<historyModel>?= null
    private var currentHistoryPosition =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_history)
    }
    fun detailOnclick(view: View){
        currentHistoryPosition=-1;
        this.historyList = storeHistory.defaultHistory();
        while (currentHistoryPosition< historyList?.size!!-1){
            if (currentHistoryPosition==view.id){
                if(historyList!![currentHistoryPosition+1].getId()==1){
                    val intent = Intent(this, SecoundExercise::class.java)
                    startActivity(intent)
                }
                else{
                    val intent = Intent(this, ThirdExercise::class.java)
                    startActivity(intent)
                }
            }
            currentHistoryPosition++;
        }

    }
}