package com.example.midwiferyv1.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.midwiferyv1.R


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        supportActionBar?.hide()

        changeStatusAndNavBarColor()
        checkDateExistanceAndRedirectTo()


    }

    private fun checkDateExistanceAndRedirectTo(){

        val sharedPreferences = this.getSharedPreferences("datePref", Context.MODE_PRIVATE)
        val savedDate = sharedPreferences.getString("DATE_KEY", null) // get date value from shared preferences

        Handler().postDelayed({ //a handler that delays for 3000ms/3s before committing the code bellow

            if(savedDate != null){ // if savedDate is not null then that means user has inputted a date so navigate him to home
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() //finish method to close the previous activity as we don't want to get back in there
            } else {
                val intent = Intent(this, DatePickerActivity::class.java)
                startActivity(intent)
                finish()
            }


        }, 2000)

    }

    private fun changeStatusAndNavBarColor(){

        val window: Window = this@SplashScreenActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this@SplashScreenActivity, R.color.pink_1)
        window.navigationBarColor = ContextCompat.getColor(this@SplashScreenActivity, R.color.pink_1)

    }

}