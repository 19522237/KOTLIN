package com.example.a7minuteworkout
import java.util.*
class Constants1 {
    companion object {

        // The drawable images used here is added in the drawable folder.
        /**
         * Here we are adding all exercises to a single list with all the default values.
         */
        fun defaultExerciseList(): ArrayList<ExerciseModel> {

            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks =
                ExerciseModel(1, "Gập bụng", R.drawable.tap_gap_bung, false, false)
            exerciseList.add(jumpingJacks)
            val wallSit = ExerciseModel(2, "Gập bụng ếch", R.drawable.tap_gap_bung_ech, false, false)
            exerciseList.add(wallSit)
            val wallSit1 = ExerciseModel(3, "Gập bụng nâng chân", R.drawable.tap_gap_bung_nang_chan, false, false)
            exerciseList.add(wallSit1)
            val pushUp = ExerciseModel(4, "Gập bụng ngược", R.drawable.tap_gap_bung_nguoc, false, false)
            exerciseList.add(pushUp)
            val pushUp1 = ExerciseModel(5, "Gập bụng với bóng", R.drawable.tap_gap_bung_voi_bong, false, false)
            exerciseList.add(pushUp1)
            return exerciseList
        }
    }
}