package com.cheemala.hrapp.domain.usecase

import com.cheemala.hrapp.data.model.Employee
import com.cheemala.hrapp.domain.repository.EmpRepository

class UpdateEmployeeUseCase(private val empRepository: EmpRepository) {
    suspend fun execute(employee: Employee) = empRepository.updateEmployee(employee)
}