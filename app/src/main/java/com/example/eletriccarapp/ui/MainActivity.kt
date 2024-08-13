package com.example.eletriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.ui.adapter.CarAdapter

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
        val adapter = CarAdapter(CarFactory.list)
        lista.adapter = adapter
    }

    fun setupListeners() {
        bntCalcular.setOnClickListener {

            startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }

    }





}