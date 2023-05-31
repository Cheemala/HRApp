package com.cheemala.hrapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheemala.hrapp.domain.usecase.AddEmployeeUseCase
import com.cheemala.hrapp.domain.usecase.DeleteEmployeeUseCase
import com.cheemala.hrapp.domain.usecase.GetAllEmployeeUseCase
import com.cheemala.hrapp.domain.usecase.UpdateEmployeeUseCase

class MainActivityViewModelFactory(
    private val addEmployeeUseCase: AddEmployeeUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase,
    private val getAllEmployeeUseCase: GetAllEmployeeUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(addEmployeeUseCase,updateEmployeeUseCase,deleteEmployeeUseCase,getAllEmployeeUseCase) as T
    }

}