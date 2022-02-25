package com.houssem85.toa.login.data.di

import com.houssem85.toa.login.data.repository.DemoLoginRepository
import com.houssem85.toa.login.data.repository.DemoTokenRepository
import com.houssem85.toa.login.domain.repository.LoginRepository
import com.houssem85.toa.login.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This module is responsible for defining how to create any repository
 * */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTokenRepository(
        tokenRepository: DemoTokenRepository
    ): TokenRepository

    @Binds
    abstract fun bindLoginRepository(
        loginRepository: DemoLoginRepository
    ): LoginRepository
}
