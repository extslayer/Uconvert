package com.example.unitconvert

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconvert.databinding.ActivitySpeedactBinding

class SpeedactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivitySpeedactBinding by lazy {
            ActivitySpeedactBinding.inflate(layoutInflater)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val speedunits = resources.getStringArray(R.array.speedunits)
        val arrayadapter = ArrayAdapter(this, R.layout.dropdown_item, speedunits)
        binding.fromunit.setAdapter(arrayadapter)
        binding.tounit.setAdapter(arrayadapter)

        val inttxt = binding.inttxt
        val fromunit = binding.fromunit
        val tounit = binding.tounit
        val outtxt = binding.outtxt

        val convertbutton = binding.convertbutton
        convertbutton.setOnClickListener {
            val out = speedcal(inttxt.text.toString().toInt(),fromunit.text.toString(),tounit.text.toString())
            val gg = ("%.2f".format(out))
            outtxt.text = ("$gg"+" ${tounit.text.toString()}")
        }
    }

    private fun speedcal(int: Int, s: String, s1: String): Double {
        var a = 00.00
        when(s){
            "Meter/Sec"->{
                when(s1){
                    "Meter/Sec"->{
                        a = int + 00.00
                    }
                    "Km/Hr"->{
                        a = int * 3.6
                    }
                    "Miles/Hr"->{
                        a = int * 2.23694
                    }
                }
            }
            "Km/Hr"->{
                when(s1){
                    "Meter/Sec"->{
                        a = int * 0.277778
                    }
                    "Km/Hr"->{
                        a = int+ 00.00
                    }
                    "Miles/Hr"->{
                        a = int * 0.621371
                    }
                }

            }
            "Miles/Hr"->{
                when(s1){
                    "Meter/Sec"->{
                        a = int * 0.44704
                    }
                    "Km/Hr"->{
                        a = int * 1.60934
                    }
                    "Miles/Hr"->{
                        a = int + 00.00
                    }
                }

            }
        }
        return a

    }
}