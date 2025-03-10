package com.aniruddha81.amphibians

import retrofit2.http.GET

interface ApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}