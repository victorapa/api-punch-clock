package com.victorapa.api.punch.clock.documents

import com.victorapa.api.punch.clock.enums.Role
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Employee (
        @Id val id: String? = null,
        val name: String,
        val email: String,
        val password: String,
        val publicId: String,
        val role: Role,
        val companyId: String
)
