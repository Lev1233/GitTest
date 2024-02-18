package com.example.testappsbuilding.usecase

import com.example.testappsbuilding.data.DataRepository
import kotlinx.coroutines.flow.StateFlow

interface UserProfileUseCase{
     fun getName(): StateFlow<String?>
     fun getAge(): StateFlow<Int?>
 }
class UserProfileUseCaseImpl(private val repository: DataRepository):UserProfileUseCase {

    override fun getName(): StateFlow<String?> {
        return repository.getName()
    }

    override fun getAge(): StateFlow<Int?> {
        return repository.getAge()
    }
}