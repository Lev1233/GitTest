package com.example.testappsbuilding.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.testappsbuilding.usecase.NameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Objects



 class UserNameScreenViewModel(private val nameUseCase: NameUseCase): ViewModel() {

     private val _name = MutableStateFlow("")
     val name: StateFlow<String> = _name

     fun setName(value: String) {
         viewModelScope.launch {
             _name.value = value
             nameUseCase.saveName(value)
         }
     }
}