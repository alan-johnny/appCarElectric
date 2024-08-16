package com.example.eletriccarapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.eletriccarapp.ui.CarFragment
import com.example.eletriccarapp.ui.FavoriteFragment
import com.example.eletriccarapp.ui.MainActivity

class TabsAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CarFragment()
            1 -> FavoriteFragment()
            else -> CarFragment()
        }
    }

    override fun getItemCount(): Int {
      return 2
    }
}
