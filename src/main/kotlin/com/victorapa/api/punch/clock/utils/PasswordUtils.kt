package com.victorapa.api.punch.clock.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class PasswordUtils {

    fun generateBCrypt(password: String): String = BCryptPasswordEncoder().encode(password)
}
