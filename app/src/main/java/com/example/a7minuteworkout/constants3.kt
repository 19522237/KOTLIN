package com.example.a7minuteworkout
import java.util.*
class Constants3 {
    companion object {

        // The drawable images used here is added in the drawable folder.
        /**
         * Here we are adding all exercises to a single list with all the default values.
         */
        fun defaultExerciseList(): ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(1, "Tập leo núi", R.drawable.tap_leo_nui, false, false)
            exerciseList.add(jumpingJacks)
            val pushUp1 = ExerciseModel(2, "Tập lunge", R.drawable.tap_lunge, false, false)
            exerciseList.add(pushUp1)
            val wallSit = ExerciseModel(3, "Tập hông một bên", R.drawable.taphong_mot_ben, false, false)
            exerciseList.add(wallSit)
            return exerciseList

        }
    }
}