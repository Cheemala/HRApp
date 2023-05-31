package com.cheemala.hrapp.data.datasource

import com.cheemala.hrapp.data.model.Employee
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addEmployee(employee: Employee)
    suspend fun updateEmployee(employee: Employee)
    suspend fun deleteEmployee(employee: Employee)
    fun getAllEmployees(): Flow<List<Employee>>
}