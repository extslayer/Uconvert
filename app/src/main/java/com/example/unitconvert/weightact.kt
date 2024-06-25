package com.example.unitconvert

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconvert.databinding.ActivityMainBinding
import com.example.unitconvert.databinding.ActivityWeightactBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.roundToLong

class weightact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityWeightactBinding by lazy {
            ActivityWeightactBinding.inflate(layoutInflater)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val weightunits = resources.getStringArray(R.array.weightunits)
        val arrayadapter = ArrayAdapter(this, R.layout.dropdown_item, weightunits)
        binding.fromunit.setAdapter(arrayadapter)
        binding.tounit.setAdapter(arrayadapter)


        val inttxt = binding.inttxt
        val fromunit = binding.fromunit
        val tounit = binding.tounit
        val outtxt = binding.outtxt



        val convertbutton = binding.convertbutton
        convertbutton.setOnClickListener {
            val out = weightcal(
                inttxt.text.toString().toInt(),
                fromunit.text.toString(),
                tounit.text.toString()
            )
            val gg = ("%.2f".format(out))
            outtxt.text = ("$gg"+" ${tounit.text.toString()}")

        }


    }

    private fun weightcal(int: Int=1, s: String="Kg", s1: String="Kg"): Double {
        var a = 00.00
        when(s){
            "Kg"->{
                when(s1){
                    "Pounds"->{
                        a = int * 2.20462
                    }
                    "g"->{
                        a = int * 1000.0
                    }
                    "Kg"->{
                        a = int+ 00.00
                    }
                }
            }
            "g"->{
                when(s1){
                    "Pounds"->{
                        a = int * 0.00220462
                    }
                    "g"->{
                        a = int+ 00.00
                    }
                    "Kg"->{
                        a = int * 0.001
                    }
                }

            }
            "Pounds"->{
                when(s1){
                    "Pounds"->{
                        a = int + 00.00
                    }
                    "g"->{
                        a = int * 453.592
                    }
                    "Kg"->{
                        a = int * 0.453592
                    }
                }

            }
        }
        return a

    }


}