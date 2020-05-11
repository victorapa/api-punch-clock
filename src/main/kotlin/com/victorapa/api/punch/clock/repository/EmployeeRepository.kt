package com.victorapa.api.punch.clock.repository

import com.victorapa.api.punch.clock.documents.Employee
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository : MongoRepository<Employee, String> {

    fun findByPublicId(publicId: String): Employee?

    fun findByEmail(email: String): Employee?
}
