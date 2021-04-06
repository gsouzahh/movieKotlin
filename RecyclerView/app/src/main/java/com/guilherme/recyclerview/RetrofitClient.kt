package com.guilherme.recyclerview

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {


        private lateinit var retrofit: Retrofit

        private val baseUrl = "https://api.themoviedb.org/3/movie/"

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            if (!::retrofit.isInitialized) {

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())//converte o json para class kotlin
                    .build()
            }
            return retrofit
        }

        fun <T> createService(serviceClass: Class<T>): T{
            return getRetrofitInstance().create(serviceClass)
        }
    }
}