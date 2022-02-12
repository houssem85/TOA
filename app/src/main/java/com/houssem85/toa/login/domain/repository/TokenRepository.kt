package com.houssem85.toa.login.domain.repository

import com.houssem85.toa.login.domain.model.Token

/**
 * The data layer that care about storing and fetching
 * a user's authentication token
 * */
interface TokenRepository {
    /**
     * Storing a given [token] in local storage.
     * */
    suspend fun storeToken(token: Token)

    /**
     * Fetching the token from local storage.
     * @return The token if it's exist in local storage
     * or null if not.
     * */
    suspend fun fetchToken(): Token?
}
