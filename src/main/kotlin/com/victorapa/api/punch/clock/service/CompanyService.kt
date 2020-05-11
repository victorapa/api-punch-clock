package com.victorapa.api.punch.clock.service

import com.victorapa.api.punch.clock.documents.Company
import com.victorapa.api.punch.clock.repository.CompanyRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CompanyService(val companyRepository: CompanyRepository) {

    fun create(company: Company): Company {
        return companyRepository.save(company)
    }

    fun findByPublicId(publicId: String): Company {
        return companyRepository.findByPublicId(publicId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found")
    }
}
