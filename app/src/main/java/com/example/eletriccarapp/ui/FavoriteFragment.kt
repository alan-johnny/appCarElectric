package com.example.eletriccarapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.local.CarRepository
import com.example.eletriccarapp.domain.Carro
import com.example.eletriccarapp.ui.adapter.CarAdapter

class FavoriteFragment: Fragment() {

    lateinit var listCarrosFv: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
    }

    private fun getOnLocalDb(): List<Carro> {
        val repository = CarRepository(requireContext())
        val carList = repository.getAll()
        return carList
    }

    fun setupView(view: View) {
        listCarrosFv = view.findViewById(R.id.rv_list_car_favorites)
    }
    fun setupList() {
        val cars = getOnLocalDb()
        val carAdapter = CarAdapter(cars, isFavoriteScreen = true)
        listCarrosFv.apply {
            adapter = carAdapter
            isVisible = true
        }

        carAdapter.carItemLister = {carro ->
           val isDeleted =  CarRepository(requireContext()).delete(carro.id)
        }
    }
}