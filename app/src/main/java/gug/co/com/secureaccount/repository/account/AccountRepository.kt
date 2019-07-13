package gug.co.com.secureaccount.repository.account

import gug.co.com.secureaccount.contract.account.IContractAccount
import gug.co.com.secureaccountlib.data.source.local.IAccountDataStore
import gug.co.com.secureaccountlib.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AccountRepository(
    private val accountDataStore: IAccountDataStore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IContractAccount.Model {

    override suspend fun createAccount(
        userName: String,
        password: String
    ): Result<Boolean> {

        return withContext(ioDispatcher) {

            try {
                return@withContext Result.Success(
                    accountDataStore.createAccount(
                        userName,
                        password
                    )
                )
            } catch (e: Exception) {
                return@withContext Result.Error(Exception("Illegal state"))
            }

        }

    }

    override suspend fun validateAccount(
        userName: String,
        password: String
    ): Result<Boolean> {

        return withContext(ioDispatcher) {

            try {
                return@withContext Result.Success(
                    accountDataStore.validateAccount(
                        userName,
                        password
                    )
                )
            } catch (e: Exception) {
                return@withContext Result.Error(Exception("Illegal state"))
            }

        }

    }
}