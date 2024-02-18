package com.example.testappsbuilding

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.testappsbuilding.screen.UserAgeScreenViewModel
import com.example.testappsbuilding.screen.UserNameScreenViewModel
import com.example.testappsbuilding.screen.UserProfileScreenViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
         UserNameScreenViewModel(
             testAppsBuilding().container.nameUseCase
         )
        }
        initializer {
          UserAgeScreenViewModel(
              testAppsBuilding().container.ageUseCase
          )
        }

        initializer {
            UserProfileScreenViewModel(
                testAppsBuilding().container.userProfileUseCase,
                testAppsBuilding().container.marsPhotosRepository
            )
        }
    }

}
fun CreationExtras.testAppsBuilding(): TestAppsBuilding =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TestAppsBuilding)