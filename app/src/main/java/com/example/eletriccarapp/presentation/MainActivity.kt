package com.example.eletriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.presentation.adapter.CarAdapter

class MainActivity : AppCompatActivity() {

    lateinit var bntCalcular: Button
    lateinit var lista : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupListeners()
        setupList()
    }

    fun setupViews() {

        bntCalcular = findViewById(R.id.btn_cacular)
        lista = findViewById(R.id.rv_list_car)

    }
    fun setupList() {
        val dados = arrayOf("watter","sugar", "coffee", "milk")
        val adapter = CarAdapter(dados)
        lista.adapter = adapter
    }

    fun setupListeners() {
        bntCalcular.setOnClickListener {

            startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }

    }





}