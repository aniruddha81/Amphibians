package com.aniruddha81.amphibians

interface Repository {
    //only the list
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibianRepo(private val apiService: ApiService) : Repository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return apiService.getAmphibians()
    }
}