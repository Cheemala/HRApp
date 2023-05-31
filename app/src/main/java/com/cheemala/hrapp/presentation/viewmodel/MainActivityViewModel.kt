package com.cheemala.hrapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cheemala.hrapp.data.model.Employee
import com.cheemala.hrapp.domain.usecase.AddEmployeeUseCase
import com.cheemala.hrapp.domain.usecase.DeleteEmployeeUseCase
import com.cheemala.hrapp.domain.usecase.GetAllEmployeeUseCase
import com.cheemala.hrapp.domain.usecase.UpdateEmployeeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val addEmployeeUseCase: AddEmployeeUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase,
    private val getAllEmployeeUseCase: GetAllEmployeeUseCase
) : ViewModel() {

    init {
        Log.i("MainActivity_","initiated...")
    }

    fun addEmployee(employee: Employee) = viewModelScope.launch(Dispatchers.IO) {
        addEmployeeUseCase.execute(employee)
    }

    fun updateEmployee(employee: Employee) = viewModelScope.launch(Dispatchers.IO) {
        updateEmployeeUseCase.execute(employee)
    }

    fun deleteEmployee(employee: Employee) = viewModelScope.launch(Dispatchers.IO) {
        deleteEmployeeUseCase.execute(employee)
    }

    val allEmployeeDta: LiveData<List<Employee>> = getAllEmployeeUseCase.execute().asLiveData()

    /*fun getAllEmployees() = viewModelScope.launch {
        getAllEmployeeUseCase.execute().collect{

        }
    }*/
    fun searchList(query:String?):List<Employee>{
        val allEmployeeDta = allEmployeeDta.value
        if(query == null)
            return emptyList()
        var filteredEmpList: List<Employee>? =  allEmployeeDta?.filter {
            it.empName!!.contains(query,true)
        }
        return filteredEmpList!!
    }
}