package com.example.midwiferyv1.fragments.periexomena

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.midwiferyv1.R

class YperixografimaFragment : Fragment(), View.OnClickListener{

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_yperixografima, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24))

        val information = view.findViewById<TextView>(R.id.keimeno)
        val previousFragment = findNavController().previousBackStackEntry?.destination?.id
        previousFragment?.let {
            when (previousFragment) {
                R.id.prwtoTriminoFragment -> information.text = resources.getText(R.string.yperixografimata_keimeno_1)
                R.id.defteroTriminoFragment -> information.text = resources.getText(R.string.yperixografimata_keimeno_2)
                R.id.tritoTriminoFragment -> information.text = resources.getText(R.string.yperixografimata_keimeno_3)
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