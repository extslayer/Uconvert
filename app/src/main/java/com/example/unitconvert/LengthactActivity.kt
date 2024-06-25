package com.example.unitconvert

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconvert.databinding.ActivityLengthactBinding

class LengthactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityLengthactBinding by lazy {
            ActivityLengthactBinding.inflate(layoutInflater)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lengthunits = resources.getStringArray(R.array.lenunits)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,lengthunits)
        binding.fromunit.setAdapter(arrayAdapter)
        binding.tounit.setAdapter(arrayAdapter)

        val inttxt = binding.inttxt
        val fromunit = binding.fromunit
        val tounit = binding.tounit
        val outtxt = binding.outtxt

        val convertbutton = binding.convertbutton
        convertbutton.setOnClickListener {
            val out = lencal(inttxt.text.toString().toInt(),fromunit.text.toString(),tounit.text.toString())
            val gg = ("%.2f".format(out))
            outtxt.text = ("$gg"+" ${tounit.text.toString()}")
        }
    }

    private fun lencal(int: Int=1, s: String="Km", s1: String="Km"): Double {
        var a = 00.00
        when(s){
            "Km"->{
                when(s1){
                    "Km"->{
                        a = int + 00.00
                    }
                    "m"->{
                        a = int * 1000.00
                    }
                    "Cm"->{
                        a = int * 100000.00
                    }
                    "Feet"->{
                        a = int * 3280.84
                    }
                }
            }
            "m"->{
                when(s1){
                    "Km"->{
                        a = int * 0.001
                    }
                    "m"->{
                        a = int + 00.00
                    }
                    "Cm"->{
                        a = int * 100.00
                    }
                    "Feet"->{
                        a = int * 3.28084
                    }
                }

            }
            "Cm"->{
                when(s1){
                    "Km"->{
                        a = int * 0.00001
                    }
                    "m"->{
                        a = int * 0.01
                    }
                    "Cm"->{
                        a = int + 00.00
                    }
                    "Feet"->{
                        a = int * 0.0328084
                    }
                }

            }
            "Feet"->{
                when(s1){
                    "Km"->{
                        a = int * 0.0003048
                    }
                    "m"->{
                        a = int * 0.3048
                    }
                    "Cm"->{
                        a = int+ 30.48
                    }
                    "Feet"->{
                        a = int + 00.00
                    }
                }

            }
        }
        return a

    }

}