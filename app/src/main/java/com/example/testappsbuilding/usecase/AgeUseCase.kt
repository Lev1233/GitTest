package com.example.testappsbuilding.usecase

import com.example.testappsbuilding.data.DataRepository
import kotlinx.coroutines.flow.StateFlow

interface AgeUseCase {
    fun saveAge(age: Int)
    fun getName(): StateFlow<String?>

}
class AgeUseCaseImpl(private  val repository: DataRepository):AgeUseCase {

    override fun saveAge(age: Int) {
     repository.saveAge(age)
    }

    override fun getName(): StateFlow<String?> {
      return  repository.getName()
    }

}