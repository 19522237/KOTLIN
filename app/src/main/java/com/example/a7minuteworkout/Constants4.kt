package com.example.a7minuteworkout
import java.util.*
class Constants4 {
    companion object {

        // The drawable images used here is added in the drawable folder.
        /**
         * Here we are adding all exercises to a single list with all the default values.
         */
        fun defaultExerciseList(): ArrayList<ExerciseModel> {

            val exerciseList = ArrayList<ExerciseModel>()
            val jumpingJacks = ExerciseModel(1, "Squat", R.drawable.squat_nhun, false, false)
            exerciseList.add(jumpingJacks)
            val pushUp1 = ExerciseModel(2, "DonKey Kicks", R.drawable.tap_donkey_kicks, false, false)
            exerciseList.add(pushUp1)
            val wallSit = ExerciseModel(3, "Fire hdrat", R.drawable.tap_fire_hydrat, false, false)
            exerciseList.add(wallSit)
            val wallSit1 = ExerciseModel(4, "Lunge", R.drawable.tap_lunge, false, false)
            exerciseList.add(wallSit1)
            return exerciseList
        }
    }
}