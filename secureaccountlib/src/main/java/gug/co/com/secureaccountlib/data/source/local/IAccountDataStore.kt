package gug.co.com.secureaccountlib.data.source.local

interface IAccountDataStore {

    /**
     * create account in local storage
     */
    suspend fun createAccount(
        userName: String,
        password: String
    ): Boolean

    /**
     * Validate an account from local storage
     */
    suspend fun validateAccount(
        userName: String,
        password: String
    ): Boolean

}