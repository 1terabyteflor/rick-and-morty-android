package com.florgmz.rickandmorty_android.core.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    data class Builder<T>(private var baseUrl: String = "",
                          private var apiService: Class<T>? = null){

        fun setBaseUrl(baseUrl: String) = apply{ this.baseUrl = baseUrl }
        fun setApiService(apiService: Class<T>) = apply{ this.apiService = apiService }

        fun build(): T{
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(apiService)
        }
    }
}