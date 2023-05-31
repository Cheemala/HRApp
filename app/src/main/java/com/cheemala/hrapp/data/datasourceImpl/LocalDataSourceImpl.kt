package com.cheemala.hrapp.data.datasourceImpl

import com.cheemala.hrapp.data.db.EmpDao
import com.cheemala.hrapp.data.model.Employee
import com.cheemala.hrapp.data.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val empDao: EmpDao) : LocalDataSource {
    override suspend fun addEmployee(employee: Employee) {
        empDao.addEmployee(employee)
    }

    override suspend fun updateEmployee(employee: Employee) {
        empDao.updateEmployee(employee)
    }

    override suspend fun deleteEmployee(employee: Employee) {
        empDao.deleteEmployee(employee)
    }

    override fun getAllEmployees(): Flow<List<Employee>> {
        return empDao.getAllEmployees()
    }

}