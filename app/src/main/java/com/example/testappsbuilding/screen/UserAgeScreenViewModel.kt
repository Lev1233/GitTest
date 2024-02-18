package com.example.testappsbuilding.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappsbuilding.usecase.AgeUseCase
import com.example.testappsbuilding.usecase.NameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserAgeScreenViewModel(private val ageUseCase: AgeUseCase):ViewModel() {

    private val _age = MutableStateFlow(0)
    val age: StateFlow<Int> = _age

    fun setAge(value: Int) {
        viewModelScope.launch {
            _age.value = value
            ageUseCase.saveAge(value)
        }

    }
    fun getName(): StateFlow<String?> {
        return ageUseCase.getName()
    }
}
