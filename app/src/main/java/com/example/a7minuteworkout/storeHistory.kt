package com.example.a7minuteworkout
import java.util.*

class storeHistory {
    companion object{
        private val historyList = ArrayList<historyModel>();
        fun defaultHistory(): ArrayList<historyModel>{
            val test= historyModel(1,"test1",R.drawable.ic_abdominal_crunch,"17-17-2001");
            historyList.add(test);
            return historyList;
        }
        fun addHistory(historyModel: historyModel){
            this.historyList.add(historyModel);
        }
    }
}