package com.example.midwiferyv1.fragments.periexomena

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.midwiferyv1.R

class SwmaFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_swma, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24))

    //EDW DEN EINAI GIA KEIMENO ALLA GIA ENA IMAGEVIEW
        val eikona = view.findViewById<ImageView>(R.id.eikona_swmatos)
        val previousFragment = findNavController().previousBackStackEntry?.destination?.id
        previousFragment?.let {
            when (previousFragment) {
                R.id.prwtoTriminoFragment -> eikona.setImageResource(R.drawable.body_image)
                R.id.defteroTriminoFragment -> eikona.setImageResource(R.drawable.first_stage_baby)
                R.id.tritoTriminoFragment -> eikona.setImageResource(R.drawable.forth_stage_baby)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.toolbar -> navController!!.popBackStack()
        }

    }

}