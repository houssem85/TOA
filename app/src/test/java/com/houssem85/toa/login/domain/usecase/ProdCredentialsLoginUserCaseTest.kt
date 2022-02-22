package com.houssem85.toa.login.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.fakes.FakeLoginRepository
import com.houssem85.toa.fakes.FakeTokenRepository
import com.houssem85.toa.login.domain.model.AuthToken
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.Email
import com.houssem85.toa.login.domain.model.InvalidCredentialsException
import com.houssem85.toa.login.domain.model.LoginResponse
import com.houssem85.toa.login.domain.model.LoginResult
import com.houssem85.toa.login.domain.model.Password
import com.houssem85.toa.login.domain.model.RefreshToken
import com.houssem85.toa.login.domain.model.Token
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProdCredentialsLoginUserCaseTest {

    private val defaultCredentials = Credentials(
        email = Email("test@toa.com"),
        password = Password("0000")
    )

    private val defaultToken = Token(
        authToken = AuthToken("Auth"),
        refreshToken = RefreshToken("Refresh")
    )

    private lateinit var loginRepository: FakeLoginRepository
    private lateinit var tokenRepository: FakeTokenRepository

    @Before
    fun setUp() {
        loginRepository = FakeLoginRepository()
        tokenRepository = FakeTokenRepository()
    }

    @Test
    fun testSuccessfulLogin() = runBlockingTest {

        val mockResult = Result.Success(
            LoginResponse(
                token = defaultToken
            )
        )

        loginRepository.apply {
            mockLoginWithCredentials(defaultCredentials, mockResult)
        }

        val useCase = ProdCredentialsLoginUserCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )
        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Success)
        tokenRepository.verifyTokenStored(defaultToken)
    }

    @Test
    fun testUnknownFailureLogin() = runBlockingTest {
        val mockResult = Result.Error(
            error = Throwable(
                "Unknown Error"
            )
        )

        loginRepository.apply {
            mockLoginWithCredentials(defaultCredentials, mockResult)
        }

        val useCase = ProdCredentialsLoginUserCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )
        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.Unknown)
        tokenRepository.verifyNoTokenStored()
    }

    @Test
    fun testInvalidCredentialsLogin() = runBlockingTest {
        val mockResult = Result.Error(
            error = InvalidCredentialsException()
        )

        loginRepository.apply {
            mockLoginWithCredentials(defaultCredentials, mockResult)
        }

        val useCase = ProdCredentialsLoginUserCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )
        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.InvalidCredentials)
        tokenRepository.verifyNoTokenStored()
    }

    @Test
    fun testEmptyCredentialsLogin() = runBlockingTest {
        val useCase = ProdCredentialsLoginUserCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )
        val result = useCase(
            credentials = Credentials(
                Email(""),
                Password("")
            )
        )
        assertThat(result).isEqualTo(
            LoginResult.Failure.EmptyCredentials(
                emptyPassword = true,
                emptyEmail = true,
            )
        )
        tokenRepository.verifyNoTokenStored()
        loginRepository.verifyNoLoginCall()
    }
}
