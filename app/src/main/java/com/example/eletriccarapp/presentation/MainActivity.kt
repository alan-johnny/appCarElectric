package com.example.eletriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R

class MainActivity : AppCompatActivity() {
    lateinit var preco: EditText
    lateinit var kmPercorridos: EditText
    lateinit var bntCalcular: Button
    lateinit var resultado: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()


        setupListeners()
    }

    fun setupViews() {
        preco = findViewById(R.id.et_preco_kWh)
        kmPercorridos = findViewById(R.id.et_km_value)
        bntCalcular = findViewById(R.id.btn_cacular)
        resultado = findViewById(R.id.tv_result)
    }

    fun setupListeners() {
        bntCalcular.setOnClickListener {
           //calcular()
            startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }

    }


    fun calcular() {
        val preco= preco.text.toString().toFloat()
        val km = kmPercorridos.text.toString().toFloat()
        val result =  preco / km
        resultado.text = result.toString()
    }


}