package com.victorapa.api.punch.clock.repository

import com.victorapa.api.punch.clock.documents.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository : MongoRepository<Company, String> {

    fun findByPublicId(publicId: String): Company?
}
