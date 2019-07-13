package gug.co.com.secureaccountlib.data.validate

interface IValidationData {

    /**
     * Validate the username structure
     * @param userName the username
     * @return true if matches
     */
    fun validateUserName(
        userName: String
    ): Boolean

    /**
     * Validate the password structure or data
     * @param password the password
     * @return true if matches
     */
    fun validatePassword(
        password: String
    ): Boolean

}