package com.houssem85.toa.login.domain.model

@JvmInline
value class AuthToken(private val value: String)

@JvmInline
value class RefreshToken(private val value: String)

/**
 * Contains the information about auth token.
 * @property[authToken] The current token used to validate the user request.
 * @property[refreshToken] A token used to generate a new [authToken] is the current one is expired.
 * */
data class Token(
    val authToken: AuthToken,
    val refreshToken: RefreshToken,
)
