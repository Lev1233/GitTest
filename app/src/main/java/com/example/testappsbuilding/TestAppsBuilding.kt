package com.example.testappsbuilding

import android.app.Application
import com.example.testappsbuilding.data.AppContainer
import com.example.testappsbuilding.data.DefaultAppContainer
import java.util.concurrent.TimeUnit

class TestAppsBuilding: Application() {

    lateinit var container: AppContainer


    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)

    }
}