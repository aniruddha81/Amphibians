package com.aniruddha81.amphibians

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

interface AppContainer {
    val repository: Repository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()
    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    override val repository: Repository by lazy {
        NetworkAmphibianRepo(apiService = retrofitService)
    }
}