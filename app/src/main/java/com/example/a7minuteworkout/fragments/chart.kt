package com.example.a7minuteworkout.fragments

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.Time
import com.example.a7minuteworkout.chart.BarData
import com.example.a7minuteworkout.chart.ChartProgressBar
import com.example.a7minuteworkout.chart.OnBarClickedListener
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [chart.newInstance] factory method to
 * create an instance of this fragment.
 */
class chart : Fragment(), OnBarClickedListener {
    private var param1: String? = null
    private var param2: String? = null
    private var mChart: ChartProgressBar? = null

    lateinit var dataReference: DatabaseReference
    lateinit var item: String
    lateinit var list: ArrayList<Time>
    lateinit var list1: ArrayList<Time>
    lateinit var listMonday: ArrayList<Time>
    lateinit var listTuesday: ArrayList<Time>
    lateinit var listWednesday: ArrayList<Time>
    lateinit var listThursday: ArrayList<Time>
    lateinit var listFriday: ArrayList<Time>
    lateinit var listSaturday: ArrayList<Time>
    lateinit var listSunday: ArrayList<Time>
    var sumMonday: Float = 0.0F
    var sumTuesday: Float = 0.0F
    var sumWednesday: Float = 0.0F
    var sumThursday: Float = 0.0F
    var sumFriday: Float = 0.0F
    var sumSaturday: Float = 0.0F
    var sumSunday: Float = 0.0F
    lateinit var listTime: ArrayList<Float>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var view =
            inflater.inflate(com.example.a7minuteworkout.R.layout.fragment_chart, container, false)
        val dataList: ArrayList<BarData> = ArrayList()

        var btnClearData: Button = view.findViewById(R.id.btnClearData)


        btnClearData.setOnClickListener {
            showDialogClear()
        }

        dataReference = FirebaseDatabase.getInstance().getReference("TIME");

        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)

        val date = LocalDate.of(year, month + 1, day)
        val day1 = date.dayOfWeek

        if (day1.getDisplayName(TextStyle.FULL, Locale.getDefault()).equals("Monday")) {
            dataReference.setValue(null)
        }

        list = ArrayList()
        list1 = ArrayList()
        listMonday = ArrayList()
        listTuesday = ArrayList()
        listWednesday = ArrayList()
        listThursday = ArrayList()
        listFriday = ArrayList()
        listSaturday = ArrayList()
        listSunday = ArrayList();
        listTime = ArrayList()

        // Inflate the layout for this fragment

        var btnThem: Button = view.findViewById(com.example.a7minuteworkout.R.id.btnTHem);

        btnThem.setOnClickListener {
            showDialog()
        }

        dataReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                list.clear()
                listMonday.clear()
                listTuesday.clear()
                listWednesday.clear()
                listThursday.clear()
                listFriday.clear()
                listSaturday.clear()
                listSunday.clear()

                sumMonday = 0f
                sumWednesday = 0f
                sumTuesday = 0f
                sumThursday = 0f
                sumSunday = 0f
                sumSaturday = 0f
                sumFriday = 0f

                for (item in dataSnapshot.children) {
                    val user: Time? = item.getValue(Time::class.java)
                    if (user != null) {
                        list.add(user);
                    }
                }

                for (item in list) {
                    if (item.date.equals("Monday")) {
                        listMonday.add(item);
                    } else if (item.date.equals("Tuesday")) {
                        listTuesday.add(item);
                    } else if (item.date.equals("Wednesday")) {
                        listWednesday.add(item);
                    } else if (item.date.equals("Thursday")) {
                        listThursday.add(item);
                    } else if (item.date.equals("Friday")) {
                        listFriday.add(item);
                    } else if (item.date.equals("Saturday")) {
                        listSaturday.add(item);
                    } else if (item.date.equals("Sunday")) {
                        listSunday.add(item);
                    }
                }

                for (item in listMonday) {
                    sumMonday += item.time
                }

                for (item in listTuesday) {
                    sumTuesday += item.time
                }
                for (item in listWednesday) {
                    sumWednesday += item.time
                }
                for (item in listThursday) {
                    sumThursday += item.time
                }

                for (item in listFriday) {
                    sumFriday += item.time
                }
                for (item in listSaturday) {
                    sumSaturday += item.time
                }
                for (item in listSunday) {
                    sumSunday += item.time
                }

                listTime.add(sumMonday)
                listTime.add(sumTuesday)
                listTime.add(sumWednesday)
                listTime.add(sumThursday)
                listTime.add(sumFriday)
                listTime.add(sumSaturday)
                listTime.add(sumSunday)


                var data = BarData("Mon", sumMonday, sumMonday.toInt().toString())
                dataList.add(data)

                data = BarData("Tue", sumTuesday, sumTuesday.toInt().toString())
                dataList.add(data)

                data = BarData("Wed", sumWednesday, sumWednesday.toInt().toString())
                dataList.add(data)

                data = BarData("Thur", sumThursday, sumThursday.toInt().toString())
                dataList.add(data)

                data = BarData("Fri", sumFriday, sumFriday.toInt().toString())
                dataList.add(data)

                data = BarData("Sat", sumSaturday, sumSaturday.toInt().toString())
                dataList.add(data)
                data = BarData("Sun", sumSunday, sumSunday.toInt().toString())
                dataList.add(data)

                mChart = view.findViewById(R.id.ChartProgressBar) as ChartProgressBar

                mChart!!.setDataList(dataList)
                mChart!!.build()
                //mChart.disableBar(dataList.size() - 1);
                mChart!!.setMaxValue(300.0F)

                var txtTBWeek = view.findViewById<TextView>(R.id.txtNumBerTimeWeek)

                var tb: Float =
                    (sumFriday + sumMonday + sumSaturday + sumSunday + sumThursday + sumTuesday + sumWednesday) / 7

                txtTBWeek.setText(tb.toInt().toString() + "phút")

                tb = 0F

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })



        return view
    }


    override fun onBarClicked(index: Int) {

    }

    private fun showDialog() {
        val dialog = Dialog(requireActivity(), android.R.style.ThemeOverlay_Material_Dialog_Alert)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        dialog.window!!.attributes = lp
        dialog.setContentView(R.layout.dialog_them)
        val edtTenBaiTap = dialog.findViewById(R.id.edtBaiTap) as EditText
        val yesBtn = dialog.findViewById(R.id.btnThemBaiTap) as Button
        val edtTime = dialog.findViewById(R.id.edtTime) as EditText

        var spinner: Spinner = dialog.findViewById(R.id.spiner);

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                item = spinner.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        yesBtn.setOnClickListener {
            val userId: String = dataReference.push().getKey().toString()
            var time: Time =
                Time(userId, edtTenBaiTap.text.toString(), edtTime.text.toString().toInt(), item);
            dataReference.child(userId).setValue(time).addOnCompleteListener {
                Toast.makeText(context, "Done", Toast.LENGTH_LONG)
            }.addOnFailureListener {
                Toast.makeText(context, "Fail", Toast.LENGTH_LONG)
            }
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun showDialogClear() {
        val dialog = Dialog(requireActivity(), android.R.style.ThemeOverlay_Material_Dialog_Alert)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        dialog.window!!.attributes = lp
        dialog.setContentView(R.layout.dialog_clear)

        val yesBtn: Button = dialog.findViewById(R.id.btnClear)

        var spinner: Spinner = dialog.findViewById(R.id.spinerClear);

        var item1: String = ""
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                item1 = spinner.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        yesBtn.setOnClickListener {
            dataReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (item in dataSnapshot.children) {
                        val user: Time? = item.getValue(Time::class.java)
                        if (user != null) {
                            list1.add(user);
                        }
                    }

                    for (item in list1) {
                        if (item.date?.equals(item1) == true) {
                            dataReference.child(item.id.toString()).removeValue()
                        }else{
                            Toast.makeText(context,"abc",Toast.LENGTH_LONG)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })
            dialog.dismiss()
        }
        dialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment chart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = chart().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}