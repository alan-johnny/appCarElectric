package com.example.eletriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.databinding.ActivityMainBinding
import com.example.eletriccarapp.ui.adapter.CarAdapter
import com.example.eletriccarapp.ui.adapter.TabsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()

        val navCcntroller = findNavController(R.id.navigation)
        setupWithNavController(binding.bottomNavigation, navCcntroller)

    }

    fun setupListeners() {
        binding.fabCalcular.setOnClickListener {
            startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }
    }








}