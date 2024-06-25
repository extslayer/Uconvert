package com.example.unitconvert

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconvert.databinding.ActivityPressureactBinding

class PressureactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityPressureactBinding by lazy {
            ActivityPressureactBinding.inflate(layoutInflater)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pressureact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val pressunits = resources.getStringArray(R.array.pressunits)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,pressunits)
        binding.fromunit.setAdapter(arrayAdapter)
        binding.tounit.setAdapter(arrayAdapter)

        val inttxt = binding.inttxt
        val fromunit = binding.fromunit
        val tounit = binding.tounit
        val outtxt = binding.outtxt

        val convertbutton = binding.convertbutton
        convertbutton.setOnClickListener {
            val out = presscal(inttxt.text.toString().toInt(),fromunit.text.toString(),tounit.text.toString())
            val gg = ("%.2f".format(out))
            outtxt.text = ("$gg"+" ${tounit.text.toString()}")
        }
    }

    private fun presscal(int: Int, s: String, s1: String): Double {
        var a = 00.00
        when(s){
            "Pa"->{
                when(s1){
                    "Pa"->{
                        a = int + 0.00
                    }
                    "Bar"->{
                        a = int * 0.00001
                    }
                    "atm"->{
                        a = int * 0.00000982
                    }
                }
            }
            "Bar"->{
                when(s1){
                    "Pa"->{
                        a = int * 100000.00
                    }
                    "Bar"->{
                        a = int + 00.00
                    }
                    "atm"->{
                        a = int * 0.986923
                    }
                }

            }
            "atm"->{
                when(s1){
                    "Pa"->{
                        a = int * 101325.00
                    }
                    "Bar"->{
                        a = int * 1.01325
                    }
                    "atm"->{
                        a = int + 00.00
                    }
                }

            }
        }
        return a

    }
}