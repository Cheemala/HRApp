package com.cheemala.hrapp.domain.usecase

import com.cheemala.hrapp.data.model.Employee
import com.cheemala.hrapp.domain.repository.EmpRepository
import kotlinx.coroutines.flow.Flow

class GetAllEmployeeUseCase(private val empRepository: EmpRepository) {
    fun execute():Flow<List<Employee>> = empRepository.getAllEmployees()
}