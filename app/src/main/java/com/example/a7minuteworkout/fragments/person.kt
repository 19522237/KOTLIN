package com.example.a7minuteworkout.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.a7minuteworkout.*
import com.example.a7minuteworkout.fragments.person
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_person.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [person.newInstance] factory method to
 * create an instance of this fragment.
 */
class person : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_person, container, false)
        // Inflate the layout for this fragment
        var fullBody: LinearLayout? = null;
        fullBody = view.findViewById(R.id.fullBody)
        fullBody.setOnClickListener{
            val intent = Intent(context, SecoundExercise::class.java)
            startActivity(intent)
        }
        //-- belly
        var belly: LinearLayout? = null;
        belly = view.findViewById(R.id.belly)
        belly.setOnClickListener{
            val intent = Intent(context, SecoundExercise::class.java)
            startActivity(intent)
        }
        //-- hand
        var hand: LinearLayout? = null;
        hand = view.findViewById(R.id.hand)
        hand.setOnClickListener{
            val intent = Intent(context, SecoundExercise::class.java)
            startActivity(intent)
        }
        //-- leg
        var leg: LinearLayout? = null;
        leg = view.findViewById(R.id.hand)
        leg.setOnClickListener{
            val intent = Intent(context, SecoundExercise::class.java)
            startActivity(intent)
        }
        //-- buttmuscles
        var buttmuscles: LinearLayout? = null;
        buttmuscles = view.findViewById(R.id.hand)
        buttmuscles.setOnClickListener{
            val intent = Intent(context, SecoundExercise::class.java)
            startActivity(intent)
        }
        return view
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        person.setOnClickListener{
//            //val intent= Intent(getActivity(),CategoryActivity::class.java)
//            //startActivity(intent)
//            val intent = Intent (fragment_contaiber.context,CategoryActivity::class.java)
//            startActivity(intent)
//        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment person.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            person().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}