package com.cheemala.hrapp.presentation.di

import com.cheemala.hrapp.domain.usecase.AddEmployeeUseCase
import com.cheemala.hrapp.domain.usecase.DeleteEmployeeUseCase
import com.cheemala.hrapp.domain.usecase.GetAllEmployeeUseCase
import com.cheemala.hrapp.domain.usecase.UpdateEmployeeUseCase
import com.cheemala.hrapp.presentation.viewmodel.MainActivityViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providesMainActivityViewModelFactory(
        addEmployeeUseCase: AddEmployeeUseCase,
        updateEmployeeUseCase: UpdateEmployeeUseCase,
        deleteEmployeeUseCase: DeleteEmployeeUseCase,
        getAllEmployeeUseCase: GetAllEmployeeUseCase
    ): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(addEmployeeUseCase,updateEmployeeUseCase,deleteEmployeeUseCase,getAllEmployeeUseCase)
    }

}