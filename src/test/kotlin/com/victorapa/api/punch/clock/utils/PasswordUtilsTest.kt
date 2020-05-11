package com.victorapa.api.punch.clock.utils

import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

internal class PasswordUtilsTest {

    private lateinit var rawPassword: String
    private lateinit var bCryptEncoder: BCryptPasswordEncoder

    @BeforeEach
    fun setUp() {
        rawPassword = RandomStringUtils.random(10)
        bCryptEncoder = BCryptPasswordEncoder()
    }

    @Test
    fun `generate password hash should match with raw password value`() {
        val hash = PasswordUtils().generateBCrypt(rawPassword)
        assertTrue(bCryptEncoder.matches(rawPassword, hash))
    }
}
