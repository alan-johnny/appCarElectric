package com.example.eletriccarapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccarapp.R
import com.example.eletriccarapp.data.CarsApi
import com.example.eletriccarapp.data.local.CarRepository
import com.example.eletriccarapp.domain.Carro
import com.example.eletriccarapp.ui.adapter.CarAdapter
import org.json.JSONArray
import org.json.JSONTokener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL

class CarFragment: Fragment() {


    lateinit var lista : RecyclerView
    lateinit var progressBar : ProgressBar
    lateinit var noInternetImg : ImageView
    lateinit var noInternetText : TextView
    lateinit var carApi : CarsApi


    var carrosArray : ArrayList<Carro> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupView(view)

    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)){
           // callService() outra forma de chamar service
            getAllCars()
        }else{
            emptyState()
        }

    }

    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        carApi = retrofit.create(CarsApi::class.java)
    }

    fun getAllCars(){
        carApi.getAllCars().enqueue(object : retrofit2.Callback<List<Carro>> {
            override fun onResponse(call: retrofit2.Call<List<Carro>>, response: retrofit2.Response<List<Carro>>) {
                if (response.isSuccessful) {

                    progressBar.isVisible = false
                    noInternetImg.isVisible = false
                    noInternetText.isVisible = false
                    response.body()?.let {
                        setupList(it)
                    }
                }else{
                    Toast.makeText(context,R.string.error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Carro>>, t: Throwable) {
                Toast.makeText(context,R.string.error,Toast.LENGTH_LONG).show()
                progressBar.isVisible = false
            }
        })
    }

    fun emptyState() {
        progressBar.isVisible = false
        lista.isVisible = false
        noInternetImg.isVisible = true
        noInternetText.isVisible= true
    }

    fun setupView(view: View) {

        lista = view.findViewById(R.id.rv_list_car)
        progressBar = view.findViewById(R.id.pb_loader)
        noInternetImg = view.findViewById(R.id.iv_empty_state)
        noInternetText = view.findViewById(R.id.tv_no_wifi)
    }

    fun setupList(list: List<Carro>) {
        val carAdapter = CarAdapter(list)
        lista.apply {
            adapter = carAdapter
            isVisible = true
        }


        carAdapter.carItemLister = {carro ->
          val isSaved =  CarRepository(requireContext()).save(carro)
        }
    }

    fun callService(){
        val urlBase = "https://igorbag.github.io/cars-api/cars.json"
        MyTask().execute(urlBase)

    }

fun checkForInternet( context: Context?) : Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }else{
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo?:return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }

    }


    // usado para aprendizado
    inner class MyTask: AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.isVisible = true
        }
        @SuppressLint("SuspiciousIndentation")
        override fun doInBackground(vararg urls: String?): String {
          var urlConnection : HttpURLConnection? = null
            try {
                val urlbase = URL(urls[0])
                urlConnection = urlbase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty("accept","application/json")

                val responseCode = urlConnection.responseCode

                if (responseCode == HttpURLConnection.HTTP_OK){

                val response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                publishProgress(response)
                }else{
                    Log.e("Erro", "Erro ao buscar dados da API")
                }
            }catch(ex : Exception) {
                Log.e("Erro", "Erro ao conectar com a API")
            }finally {
                urlConnection?.disconnect()
            }
            return ""
        }
        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray
                for (i in 0 until jsonArray.length()) {

                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("ID ->", id)

                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    Log.d("Preco ->", preco)

                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("Bateria ->", bateria)

                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("Potencia ->", potencia)

                    val recarga = jsonArray.getJSONObject(i).getString("recarga")
                    Log.d("Recarga ->", recarga)

                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("urlPhoto ->", urlPhoto)

                    val model = Carro(
                        id = id.toInt(),
                        preco = preco,
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        urlPhoto = urlPhoto,
                        isFavorite = false
                    )
                    carrosArray.add(model)
                }
                progressBar.isVisible = false
                noInternetImg.isVisible = false
                noInternetText.isVisible = false
                //setupList()

            }catch(ex : Exception) {
                Log.e("Erro", "Erro ao processar dados")
            }
        }
    }

}