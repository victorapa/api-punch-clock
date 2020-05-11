package com.victorapa.api.punch.clock.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Company (
    @Id val id: String? = null,
    val name: String,
    val publicId: String
)
