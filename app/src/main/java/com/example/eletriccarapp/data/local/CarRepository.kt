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
        var itemPreco = ""
        var itemBateria = ""
        var itemPotencia  = ""
        var itemRecarga  = ""
        var itemUrlPhoto  = ""

        with (cursor) {
            while (moveToNext()){
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

    fun saveIfNoExist(carro: Carro){

        val car = findCarById(carro.id)
        if(car.id == ID_WHEN_NO_CAR ) {
            save(carro)
        }
    }

    fun delete(carId: Int): Boolean {
        var isDeleted = false
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase

            // Definindo a condição para a exclusão
            val whereClause = "$COLUMN_CAR_ID = ?"
            val whereArgs = arrayOf(carId.toString())


            val deletedRows = db.delete(TABLE_NAME, whereClause, whereArgs)


            if (deletedRows > 0) {
                isDeleted = true
            }

        } catch (ex: Exception) {
            ex.message?.let {
                Log.e("Erro ao deletar -> ", it)
            }
        }

        return isDeleted
    }




    fun getAll(): List<Carro> {
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
        val cursor = db.query(
            TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )
    val carros = mutableListOf<Carro>()
        with (cursor) {
            while (moveToNext()){
                val itemId = getLong(getColumnIndexOrThrow(COLUMN_CAR_ID))
                Log.d("ID ->",itemId.toString())

                val itemPreco = getString(getColumnIndexOrThrow(COLUMN_PRECO))
                Log.d("Preco ->", itemPreco)

                val itemBateria = getString(getColumnIndexOrThrow(COLUMN_BATERIA))
                Log.d("Bateria ->", itemBateria)

                val itemPotencia = getString(getColumnIndexOrThrow(COLUMN_POTENCIA))
                Log.d("Potencia ->", itemPotencia)

                val itemRecarga = getString(getColumnIndexOrThrow(COLUMN_RECARGA))
                Log.d("Recarga ->", itemRecarga)

                val itemUrlPhoto = getString(getColumnIndexOrThrow(COLUMN_URL_PHOTO))
                Log.d("URL Photo ->", itemUrlPhoto)
                carros.add(Carro(
                    id = itemId.toInt(),
                    preco = itemPreco,
                    bateria = itemBateria,
                    potencia = itemPotencia,
                    recarga = itemRecarga,
                    urlPhoto = itemUrlPhoto,
                    isFavorite = true,
                    )
                )
            }
        }
        cursor.close()
        return carros
    }
    companion object{
        const val  ID_WHEN_NO_CAR = 0
    }


}

