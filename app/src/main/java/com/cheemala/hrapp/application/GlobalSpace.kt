package com.cheemala.hrapp.application

import android.app.Application
import com.cheemala.hrapp.presentation.di.AppComponent
import com.cheemala.hrapp.presentation.di.AppModule
import com.cheemala.hrapp.presentation.di.DaggerAppComponent

class GlobalSpace : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
    }

    fun getAppComponentInstance(): AppComponent = appComponent

}