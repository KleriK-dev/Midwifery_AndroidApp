package com.example.midwiferyv1.fragments.trimina

import android.animation.LayoutTransition
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.midwiferyv1.R

class PrwtoTriminoFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_prwto_trimino, container, false)

        changeStatusBarColor()

        //handle todolist
        val todolistCard = view.findViewById<LinearLayout>(R.id.layout)
        todolistCard.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        todolistCard.setOnClickListener(){
            expandCard()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<CardView>(R.id.YperixografimataButton).setOnClickListener(this)
        view.findViewById<CardView>(R.id.EksetaseisButton).setOnClickListener(this)
        view.findViewById<CardView>(R.id.DiatrofiButton).setOnClickListener(this)
        view.findViewById<CardView>(R.id.SinithiesButton).setOnClickListener(this)
        view.findViewById<CardView>(R.id.SwmaButton).setOnClickListener(this)
        view.findViewById<CardView>(R.id.SimptomataButton).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.YperixografimataButton -> navController!!.navigate(R.id.action_prwtoTriminoFragment_to_yperixografimaFragment)
            R.id.EksetaseisButton -> navController!!.navigate(R.id.action_prwtoTriminoFragment_to_eksetaseisFragment)
            R.id.DiatrofiButton -> navController!!.navigate(R.id.action_prwtoTriminoFragment_to_diatrofiFragment)
            R.id.SinithiesButton -> navController!!.navigate(R.id.action_prwtoTriminoFragment_to_sinithiesFragment)
            R.id.SwmaButton -> navController!!.navigate(R.id.action_prwtoTriminoFragment_to_swmaFragment)
            R.id.SimptomataButton -> navController!!.navigate(R.id.action_prwtoTriminoFragment_to_simptomataFragment)
        }

    }

    private fun changeStatusBarColor(){

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.elevation = 0f
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        activity?.window?.statusBarColor = this.resources.getColor(R.color.pink_1)

    }

    private fun expandCard(){

        val todolistCard = view?.findViewById<LinearLayout>(R.id.layout)
        val bullets = view?.findViewById<TextView>(R.id.bullets)
        val ImageViewId = view?.findViewById<ImageView>(R.id.arrow)

        var v: Int? = null

        if(bullets?.visibility == View.GONE){
            v = View.VISIBLE
            ImageViewId?.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        } else {
            v = View.GONE
            ImageViewId?.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
        }

        TransitionManager.beginDelayedTransition(todolistCard, AutoTransition())
        bullets?.visibility = v

    }

}