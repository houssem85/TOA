package com.houssem85.toa.login.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.houssem85.toa.core.data.Result
import com.houssem85.toa.fakes.FakeLoginRepository
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.Email
import com.houssem85.toa.login.domain.model.InvalidCredentialsException
import com.houssem85.toa.login.domain.model.LoginResponse
import com.houssem85.toa.login.domain.model.LoginResult
import com.houssem85.toa.login.domain.model.Password
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ProdCredentialsLoginUserCaseTest {

    @Test
    fun testSuccessfulLogin() = runBlockingTest {
        val inputCredentials = Credentials(
            email = Email("test@toa.com"),
            password = Password("0000")
        )
        val mockResponse = LoginResponse(
            authToken = "Success"
        )
        val mockResult = Result.Success(mockResponse)

        val loginRepository = FakeLoginRepository().apply {
            mockLoginWithCredentials(inputCredentials, mockResult)
        }
        val useCase = ProdCredentialsLoginUserCase(
            loginRepository = loginRepository.mock
        )
        val result = useCase(inputCredentials)
        assertThat(result).isEqualTo(LoginResult.Success)
    }

    @Test
    fun testUnknownFailureLogin() = runBlockingTest {
        val inputCredentials = Credentials(
            email = Email("test@toa.com"),
            password = Password("0000")
        )
        val mockResult = Result.Error(
            error = Throwable(
                "Unknown Error"
            )
        )
        val loginRepository = FakeLoginRepository().apply {
            mockLoginWithCredentials(inputCredentials, mockResult)
        }
        val useCase = ProdCredentialsLoginUserCase(
            loginRepository = loginRepository.mock
        )
        val result = useCase(inputCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.Unknown)
    }

    @Test
    fun testInvalidCredentialsLogin() = runBlockingTest {
        val inputCredentials = Credentials(
            email = Email("test@toa.com"),
            password = Password("1111")
        )
        val mockResult = Result.Error(
            error = InvalidCredentialsException()
        )
        val loginRepository = FakeLoginRepository().apply {
            mockLoginWithCredentials(inputCredentials, mockResult)
        }
        val useCase = ProdCredentialsLoginUserCase(
            loginRepository = loginRepository.mock
        )
        val result = useCase(inputCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.InvalidCredentials)
    }
}
