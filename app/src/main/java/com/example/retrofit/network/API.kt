package com.example.retrofit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    // Creando conexión con API
    private val URL_BASE = "https://dog.ceo/api/"

    fun crearServicioAPI(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: ApiInterface = retrofit.create(ApiInterface::class.java)
        return service
    }

}