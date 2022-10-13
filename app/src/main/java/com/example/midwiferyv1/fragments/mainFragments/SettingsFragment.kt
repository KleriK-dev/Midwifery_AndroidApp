package com.example.midwiferyv1.fragments.mainFragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.midwiferyv1.R
import com.example.midwiferyv1.commons.CommonMethods
import kotlinx.android.synthetic.main.activity_date_picker.*
import java.text.SimpleDateFormat
import java.util.*

class SettingsFragment : Fragment() {

    val commons = CommonMethods()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        changeStatusBarColor()

        //get the id of the date field
        val dateField = view.findViewById<EditText>(R.id.textDate)

        //initialize field with the previous selected date
        val sharedPreferences = activity?.getSharedPreferences("datePref", Context.MODE_PRIVATE)
        val savedDate = sharedPreferences?.getString("DATE_KEY", null)
        val stringDate = savedDate.toString()
        dateField.setText(stringDate)

        //get instance of Calendar so we can use its methods
        val myCalendar = Calendar.getInstance()
        //add a listener to date dialog
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabelAndDateSharedPreferences(myCalendar)
        }

        dateField.setOnClickListener() { //on click show date dialog
            DatePickerDialog(
                view.context, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(
                    Calendar.MONTH
                ), myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        //get switch id
        val switch_state = view.findViewById<Switch>(R.id.switch_button)

        //get shared preference of nav_key
        val savedState = sharedPreferences?.getString("NAV_KEY", null)

        if(savedState == "ON"){
            switch_state.isChecked = true
        } else {
            switch_state.isChecked = false
        }

        if(switch_state.isChecked == true){
            switch_state.text = "Αυτόματη πλοήγηση στο υπολογισμένο τρίμηνο"
        } else {
            switch_state.text = "Μη αυτόματη πλοήγηση στο υπολογισμένο τρίμηνο"
        }

        switch_state.setOnCheckedChangeListener { compoundButton, isChecked ->

            if(isChecked == true){

                val sharedPreferences = requireActivity().getSharedPreferences("datePref", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply(){
                    putString("NAV_KEY", "ON")
                }.apply()

                switch_state.text = "Αυτόματη πλοήγηση στο υπολογισμένο τρίμηνο"

            } else {

                val sharedPreferences = requireActivity().getSharedPreferences("datePref", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply(){
                    putString("NAV_KEY", "OFF")
                }.apply()

                switch_state.text = "Μη αυτόματη πλοήγηση στο υπολογισμένο τρίμηνο"

            }

        }

        return view
    }

    private fun changeStatusBarColor(){

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.elevation = 0f
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        activity?.window?.statusBarColor = this.resources.getColor(R.color.pink_1)

    }

    private fun updateLabelAndDateSharedPreferences(myCalendar: Calendar){ //method to insert date to the textfield

        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat)
        val dateToString = sdf.format(myCalendar.time)

        if(commons.checkDate(dateToString) == 1){
            Toast.makeText(
                activity,
                "Έχετε περάσει την σημερινή ημερομηνία",
                Toast.LENGTH_LONG
            ).show()
        }
        else if(commons.checkDate(dateToString) == 0){
            Toast.makeText(
                activity,
                "Η ημερομηνία που επιλέξατε έχει περάσει τους 9 μήνες εγκυμοσύνης",
                Toast.LENGTH_LONG
            ).show()
        }
        else {
            textDate.setText(dateToString)

            val sharedPreferences = requireActivity().getSharedPreferences("datePref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply(){
                putString("DATE_KEY", dateToString)
            }.apply()

        }

    }


}