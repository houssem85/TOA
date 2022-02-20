package com.houssem85.toa.fakes

import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.LoginResult
import com.houssem85.toa.login.domain.usecase.CredentialsLoginUseCase
import io.mockk.coEvery
import io.mockk.mockk

/**
 * A fake implementation of a [CredentialsLoginUseCase] that wraps all of our mock work.
 * */
class FakeCredentialsLoginUseCase {

    val mock: CredentialsLoginUseCase = mockk()

    fun mockLoginResultForCredentials(credentials: Credentials, loginResult: LoginResult) {
        coEvery {
            mock(credentials)
        } returns loginResult
    }
}
