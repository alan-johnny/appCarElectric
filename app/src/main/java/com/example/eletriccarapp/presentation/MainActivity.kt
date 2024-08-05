package com.example.eletriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R

class MainActivity : AppCompatActivity() {

    lateinit var bntCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()


        setupListeners()
    }

    fun setupViews() {

        bntCalcular = findViewById(R.id.btn_cacular)

    }

    fun setupListeners() {
        bntCalcular.setOnClickListener {

            startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }

    }





}