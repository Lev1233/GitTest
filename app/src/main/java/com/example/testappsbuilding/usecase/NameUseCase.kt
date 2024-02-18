package com.example.testappsbuilding.usecase

import com.example.testappsbuilding.data.DataRepository

interface NameUseCase {
    fun saveName(name: String)

}
class NameUseCaseImpl(private  val repository: DataRepository):NameUseCase {

    override fun saveName(name: String) {
       repository.saveName(name)
    }


}