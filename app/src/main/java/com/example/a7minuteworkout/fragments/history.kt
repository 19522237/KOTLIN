package com.example.a7minuteworkout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.historyModel
import com.example.a7minuteworkout.storeHistory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [history.newInstance] factory method to
 * create an instance of this fragment.
 */
class history : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var historyList: ArrayList<historyModel>?= null
    private var currentHistoryPosition =-1
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
        currentHistoryPosition =-1
        // Inflate the layout for this fragment
        this.historyList = storeHistory.defaultHistory();
        print(historyList);
        val inflaterr= inflater.inflate(R.layout.fragment_history, container, false)
        var view: LinearLayout = inflaterr.findViewById(R.id.listHistory);
        while (currentHistoryPosition< historyList?.size!!-1){
            val rowView = inflater.inflate(R.layout.card_history,null);
            var cardDetail: LinearLayout = rowView.findViewById(R.id.card_detail);
            cardDetail.removeView(rowView);
            var content: TextView?=null;
            var date: TextView?=null;
            var time:TextView?=null;
            var img: ImageView?=null;
            content = cardDetail.findViewById(R.id.content);
            date = cardDetail.findViewById(R.id.date);
            img= cardDetail.findViewById(R.id.image);
            cardDetail.id=currentHistoryPosition+2;
            content?.text=historyList!![currentHistoryPosition+1].getName();
            date?.text=historyList!![currentHistoryPosition+1].getTime();
            img?.setImageResource(historyList!![currentHistoryPosition+1].getImage());
            (cardDetail.getParent() as LinearLayout).removeView(cardDetail)
            view.addView(cardDetail);
            currentHistoryPosition++;
        }
        return inflaterr;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment history.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            history().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}