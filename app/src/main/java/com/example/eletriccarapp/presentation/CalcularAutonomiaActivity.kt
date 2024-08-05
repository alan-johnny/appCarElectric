package com.example.eletriccarapp.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R

class CalcularAutonomiaActivity : AppCompatActivity() {
    lateinit var preco: EditText
    lateinit var kmPercorridos: EditText
    lateinit var resultado: TextView
    lateinit var bntCalcular: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        setupViews()
    }

    fun setupViews() {
        preco = findViewById(R.id.et_preco_kWh)
        kmPercorridos = findViewById(R.id.et_km_value)
        resultado = findViewById(R.id.tv_result)
        bntCalcular = findViewById(R.id.btn_calcular)
    }

    fun setupListeners() {
        bntCalcular.setOnClickListener {
            calcular()  // call the function to calculate the autonomy.
        }

    fun calcular() {
        val preco= preco.text.toString().toFloat()
        val km = kmPercorridos.text.toString().toFloat()
        val result =  preco / km
        resultado.text = result.toString()
    }
}