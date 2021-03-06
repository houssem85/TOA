package com.houssem85.toa.login.ui

import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.login.domain.model.Credentials

/**
 * This defines the state of login screen
 * @property[credentials] the currents credentials entered by the user.
 * @property[inputsEnabled] define if the buttons can accept clicks or not.
 * */
sealed class LoginViewState(
    open val credentials: Credentials,
    val inputsEnabled: Boolean = true,
) {
    /**
     * The initial state of the screen with nothing inputs.
     * */
    object Initial : LoginViewState(
        Credentials(),
        inputsEnabled = true
    )

    /**
     * The state of the screen when the user begin entering credentials.
     * */
    data class Active(
        override val credentials: Credentials,
        val emailInputErrorMessage: UIText? = null,
        val passwordInputErrorMessage: UIText? = null,
    ) : LoginViewState(
        credentials = credentials,
    )

    /**
     * The state of screen when the user click submit button.
     * */
    data class Submitting(
        override val credentials: Credentials
    ) : LoginViewState(
        credentials = credentials,
        inputsEnabled = false
    )

    /**
     * The state of screen when the user has an error after an attempting to login.
     * */
    data class SubmittingError(
        override val credentials: Credentials,
        val errorMessage: UIText
    ) : LoginViewState(
        credentials = credentials,
    )

    /**
     * The state of screen when an attempt of login is successful.
     * */
    object Completed : LoginViewState(
        credentials = Credentials(),
        inputsEnabled = false,
    )
}
