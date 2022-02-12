package com.houssem85.toa.login.domain.model

/**
 * A response from any request to login to an external service.
 * @property [authToken] represent the bearer token returned by an external service.
 **/
@Suppress("UnusedPrivateMember")
data class LoginResponse(
    private val authToken: String,
)
