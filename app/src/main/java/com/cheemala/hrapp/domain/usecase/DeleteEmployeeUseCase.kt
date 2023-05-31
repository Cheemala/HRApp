package com.cheemala.hrapp.domain.usecase

import com.cheemala.hrapp.data.model.Employee
import com.cheemala.hrapp.domain.repository.EmpRepository

class DeleteEmployeeUseCase(private val empRepository: EmpRepository) {
    suspend fun execute(employee: Employee) = empRepository.deleteEmployee(employee)
}