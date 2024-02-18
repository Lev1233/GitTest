package com.example.testappsbuilding.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappsbuilding.data.MarsPhotosRepository

import com.example.testappsbuilding.model.MarsPhoto

import com.example.testappsbuilding.usecase.UserProfileUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException



sealed interface MarsUiState {
    data class Success(val photo: MarsPhoto?) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class UserProfileScreenViewModel(
    private val userProfileUseCase: UserProfileUseCase,
      private val marsPhotosRepository: MarsPhotosRepository
): ViewModel() {


    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set



    init {
        getMarsPhoto()
    }

    fun getName(): StateFlow<String?> {
        return userProfileUseCase.getName()
    }

    fun getAge(): StateFlow<Int?> {
        return userProfileUseCase.getAge()
    }

    fun getMarsPhoto() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                MarsUiState.Success(marsPhotosRepository.getRandomMarsPhoto())
            } catch (e: IOException) {

                MarsUiState.Error
            } catch (e: HttpException) {

                MarsUiState.Error
            }
        }
    }

}