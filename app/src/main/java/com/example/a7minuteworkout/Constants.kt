package com.example.a7minuteworkout
import java.util.*
class Constants {
    companion object {

        // The drawable images used here is added in the drawable folder.
        /**
         * Here we are adding all exercises to a single list with all the default values.
         */
        fun defaultExerciseList(): ArrayList<ExerciseModel> {

            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks =
                ExerciseModel(1, "Tập Chiếc Thuyền", R.drawable.tap_chiec_thuyen, false, false)
            exerciseList.add(jumpingJacks)

            val wallSit = ExerciseModel(2, "Tập Crunch", R.drawable.tap_crunch, false, false)
            exerciseList.add(wallSit)

            val pushUp = ExerciseModel(3, "Tập Kiểu Leo Núi", R.drawable.tap_leo_nui, false, false)
            exerciseList.add(pushUp)
            val pushUp1 = ExerciseModel(3, "Tập Hông Một Bên", R.drawable.taphong_mot_ben, false, false)
            exerciseList.add(pushUp1)


            return exerciseList
        }
    }
}