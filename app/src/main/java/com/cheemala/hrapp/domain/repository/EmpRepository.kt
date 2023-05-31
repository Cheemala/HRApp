package com.cheemala.hrapp.domain.repository

import com.cheemala.hrapp.data.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmpRepository {
    suspend fun addEmployee(employee: Employee)
    suspend fun updateEmployee(employee: Employee)
    suspend fun deleteEmployee(employee: Employee)
    fun getAllEmployees():Flow<List<Employee>>
    suspend fun searchEmployees(query:String)
}