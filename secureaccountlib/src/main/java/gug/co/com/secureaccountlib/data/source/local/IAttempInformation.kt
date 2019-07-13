package gug.co.com.secureaccountlib.data.source.local

interface IAttempInformation {

    /**
     * get attemps information from datalocal for an account
     */
    fun getAttempsInfo(
        userName: String
    )


}