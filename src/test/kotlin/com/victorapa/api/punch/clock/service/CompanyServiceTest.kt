package com.victorapa.api.punch.clock.service

import com.victorapa.api.punch.clock.documents.Company
import com.victorapa.api.punch.clock.repository.CompanyRepository
import org.apache.commons.lang3.RandomStringUtils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.web.server.ResponseStatusException

@SpringBootTest
internal class CompanyServiceTest {

    private lateinit var company: Company

    @Autowired
    private lateinit var companyService: CompanyService

    @MockBean
    private lateinit var companyRepository: CompanyRepository

    @BeforeEach
    fun setUp() {
        company = Company(RandomStringUtils.random(5),
                RandomStringUtils.random(10),
                RandomStringUtils.random(15))
    }

    @Test
    fun `Find company with valid public id should return company`() {
        given(companyRepository.findByPublicId(company.publicId)).willReturn(company)

        val givenCompany: Company = companyService.findByPublicId(company.publicId)

        assertEquals(company, givenCompany)
    }

    @Test
    fun `Find company with invalid public id should throw entity not found`() {
        val publicId: String  = RandomStringUtils.random(5);

        given(companyRepository.findByPublicId(publicId)).willReturn(null)

        assertThrows(ResponseStatusException::class.java) {
            companyService.findByPublicId(publicId)
        }
    }

    @Test
    fun `Create company should return created company`() {
        val name: String  = RandomStringUtils.random(10)
        val publicId: String  = RandomStringUtils.random(15)
        val company = Company(null, name, publicId)

        given(companyRepository.save(company)).willReturn(Company(RandomStringUtils.random(5), name, publicId))

        val createdCompany: Company = companyService.create(company)

        assertNotNull(createdCompany.id)
    }
}
