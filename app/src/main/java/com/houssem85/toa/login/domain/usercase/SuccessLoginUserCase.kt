package com.houssem85.toa.login.domain.usercase

import com.houssem85.toa.login.domain.model.LoginResult

class SuccessLoginUserCase : LoginUseCase {
    override suspend fun invoke(email: Email, password: Password): LoginResult {
        return LoginResult.Success
    }
}
