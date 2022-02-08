package com.houssem85.toa.login.domain.usecase

import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.LoginResult

class SuccessCredentialsLoginUserCase : CredentialsLoginUseCase {
    override suspend fun invoke(credentials: Credentials): LoginResult {
        return LoginResult.Success
    }
}
