package com.houssem85.toa.fakes

import com.houssem85.toa.core.data.Result
import com.houssem85.toa.login.domain.model.Credentials
import com.houssem85.toa.login.domain.model.LoginResponse
import com.houssem85.toa.login.domain.repository.LoginRepository
import io.mockk.coEvery
import io.mockk.mockk

/**
 * A fake implementation of a [LoginRepository] that wraps all of our mock work.
 * */
class FakeLoginRepository {

    val mock: LoginRepository = mockk()

    fun mockLoginWithCredentials(
        credentials: Credentials,
        result: Result<LoginResponse>
    ) {
        coEvery {
            mock.login(credentials = credentials)
        } returns result
    }
}
