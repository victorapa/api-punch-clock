package com.victorapa.api.punch.clock.service

import com.victorapa.api.punch.clock.documents.Employee
import com.victorapa.api.punch.clock.enums.Role
import com.victorapa.api.punch.clock.repository.EmployeeRepository
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.web.server.ResponseStatusException
import java.util.*

@SpringBootTest
internal class EmployeeServiceTest {

    private lateinit var employee: Employee
    
    @Autowired
    private lateinit var employeeService: EmployeeService

    @MockBean
    private lateinit var employeeRepository: EmployeeRepository

    @BeforeEach
    fun setUp() {
        employee = Employee(
                RandomStringUtils.random(5),
                RandomStringUtils.random(5),
                RandomStringUtils.random(5),
                RandomStringUtils.random(5),
                RandomStringUtils.random(5),
                Role.ROLE_USER,
                RandomStringUtils.random(5))
    }

    @Test
    fun `Create with valid user should ret user`() {
        employee = Employee(
                null,
                RandomStringUtils.random(5),
                RandomStringUtils.random(5),
                RandomStringUtils.random(5),
                RandomStringUtils.random(5),
                Role.ROLE_USER,
                RandomStringUtils.random(5))

        val createdEmployee = Employee(
                RandomStringUtils.random(5),
                employee.name,
                employee.email,
                employee.password,
                employee.publicId,
                employee.role,
                employee.companyId)

        given(employeeRepository.save(employee)).willReturn(createdEmployee)

        assertEquals(createdEmployee, employeeService.create(employee))
    }

    @Test
    fun `Find employee with valid public id should return employee`() {
        given(employeeRepository.findByPublicId(employee.publicId)).willReturn(employee)

        val givenEmployee: Employee = employeeService.findByPublicId(employee.publicId)

        assertEquals(employee, givenEmployee)
    }

    @Test
    fun `Find employee with invalid public id should throw entity not found`() {
        val publicId: String  = RandomStringUtils.random(5);

        given(employeeRepository.findByPublicId(publicId)).willReturn(null)

        assertThrows(ResponseStatusException::class.java) {
            employeeService.findByPublicId(publicId)
        }
    }

    @Test
    fun `Find employee with valid email should return employee`() {
        given(employeeRepository.findByEmail(employee.email)).willReturn(employee)

        val givenEmployee: Employee = employeeService.findByEmail(employee.email)

        assertEquals(employee, givenEmployee)
    }

    @Test
    fun `Find employee with invalid email should throw entity not found`() {
        val email: String  = RandomStringUtils.random(5);

        given(employeeRepository.findByEmail(email)).willReturn(null)

        assertThrows(ResponseStatusException::class.java) {
            employeeService.findByEmail(email)
        }
    }

    @Test
    fun `Find employee with valid id should return employee`() {
        val id: String = employee.id!!

        given(employeeRepository.findById(id)).willReturn(Optional.of(employee))

        val givenEmployee: Employee = employeeService.findById(id)

        assertEquals(employee, givenEmployee)
    }

    @Test
    fun `Find employee with invalid id should throw entity not found`() {
        val id: String  = RandomStringUtils.random(5);

        given(employeeRepository.findById(id)).willReturn(Optional.empty())

        assertThrows(ResponseStatusException::class.java) {
            employeeService.findById(id)
        }
    }
}
