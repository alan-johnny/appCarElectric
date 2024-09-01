package com.example.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_BATERIA
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_POTENCIA
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_PRECO
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_RECARGA
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_URL_PHOTO
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.TABLE_NAME
import com.example.eletriccarapp.domain.Carro

class CarRepository(private val context: Context) {

    fun save(carro: Carro) : Boolean {

        var isSaved = false
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(COLUMN_PRECO,carro.preco)
                put(COLUMN_BATERIA, carro.bateria)
                put(COLUMN_POTENCIA, carro.potencia)
                put(COLUMN_RECARGA, carro.recarga)
                put(COLUMN_URL_PHOTO, carro.urlPhoto)
            }

            val inserted = db?.insert(TABLE_NAME, null, values)

            if(inserted != null){
                isSaved = true
            }

        }catch (ex : Exception){
            ex.message?.let {
                Log.e("Error inserting", it)
            }
        }
        return isSaved
    }

}