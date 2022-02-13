package com.houssem85.toa.login.domain.model

@JvmInline
value class Email(
    val value: String
)

@JvmInline
value class Password(
    val value: String
)

/**
 * This data class contains user information's
 * @param[email] user email
 * @param[password] user password
 * */
data class Credentials(
    val email: Email = Email(""),
    val password: Password = Password("")
)
