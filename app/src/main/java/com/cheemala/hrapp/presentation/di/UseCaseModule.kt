package com.cheemala.hrapp.presentation.di

import com.cheemala.hrapp.domain.repository.EmpRepository
import com.cheemala.hrapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun providesAddEmployeeUseCase(empRepository: EmpRepository): AddEmployeeUseCase {
        return AddEmployeeUseCase(empRepository)
    }

    @Singleton
    @Provides
    fun providesDeleteEmployeeUseCase(empRepository: EmpRepository): DeleteEmployeeUseCase {
        return DeleteEmployeeUseCase(empRepository)
    }

    @Singleton
    @Provides
    fun providesGetAllEmployeeUseCase(empRepository: EmpRepository): GetAllEmployeeUseCase {
        return GetAllEmployeeUseCase(empRepository)
    }

    @Singleton
    @Provides
    fun providesUpdateEmployeeUseCase(empRepository: EmpRepository): UpdateEmployeeUseCase {
        return UpdateEmployeeUseCase(empRepository)
    }

    @Singleton
    @Provides
    fun providesSearchEmployeeUseCase(empRepository: EmpRepository): SearchEmployeeUseCase {
        return SearchEmployeeUseCase(empRepository)
    }


}