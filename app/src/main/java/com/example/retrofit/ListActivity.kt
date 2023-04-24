package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.adapters.RecyclerViewAdapter
import com.example.retrofit.models.Dog
import com.example.retrofit.models.ImageRandom
import com.example.retrofit.models.ImagesRaza
import com.example.retrofit.network.API
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    private var titulor: TextView? = null
    private var Recycler: RecyclerView? = null
    val apiCall = API().crearServicioAPI()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        var raza = intent.getStringExtra("raza")
        Log.d("DATOS:", raza.toString())
        titulor = findViewById(R.id.txtTitleRaza)
        titulor?.setText(raza)
        Toast.makeText(this, "Buscando $raza", Toast.LENGTH_LONG).show()
        Recycler = findViewById(R.id.recyclerviewPerros)
        Recycler?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        GetDogs(raza.toString())
    }

    private fun GetDogs(raza: String){
        apiCall.listaImagenesDePerrosPorRaza("$raza").enqueue(object : Callback<ImagesRaza> {
            // @Override obtiene la respuesta/resultado
            override fun onResponse(call: Call<ImagesRaza>, response: Response<ImagesRaza>) {
                val result = ArrayList<ImageRandom>()
                val dogs = response.body()?.message // Array
                val status = response.body()?.status
                Log.d("PRUEBAS", "Status de la respuesta ${response.body()?.status}")
                if (dogs != null) {
                    for (dog in dogs) {
                        Log.d("PRUEBAS", "Tu perro es ${dog}")

                        // Agregando perro a lista (Modelo = <ImageRandom>)
                        result.add(ImageRandom("$status", "${dog}"))
                    }

                    // Seteando el RecyclerView con el resultado (Lista de perros)
                    val adapter = RecyclerViewAdapter(result)
                    Recycler?.adapter = adapter
                }

            }

            // @Override por si algo sale mal al conectar con la API
            override fun onFailure(call: Call<ImagesRaza>, t: Throwable) {
                Toast.makeText(
                    this@ListActivity,
                    "No fue posible conectar a API",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }
}