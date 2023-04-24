package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.retrofit.models.ImageRandom
import com.example.retrofit.models.ImagesRaza
import com.example.retrofit.network.API
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var URL: TextView
    private lateinit var imgagen: ImageView
    private var spinner1: Spinner? = null
    private var boton1: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarVistas()
        agregarEventos()

        // Función traer imagen aleatoria
        traerImagenAleatoria()
    }

    private fun inicializarVistas(){
        URL = findViewById(R.id.txtURL)
        imgagen = findViewById(R.id.imgRandom)
        boton1 = findViewById(R.id.btnBuscar)
        spinner1 = findViewById(R.id.spBreeds)
    }

    private fun agregarEventos(){
        boton1?.setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        //TODO("Not yet implemented")
        val raza = spinner1?.selectedItem.toString()

        when(p0?.id){
            R.id.btnBuscar -> {
                Log.d("RAZA", "raza es $raza")
                var i = Intent(this, ListActivity::class.java)
                i.putExtra("raza", raza)
                startActivity(i)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_images, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun traerImagenAleatoria(){
        val apiCall = API().crearServicioAPI()

        apiCall.imagenAleatoria().enqueue(object : Callback<ImageRandom> {
            override fun onResponse(call: Call<ImageRandom>, response: Response<ImageRandom>) {
                Log.d("API_PRUEBA", "status es " + response.body()?.status)
                Log.d("API_PRUEBA ", "message es " + response.body()?.message)

                // Aqui hacer lo que necesitemos con los datos
                val status = response.body()?.status
                val message = response.body()?.message

                if(status == "success"){
                    URL.text = message
                    Picasso.get().load(response.body()?.message).into(imgagen)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error Status",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<ImageRandom>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "No fue posible conectar a API",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }
}