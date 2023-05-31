package com.cheemala.hrapp.data.db

import androidx.room.*
import com.cheemala.hrapp.data.model.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpDao {
    @Insert
    suspend fun addEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("select * from employee")
    fun getAllEmployees():Flow<List<Employee>>
}