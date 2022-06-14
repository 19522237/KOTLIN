package com.example.a7minuteworkout
import java.util.*
class Constants2 {
    companion object {

        // The drawable images used here is added in the drawable folder.
        /**
         * Here we are adding all exercises to a single list with all the default values.
         */
        fun defaultExerciseList(): ArrayList<ExerciseModel> {

            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(1, "Tập đánh tay", R.drawable.danh_tay, false, false)
            exerciseList.add(jumpingJacks)
            val pushUp1 = ExerciseModel(2, "Tập Nâng Tay", R.drawable.nang_tay, false, false)
            exerciseList.add(pushUp1)
            val wallSit = ExerciseModel(3, "Tập Bắp Tay", R.drawable.tap_tay, false, false)
            exerciseList.add(wallSit)
            val wallSit1 = ExerciseModel(4, "Tập xoay tay", R.drawable.xoay_tay, false, false)
            exerciseList.add(wallSit1)
            return exerciseList
        }
    }
}