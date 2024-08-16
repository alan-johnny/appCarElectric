package com.example.eletriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarFactory
import com.example.eletriccarapp.ui.adapter.CarAdapter
import com.example.eletriccarapp.ui.adapter.TabsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {


    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2
    lateinit var fabCalcular: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupTabs()
        setupListeners()
    }

    fun setupViews() {
        viewPager = findViewById(R.id.vp_view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        fabCalcular = findViewById(R.id.fab_calculate)


    }

    fun setupTabs() {
        val adapter = TabsAdapter(this)
        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
               tab?.let {
                   viewPager.currentItem = it.position
               }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
    fun setupListeners() {
        fabCalcular.setOnClickListener {

             startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }
    }



}