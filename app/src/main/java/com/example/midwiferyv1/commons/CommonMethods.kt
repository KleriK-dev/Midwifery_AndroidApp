package com.example.midwiferyv1.commons

import java.text.SimpleDateFormat
import java.util.*

class CommonMethods {

    fun calculateMonths(currentDate: String, lastDate: String): String {

        var sfd = SimpleDateFormat("dd/MM/yyyy")
        var date1 = sfd.parse(currentDate)
        var date2 = sfd.parse(lastDate)

        var days = (date1.time - date2.time) / 86400000

        var numOfMonths = "0"

        if(days <= 90){
            numOfMonths = "1"
        } else if(days > 90 && days <= 181){
            numOfMonths = "2"
        } else if(days > 181){
            numOfMonths = "3"
        }

        return numOfMonths

    }

     fun checkDate(textDate: String): Int {

        val current = Date()
        var sfd1 = SimpleDateFormat("dd/MM/yyyy")
        val formattedCurrentDate = sfd1.format(current)
        val formattedCurrentDateString = formattedCurrentDate.toString()

        var sfd = SimpleDateFormat("dd/MM/yyyy")
        var currentDate = sfd.parse(formattedCurrentDateString)
        var chosenDate = sfd.parse(textDate)

        var days = (currentDate.time - chosenDate.time) / 86400000

        if(currentDate < chosenDate){
            return 1 //first error
        }
        else if(days > 273){
            return 0 //second error
        }

        return 2 //its true
    }

}