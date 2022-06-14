package com.example.a7minuteworkout

class storeHistory{
    companion object name{
        @JvmField
        val historyList = ArrayList<historyModel>()
        @JvmStatic
        fun defaultHistory(): ArrayList<historyModel> {
            return historyList
        }
        @JvmStatic
        fun addHistory(historyModel: historyModel){
            historyList.add(historyModel);
        }
    }
}