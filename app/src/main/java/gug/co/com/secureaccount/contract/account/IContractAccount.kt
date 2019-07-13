package gug.co.com.secureaccount.contract.account

import gug.co.com.secureaccountlib.util.Result

interface IContractAccount {

    interface View {

        fun navigateToAttempsList(userName: String)

    }

    interface ViewModel {

        fun createAccount(userName: String, password: String)

        fun validateAccount(userName: String, password: String)

    }

    interface Model {

        suspend fun createAccount(userName: String, password: String): Result<Boolean>

        suspend fun validateAccount(userName: String, password: String): Result<Boolean>

    }

}