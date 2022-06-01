package com.example.a7minuteworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.a7minuteworkout.fragments.chart
import com.example.a7minuteworkout.fragments.history
import com.example.a7minuteworkout.fragments.person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val personFragment = person()
    private val chartFragment = chart()
    private val historyFragment = history()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(findViewById(R.id.toolbar_exercise_activity))

        addFragment(personFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_person -> {
                    replaceFragmaent(personFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.ic_chart -> {
                    replaceFragmaent(chartFragment)
                    return@setOnNavigationItemSelectedListener  true
                }

                R.id.ic_history -> {
                    replaceFragmaent(historyFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
           false
        }


    }



    private fun addFragment(fragment: Fragment) {
        if (fragment != null) {

            val fragmentManager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fragment_contaiber, fragment)
            transaction.commit()
        }
    }

    private fun replaceFragmaent(fragment: Fragment) {
        if (fragment != null) {
            val fragmentManager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_contaiber, fragment)
            transaction.commit()
        }
    }

//    fun person_click(view: View) {
//        val intent = Intent(this, CategoryActivity::class.java)
//        startActivity(intent)
//    }



}