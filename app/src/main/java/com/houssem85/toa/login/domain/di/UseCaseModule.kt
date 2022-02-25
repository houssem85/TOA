package com.houssem85.toa.login.domain.di

import com.houssem85.toa.login.domain.usecase.CredentialsLoginUseCase
import com.houssem85.toa.login.domain.usecase.ProdCredentialsLoginUserCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This module is responsible for defining how to create any use cases
 * */
@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindCredentialsLoginUseCase(
        credentialsLoginUserCase: ProdCredentialsLoginUserCase
    ): CredentialsLoginUseCase
}
