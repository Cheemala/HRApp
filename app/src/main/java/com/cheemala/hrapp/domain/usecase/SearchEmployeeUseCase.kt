package com.cheemala.hrapp.domain.usecase

import com.cheemala.hrapp.domain.repository.EmpRepository

class SearchEmployeeUseCase(private val empRepository: EmpRepository) {
    suspend fun execute(query:String) = empRepository.searchEmployees(query)
}