package com.example.midwiferyv1.activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_date_picker.*
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.midwiferyv1.R
import com.example.midwiferyv1.commons.CommonMethods
import java.text.SimpleDateFormat
import java.util.*

class DatePickerActivity : AppCompatActivity() {

    val commons = CommonMethods()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)

        //always light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.hide()
        changeStatusBarColor()

        val myCalendar = Calendar.getInstance() //get instance of Calendar so we can use its methods
        //add a listener to date dialog
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }

        textDate.setOnClickListener() { //on click show date dialog
            DatePickerDialog(
                this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(
                    Calendar.MONTH
                ), myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        continueButton.setOnClickListener() {

            var dateString = textDate.text.toString()

            if (dateString.equals("")) {
                Toast.makeText(
                    applicationContext,
                    "Πρέπει να συμπληρώσετε την ημερομηνία",
                    Toast.LENGTH_LONG
                ).show()
            }
            else if(commons.checkDate(dateString) == 1){
                Toast.makeText(
                    applicationContext,
                    "Έχετε περάσει την σημερινή ημερομηνία",
                    Toast.LENGTH_LONG
                ).show()
            }
            else if(commons.checkDate(dateString) == 0){
                Toast.makeText(
                    applicationContext,
                    "Η ημερομηνία που επιλέξατε έχει περάσει τους 9 μήνες εγκυμοσύνης",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                saveDate(dateString)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    private fun updateLable(myCalendar: Calendar){ //method to insert date to the textfield
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat)
        textDate.setText(sdf.format(myCalendar.time))
    }

    private fun saveDate(date : String){ //method to save the date to the shared preferences

        val insertedDate = date
        val sharedPreferences = getSharedPreferences("datePref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply(){
            putString("DATE_KEY", insertedDate)
            putString("NAV_KEY", "ON")
        }.apply()

    }

    private fun changeStatusBarColor(){

        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        this.window.statusBarColor = this.resources.getColor(R.color.white)

    }


}