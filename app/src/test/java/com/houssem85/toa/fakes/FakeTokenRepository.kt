package com.houssem85.toa.fakes

import com.houssem85.toa.login.domain.model.Token
import com.houssem85.toa.login.domain.repository.TokenRepository
import io.mockk.called
import io.mockk.coVerify
import io.mockk.mockk

class FakeTokenRepository {

    val mock: TokenRepository = mockk(
        relaxUnitFun = true
    )

    fun verifyTokenStored(token: Token) {
        coVerify {
            mock.storeToken(token = token)
        }
    }

    fun verifyNoTokenStored() {
        coVerify {
            mock wasNot called
        }
    }
}
