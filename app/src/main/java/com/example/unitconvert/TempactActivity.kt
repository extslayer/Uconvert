package com.example.unitconvert

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconvert.databinding.ActivityTempactBinding
import com.example.unitconvert.databinding.ActivityWeightactBinding
import java.util.Locale

class TempactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityTempactBinding by lazy {
            ActivityTempactBinding.inflate(layoutInflater)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val weightunits = resources.getStringArray(R.array.tempunits)
        val arrayadapter = ArrayAdapter(this, R.layout.dropdown_item, weightunits)
        binding.fromunit.setAdapter(arrayadapter)
        binding.tounit.setAdapter(arrayadapter)

        val inttxt = binding.inttxt
        val fromunit = binding.fromunit
        val tounit = binding.tounit
        val outtxt = binding.outtxt


        val convertbutton = binding.convertbutton
        convertbutton.setOnClickListener {
            val out = tempcal(
                inttxt.text.toString().toInt(),
                fromunit.text.toString(),
                tounit.text.toString()
            )
            val gg = ("%.2f".format(out))
            outtxt.text = ("$gg"+" ${tounit.text.toString()}")

        }


    }

    private fun tempcal(int: Int, s: String, s1: String): Double {
        var a = 00.00
        when(s){
            "Celsius"->{
                when(s1){
                    "Celsius"->{
                        a = int + 00.00
                    }
                    "Fahrenheit"->{
                        a = (int * 1.8) + 32
                    }
                    "Kelvin"->{
                        a = int + 273.15
                    }
                }
            }
            "Fahrenheit"->{
                when(s1){
                    "Celsius"->{
                        a = (int-32) * 0.55
                    }
                    "Fahrenheit"->{
                        a = int+ 00.00
                    }
                    "Kelvin"->{
                        a = ((int-32) * 0.55) + 273.15
                    }
                }

            }
            "Kelvin"->{
                when(s1){
                    "Celsius"->{
                        a = int - 273.15
                    }
                    "Fahrenheit"->{
                        a = ((int - 273.15) * 1.8) + 32
                    }
                    "Kelvin"->{
                        a = int + 00.00
                    }
                }

            }
        }
        return a

    }
}