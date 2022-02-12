package com.houssem85.toa.login.domain.usecase

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.InvalidCredentialsException
import com.houssem85.toa.login.domain.model.LoginResult
import com.houssem85.toa.login.domain.repository.LoginRepository
import com.houssem85.toa.login.domain.repository.TokenRepository

/**
 * That is a concrete implementation of [CredentialsLoginUseCase] that will request logging in via
 * the [LoginRepository].
 * @property [loginRepository] the data layer that group login request methods.
 * @property [tokenRepository] the data layer that care about storing and fetching token.
 * */
class ProdCredentialsLoginUserCase(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository,
) : CredentialsLoginUseCase {
    override suspend fun invoke(credentials: Credentials): LoginResult {
        val resultLoginResponse = loginRepository.login(credentials = credentials)
        return when (resultLoginResponse) {
            is Result.Error -> {
                loginResultForFailure(resultLoginResponse)
            }
            is Result.Success -> {
                tokenRepository.storeToken(resultLoginResponse.data.token)
                LoginResult.Success
            }
        }
    }

    /**
     * Checks the possible error scenario for the [resultLoginResponse]
     * and maps to an appropriate [LoginResult]
     * */
    private fun loginResultForFailure(resultLoginResponse: Result.Error) =
        when (resultLoginResponse.error) {
            is InvalidCredentialsException -> {
                LoginResult.Failure.InvalidCredentials
            }
            else -> {
                LoginResult.Failure.Unknown
            }
        }
}
