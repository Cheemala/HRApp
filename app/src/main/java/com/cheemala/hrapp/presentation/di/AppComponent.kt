package com.cheemala.hrapp.presentation.di

import com.cheemala.hrapp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,DatabaseModule::class,DataSourceModule::class,RepositoryModule::class,UseCaseModule::class,ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}