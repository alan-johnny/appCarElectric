package com.example.eletriccarapp.data.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_BATERIA
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_CAR_ID
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_POTENCIA
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_PRECO
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_RECARGA
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.COLUMN_URL_PHOTO
import com.example.eletriccarapp.data.local.CarsContract.CarEntry.TABLE_NAME
import com.example.eletriccarapp.data.local.CarsContract.TABLE_CAR
import com.example.eletriccarapp.domain.Carro

class CarRepository(private val context: Context) {

    @SuppressLint("SuspiciousIndentation")
    fun save(carro: Carro) : Boolean {

        var isSaved = false
        try {

            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(COLUMN_CAR_ID, carro.id)
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


    fun findCarById(id: Int) : Carro {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        val columns = arrayOf(

            BaseColumns._ID,
            COLUMN_CAR_ID,
            COLUMN_PRECO,
            COLUMN_BATERIA,
            COLUMN_POTENCIA,
            COLUMN_RECARGA,
            COLUMN_URL_PHOTO
        )

        val filter = "$COLUMN_CAR_ID = ?"
        val args = arrayOf(id.toString())


        val cursor = db.query(
            CarsContract.CarEntry.TABLE_NAME,
            columns,
            filter,
            args,
            null,
            null,
            null

        )

        var itemId: Long = 0
        var itemPreco: String = ""
        var itemBateria: String = ""
        var itemPotencia: String = ""
        var itemRecarga: String = ""
        var itemUrlPhoto: String = ""

        with (cursor) {
            while (moveToFirst()){
                itemId = getLong(getColumnIndexOrThrow(COLUMN_CAR_ID))
                Log.d("ID ->",itemId.toString())

                itemPreco = getString(getColumnIndexOrThrow(COLUMN_PRECO))
                Log.d("Preco ->", itemPreco)

                itemBateria = getString(getColumnIndexOrThrow(COLUMN_BATERIA))
                Log.d("Bateria ->", itemBateria)

                itemPotencia = getString(getColumnIndexOrThrow(COLUMN_POTENCIA))
                Log.d("Potencia ->", itemPotencia)

                itemRecarga = getString(getColumnIndexOrThrow(COLUMN_RECARGA))
                Log.d("Recarga ->", itemRecarga)

                itemUrlPhoto = getString(getColumnIndexOrThrow(COLUMN_URL_PHOTO))
                Log.d("URL Photo ->", itemUrlPhoto)



            }
        }
        cursor.close()
        return Carro(
            id = itemId.toInt(),
            preco = itemPreco,
            bateria = itemBateria,
            potencia = itemPotencia,
            recarga = itemRecarga,
            urlPhoto = itemUrlPhoto,
            isFavorite = true,
        )

    }

}