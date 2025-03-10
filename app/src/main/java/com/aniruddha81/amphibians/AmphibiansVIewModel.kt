package com.aniruddha81.amphibians

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil3.network.HttpException
import kotlinx.coroutines.launch
import okio.IOException

sealed interface AmphibiansUiState {
    data object Loading : AmphibiansUiState
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    data object Error : AmphibiansUiState
}

class AmphibiansViewModel(private val repository: Repository) : ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(
        AmphibiansUiState.Loading
    )
        private set


    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = try {
                AmphibiansUiState.Success(repository.getAmphibians())
            } catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepo = application.container.repository
                AmphibiansViewModel(repository = amphibiansRepo)
            }
        }
    }

}