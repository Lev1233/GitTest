package com.example.testappsbuilding.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataRepositoryImpl(context: Context ):DataRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)

    private val _nameFlow = MutableStateFlow(sharedPreferences.getString("name", null))
    private val _ageFlow = MutableStateFlow(sharedPreferences.getInt("age", 0))

    override fun saveName(name: String) {
        sharedPreferences.edit().putString("name", name).apply()
        _nameFlow.value = name
    }

    override fun getName(): StateFlow<String?> {
        return _nameFlow
    }

    override fun saveAge(age: Int) {
        sharedPreferences.edit().putInt("age", age).apply()
        _ageFlow.value = age
    }

    override fun getAge(): StateFlow<Int> {
        return _ageFlow
    }
}