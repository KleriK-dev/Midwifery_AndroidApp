package com.example.midwiferyv1.fragments.mainFragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.midwiferyv1.R
import com.example.midwiferyv1.commons.CommonMethods
import java.text.SimpleDateFormat
import java.util.*

class MonthsFragment : Fragment(), View.OnClickListener{

    private var commons = CommonMethods()
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_months, container, false)

        changeStatusBarColor()

        val sharedPreferences = activity?.getSharedPreferences("datePref", Context.MODE_PRIVATE)
        val savedDate = sharedPreferences?.getString("DATE_KEY", null)
        val stringDate = savedDate.toString()

        val current = Date()
        var sfd1 = SimpleDateFormat("dd/MM/yyyy")
        val formattedCurrentDate = sfd1.format(current)
        val formattedCurrentDateString = formattedCurrentDate.toString()

        val lastDate = view.findViewById<TextView>(R.id.lastDateTextField)
        lastDate.text = "Τελευταία Περ. : $savedDate"

        val currentDate = view.findViewById<TextView>(R.id.currentDateTextField)
        currentDate.text = "Σημερινή Ημερ. : $formattedCurrentDate"

        var monthsBetween = commons.calculateMonths(formattedCurrentDateString, stringDate)

        val monthNum = view.findViewById<TextView>(R.id.monthNumDate)
        monthNum.text = "Τρίμηνο : $monthsBetween"

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<CardView>(R.id.prwtoTriminoButton).setOnClickListener(this)
        view.findViewById<CardView>(R.id.defteroTriminoButton).setOnClickListener(this)
        view.findViewById<CardView>(R.id.tritoTriminoButton).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.prwtoTriminoButton -> navController!!.navigate(R.id.action_monthsFragment_to_prwtoTriminoFragment)
            R.id.defteroTriminoButton -> navController!!.navigate(R.id.action_monthsFragment_to_defteroTriminoFragment)
            R.id.tritoTriminoButton -> navController!!.navigate(R.id.action_monthsFragment_to_tritoTriminoFragment)
        }

    }

    private fun changeStatusBarColor(){

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.elevation = 0f
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        activity?.window?.statusBarColor = this.resources.getColor(R.color.pink_1)

    }

}