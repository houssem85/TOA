package com.houssem85.toa.fakes

import com.houssem85.toa.login.domain.usecase.CredentialsLoginUseCase
import io.mockk.mockk

/**
 * A fake implementation of a [CredentialsLoginUseCase] that wraps all of our mock work.
 * */
class FakeCredentialsLoginUseCase {

    val mock: CredentialsLoginUseCase = mockk()
}
