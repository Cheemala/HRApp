package com.cheemala.hrapp.data

import com.cheemala.hrapp.data.datasource.LocalDataSource
import com.cheemala.hrapp.data.model.Employee
import com.cheemala.hrapp.domain.repository.EmpRepository
import kotlinx.coroutines.flow.Flow

class EmpRepositoryImpl(private val localDataSource: LocalDataSource):EmpRepository {
    override suspend fun addEmployee(employee: Employee) {
        localDataSource.addEmployee(employee)
    }

    override suspend fun updateEmployee(employee: Employee) {
        localDataSource.updateEmployee(employee)
    }

    override suspend fun deleteEmployee(employee: Employee) {
        localDataSource.deleteEmployee(employee)
    }

    override fun getAllEmployees(): Flow<List<Employee>> {
        return localDataSource.getAllEmployees()
    }

    override suspend fun searchEmployees(query: String) {
        TODO("Not yet implemented")
    }
}