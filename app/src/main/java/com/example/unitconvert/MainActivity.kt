package com.example.unitconvert

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unitconvert.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityMainBinding by lazy {
            ActivityMainBinding.inflate(layoutInflater)
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       /* val but = findViewById<ImageView>(R.id.weight)
        but.setOnClickListener {
            Toast.makeText(this, "lollll", Toast.LENGTH_SHORT).show()
        }*/

        val weight = binding.weight
        weight.setOnClickListener {
            val Intent = Intent(this, weightact::class.java)
            startActivity(Intent)

        }


    }
}