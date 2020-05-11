package com.victorapa.api.punch.clock.service

import com.victorapa.api.punch.clock.documents.Employee
import com.victorapa.api.punch.clock.repository.EmployeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EmployeeService(val employeeRepository: EmployeeRepository) {

    fun create(employee: Employee): Employee =
            employeeRepository.save(employee)

    fun findById(id: String): Employee {
        return employeeRepository.findById(id).orElseThrow {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found")
        }
    }

    fun findByEmail(email: String): Employee =
            employeeRepository.findByEmail(email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found")

    fun findByPublicId(publicId: String): Employee =
            employeeRepository.findByPublicId(publicId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found")
}
