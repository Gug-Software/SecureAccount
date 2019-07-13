package gug.co.com.secureaccountlib.data.source.local.room.datastores

import gug.co.com.secureaccountlib.data.source.local.IAccountDataStore
import gug.co.com.secureaccountlib.data.source.local.room.dao.AccountDao
import gug.co.com.secureaccountlib.data.source.local.room.dao.AttempDao
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAccount
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp
import gug.co.com.secureaccountlib.data.validate.IValidationData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountDataStore(
    private val accountDao: AccountDao,
    private val attempDao: AttempDao,
    private val iValidationData: IValidationData,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IAccountDataStore {

    override suspend fun createAccount(
        userName: String,
        password: String
    ): Boolean = withContext(ioDispatcher) {
        return@withContext try {

            val valideUserName = iValidationData.validateUserName(userName)
            val validePassword = iValidationData.validatePassword(password)
            if (valideUserName && validePassword) {
                accountDao.insert(DbAccount(userName, password))
                true
            } else {
                false
            }

        } catch (e: Exception) {
            Error(e)
        } as Boolean
    }

    override suspend fun validateAccount(
        userName: String,
        password: String
    ): Boolean = withContext(ioDispatcher) {
        return@withContext try {

            val validate = accountDao.getAccountByUserNameAndPassword(
                userName, password
            )

            if (validate != null) {
                attempDao.insert(
                    DbAttemp.createAttemp(userName, true)
                )
                true
            } else {
                attempDao.insert(
                    DbAttemp.createAttemp(userName, false)
                )
                false
            }

        } catch (e: Exception) {
            Error(e)
        }
    } as Boolean

}