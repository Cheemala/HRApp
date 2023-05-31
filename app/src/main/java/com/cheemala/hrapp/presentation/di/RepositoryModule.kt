package com.cheemala.hrapp.presentation.di

import com.cheemala.hrapp.data.EmpRepositoryImpl
import com.cheemala.hrapp.data.datasource.LocalDataSource
import com.cheemala.hrapp.domain.repository.EmpRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesEmpRepository(localDataSource: LocalDataSource):EmpRepository{
        return EmpRepositoryImpl(localDataSource)
    }

}
