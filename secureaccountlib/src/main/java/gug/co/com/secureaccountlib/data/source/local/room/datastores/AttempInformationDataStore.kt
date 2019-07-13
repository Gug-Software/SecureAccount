package gug.co.com.secureaccountlib.data.source.local.room.datastores

import gug.co.com.secureaccountlib.data.source.local.IAttempInformationDataStore
import gug.co.com.secureaccountlib.data.source.local.room.dao.AttempDao
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp
import gug.co.com.secureaccountlib.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AttempInformationDataStore(
    private val attempDao: AttempDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IAttempInformationDataStore {

    override suspend fun getAttempsInfo(
        userName: String
    ): Result<List<DbAttemp>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(attempDao.getAttempsByUserName(userName))
        } catch (e: Exception) {
            Error(e)
        } as Result<List<DbAttemp>>
    }

}