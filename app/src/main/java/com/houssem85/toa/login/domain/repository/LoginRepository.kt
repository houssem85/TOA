package com.houssem85.toa.login.domain.repository

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.LoginResponse

/**
 * The data layer for any requests related to logging in the user.
 * */
interface LoginRepository {
    /**
     * Given some user [credentials] , try to login the user.
     * @return A [Result] that contains the [LoginResponse] if successful , or an error otherwise.
     * */
    suspend fun login(
        credentials: Credentials
    ): Result<LoginResponse>
}
