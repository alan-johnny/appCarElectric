package com.example.eletriccarapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eletriccarapp.R

class CalcularAutonomiaActivity : AppCompatActivity() {

    lateinit var preco: EditText
    lateinit var kmPercorridos: EditText
    lateinit var resultado: TextView
    lateinit var bntCalcular: Button
    lateinit var bntClose: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        setupViews()
        setupListeners()
        setupCache()
    }

    private fun setupCache() {
        val valorCache = getSharedPref()
        resultado.text = valorCache.toString()
    }

    fun setupViews() {
        preco = findViewById(R.id.et_preco_kWh)
        kmPercorridos = findViewById(R.id.et_km_value)
        resultado = findViewById(R.id.tv_result)
        bntCalcular = findViewById(R.id.btn_calcular)
        bntClose = findViewById(R.id.ic_close)

    }

    fun setupListeners() {

        bntCalcular.setOnClickListener {
            calcular()  // call the function to calculate the autonomy.
        }

        bntClose.setOnClickListener {
            finish()
        }
    }


    fun calcular() {
        val preco= preco.text.toString().toFloat()
        val km = kmPercorridos.text.toString().toFloat()
        val result =  preco / km
        resultado.text = result.toString()
        saveSharedPref(result)
    }

    fun saveSharedPref(resultado: Float){
        val sharedPref = getPreferences(MODE_PRIVATE)?: return
        with(sharedPref.edit()) {
            putFloat(getString(R.string.save_calc),resultado)
          apply()
        }
    }

    fun getSharedPref(): Float {
        val sharedPref = getPreferences(MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.save_calc), 0.0F)
    }
}