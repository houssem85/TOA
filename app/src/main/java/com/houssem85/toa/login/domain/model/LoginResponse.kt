package com.houssem85.toa.login.domain.model

/**
 * A response from any request to login to an external service.
 * @property [token] represent a token returned by an external service.
 **/
@Suppress("UnusedPrivateMember")
data class LoginResponse(
    val token: Token,
)
