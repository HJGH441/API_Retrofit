package com.example.retrofit.network

import com.example.retrofit.models.ImageRandom
import com.example.retrofit.models.ImagesRaza
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// Endpoints a consumir
interface ApiInterface {
    // Imagen Random
    @GET("breeds/image/random")
    fun imagenAleatoria(): Call<ImageRandom>

    // Muchas Imagenes
    @GET("breed/{raza}/images")
    fun listaImagenesDePerrosPorRaza(@Path("raza") raza: String): Call<ImagesRaza>
    // Si raza = "chihuahua" url seria breed/chihuahua/images

}