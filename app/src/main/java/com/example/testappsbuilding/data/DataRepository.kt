package com.example.testappsbuilding.data

import kotlinx.coroutines.flow.StateFlow

interface DataRepository {

    fun saveName(name: String)

    fun getName(): StateFlow<String?>

    fun saveAge(age: Int)

    fun getAge(): StateFlow<Int?>
}