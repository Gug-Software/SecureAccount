package gug.co.com.secureaccountlib.data.validate.cyxtera

import gug.co.com.secureaccountlib.data.validate.IValidationData
import java.util.regex.Pattern


class CyxteraValidationData : IValidationData {

    override fun validateUserName(
        userName: String
    ): Boolean {
        return isEmailValid(userName)
    }

    override fun validatePassword(
        password: String
    ): Boolean {
        return isPasswordValid(password)
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {

        /*
            Tomado de:
            https://riptutorial.com/regex/example/18996/a-password-containing-at-least-1-uppercase--1-lowercase--1-digit--1-special-character-and-have-a-length-of-at-least-of-10
         */
        return Pattern.compile(
            "^(?=.{8,}\$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*\$"
        ).matcher(password).matches()
    }

}