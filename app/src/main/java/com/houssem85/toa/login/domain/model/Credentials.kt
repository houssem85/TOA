package com.houssem85.toa.login.domain.model

@JvmInline
value class Email(
    @Suppress("UnusedPrivateMember")
    private val email: String
)

@JvmInline
value class Password(
    @Suppress("UnusedPrivateMember")
    private val password: String
)

/**
 * This data class contains user information's
 * @param[email] user email
 * @param[password] user password
 * */
data class Credentials(
    private val email: Email,
    private val password: Password
)
