package com.example.midwiferyv1.activities

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.midwiferyv1.R
import com.example.midwiferyv1.commons.CommonMethods
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var commons = CommonMethods()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //always light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val navHomeFragment = supportFragmentManager.findFragmentById(R.id.navFragment) as NavHostFragment
        navController = navHomeFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController) //make navigation bottom items work with fragments

        setupActionBarWithNavController(navController)

        navigateBasedOnDate()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun navigateBasedOnDate(){

        val sharedPreferences = this.getSharedPreferences("datePref", Context.MODE_PRIVATE)
        val savedDate = sharedPreferences?.getString("DATE_KEY", null)
        val savedState = sharedPreferences?.getString("NAV_KEY", null)
        val stringDate = savedDate.toString()

        val current = Date()
        var sfd1 = SimpleDateFormat("dd/MM/yyyy")
        val formattedCurrentDate = sfd1.format(current)
        val formattedCurrentDateString = formattedCurrentDate.toString()

        if(savedState == "ON"){
            if(commons.calculateMonths(formattedCurrentDateString, stringDate) == "1"){
                navController.navigate(R.id.prwtoTriminoFragment)
            } else if(commons.calculateMonths(formattedCurrentDateString, stringDate) == "2"){
                navController.navigate(R.id.defteroTriminoFragment)
            } else if(commons.calculateMonths(formattedCurrentDateString, stringDate) == "3"){
                navController.navigate(R.id.tritoTriminoFragment)
            }
        }

    }

}