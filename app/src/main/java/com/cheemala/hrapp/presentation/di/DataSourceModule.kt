package com.cheemala.hrapp.presentation.di

import com.cheemala.hrapp.data.datasource.LocalDataSource
import com.cheemala.hrapp.data.datasourceImpl.LocalDataSourceImpl
import com.cheemala.hrapp.data.db.EmpDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(empDao: EmpDao): LocalDataSource{
        return LocalDataSourceImpl(empDao)
    }

}