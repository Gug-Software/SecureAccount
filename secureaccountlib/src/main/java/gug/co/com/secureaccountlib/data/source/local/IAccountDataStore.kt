package gug.co.com.secureaccountlib.data.source.local

interface IAccountDataStore {

    /**
     * create account in local storage
     */
    fun createAccount(
        userName: String,
        password: String
    )

    /**
     * Validate an account from local storage
     */
    fun validateAccount(
        userName: String,
        password: String
    ): Boolean

}