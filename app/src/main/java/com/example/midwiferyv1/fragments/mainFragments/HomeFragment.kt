package com.example.midwiferyv1.fragments.mainFragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.midwiferyv1.R
import com.example.midwiferyv1.commons.CommonMethods
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment(), View.OnClickListener {

    private var commons = CommonMethods()
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
       // navigateBasedOnDate()
        changeStatusBarColor()

        //get date from shared preferences
        val sharedPreferences = activity?.getSharedPreferences("datePref", Context.MODE_PRIVATE)
        val savedDate = sharedPreferences?.getString("DATE_KEY", null)
        val stringDate = savedDate.toString()

        //get current date
        val current = Date()
        var sfd1 = SimpleDateFormat("dd/MM/yyyy")
        val formattedCurrentDate = sfd1.format(current)
        val formattedCurrentDateString = formattedCurrentDate.toString()

        //get number of trimino
        var monthsBetween = commons.calculateMonths(formattedCurrentDateString, stringDate)

        //get text view ids and display the values
        val lastDate = view?.findViewById<TextView>(R.id.lastDate)
        lastDate?.text = stringDate

        val currentDate = view?.findViewById<TextView>(R.id.currentDate)
        currentDate?.text = formattedCurrentDateString

        val monthNum = view?.findViewById<TextView>(R.id.numMonth)
        monthNum?.text = "$monthsBetween"



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<TextView>(R.id.periodosRightBox).setOnClickListener(this)
        view.findViewById<TextView>(R.id.triminoRightBox).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.periodosRightBox -> navController!!.navigate(R.id.settingsFragment3)
            R.id.triminoRightBox -> {

                val arithmosTriminou =  view?.findViewById<TextView>(R.id.numMonth)

                if(arithmosTriminou!!.text.equals("1")){
                    navController!!.navigate(R.id.prwtoTriminoFragment)
                } else if(arithmosTriminou!!.text.equals("2")){
                    navController!!.navigate(R.id.defteroTriminoFragment)
                } else if(arithmosTriminou!!.text.equals("3")){
                    navController!!.navigate(R.id.tritoTriminoFragment)
                }

            }

        }

    }

    @SuppressLint("RestrictedApi")
    private fun changeStatusBarColor(){

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity?.window?.statusBarColor = this.resources.getColor(R.color.white)

    }

}