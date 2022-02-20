package com.houssem85.toa.login.ui

import com.houssem85.toa.core.ui.UIText
import com.houssem85.toa.login.domain.model.Credentials

/**
 * This defines the state of login screen
 * @property[credentials] the currents credentials entered by the user.
 * @property[buttonsEnabled] define if the buttons can accept clicks or not.
 * */
sealed class LoginViewState(
    open val credentials: Credentials,
    val buttonsEnabled: Boolean = true,
) {
    /**
     * The initial state of the screen with nothing inputs.
     * */
    object Initial : LoginViewState(
        Credentials(),
        buttonsEnabled = true
    )

    /**
     * The state of the screen when the user begin entering credentials.
     * */
    data class Active(
        override val credentials: Credentials
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
        buttonsEnabled = false
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

    data class InputError(
        override val credentials: Credentials,
        val emailInputErrorMessage: String?,
        val passwordInputErrorMessage: String?
    ) : LoginViewState(
        credentials = credentials
    )
}
