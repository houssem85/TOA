package com.houssem85.toa.login.domain.model

/**
 * A collection of possible results for an attempt to login the user.
 * */
sealed class LoginResult {
    /**
     * The attempt to login was successful.
     * */
    object Success : LoginResult()

    /**
     * This is information about an successful attempt to login.
     * */
    sealed class Failure : LoginResult() {
        /**
         * This will be returned if there was no account matching the requested credentials.
         * */
        object InvalidCredentials : Failure()

        /**
         * This will be returned for any unknown exceptions when attempting to login.
         */
        object Unknown : Failure()

        /**
         * This will be returned when the credentials are empty.
         * */
        data class EmptyCredentials(
            val emptyEmail: Boolean,
            val emptyPassword: Boolean,
        ) : Failure()
    }
}
