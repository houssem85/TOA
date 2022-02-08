package com.houssem85.toa.login.domain.model

@Suppress("UnusedPrivateMember")
@JvmInline
value class Email(private val email: String)

@Suppress("UnusedPrivateMember")
@JvmInline
value class Password(private val password: String)

/**
 * This data class contains user information's
 * @param[email] user email
 * @param[password] user password
 * */
data class Credentials(
    private val email : Email,
    private val password: Password
)