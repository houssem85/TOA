package com.houssem85.toa.login.data.repository

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.login.domain.model.AuthToken
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.LoginResponse
import com.houssem85.toa.login.domain.model.RefreshToken
import com.houssem85.toa.login.domain.model.Token
import com.houssem85.toa.login.domain.repository.LoginRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * This is a sample [LoginRepository] that does not interact with any real data source , but allows
 * us to quickly modify return values for manual testing sake.
 */
class DemoLoginRepository @Inject constructor() : LoginRepository {
    override suspend fun login(credentials: Credentials): Result<LoginResponse> {
        delay(5000)
        return Result.Success(
            data = LoginResponse(
                token = Token(
                    authToken = AuthToken(
                        value = "fake authToken"
                    ),
                    refreshToken = RefreshToken(
                        value = "fake refreshToken"
                    )
                )
            )
        )
    }
}
