package com.example.unitconvert

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconvert.databinding.ActivityVolumeactBinding

class VolumeactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityVolumeactBinding by lazy {
            ActivityVolumeactBinding.inflate(layoutInflater)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val volume = resources.getStringArray(R.array.volumeunits)
        val arrayadapter = ArrayAdapter(this,R.layout.dropdown_item,volume)
        binding.fromunit.setAdapter(arrayadapter)
        binding.tounit.setAdapter(arrayadapter)

        val inttxt = binding.inttxt
        val fromunit = binding.fromunit
        val tounit = binding.tounit
        val outtxt = binding.outtxt

        val convertbutton = binding.convertbutton
        convertbutton.setOnClickListener {
            val out = volcal(
                inttxt.text.toString().toInt(),
                fromunit.text.toString(),
                tounit.text.toString()
            )
            val gg = ("%.4f".format(out))
            outtxt.text = ("$gg"+" ${tounit.text.toString()}")

        }
    }

    private fun volcal(int: Int=1, s: String="Cubic m", s1: String="Cubic m"): Double {
        var a = 00.00
        when(s){
            "Cubic m"->{
                when(s1){
                    "Cubic m"->{
                        a = int + 00.00
                    }
                    "Cubic Cm"->{
                        a = int * 1000000.00
                    }
                    "Litre"->{
                        a = int * 1000.00
                    }
                    "mL"->{
                        a = int * 1000000.00
                    }
                }
            }
            "Cubic Cm"->{
                when(s1){
                    "Cubic m"->{
                        a = int * 0.000001
                    }
                    "Cubic Cm"->{
                        a = int + 00.00
                    }
                    "Litre"->{
                        a = int * 0.001
                    }
                    "mL"->{
                        a = int * 1.0
                    }
                }

            }
            "Litre"->{
                when(s1){
                    "Cubic m"->{
                        a = int * 0.001
                    }
                    "Cubic Cm"->{
                        a = int * 1000.00
                    }
                    "Litre"->{
                        a = int + 00.00
                    }
                    "mL"->{
                        a = int * 1000.00
                    }
                }

            }
            "mL"->{
                when(s1){
                    "Cubic m"->{
                        a = int * 0.000001
                    }
                    "Cubic Cm"->{
                        a = int * 1.00
                    }
                    "Litre"->{
                        a = int * 0.001
                    }
                    "mL"->{
                        a = int + 0.00
                    }
                }

            }
        }
        return a

    }
}