package gug.co.com.secureaccount.repository.attemps

import gug.co.com.secureaccount.contract.attemps.IContractAttemps
import gug.co.com.secureaccountlib.data.source.local.IAttempInformationDataStore
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp
import gug.co.com.secureaccountlib.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AttempsRepository(
    private val attempsInformationDataStore: IAttempInformationDataStore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IContractAttemps.Model {

    override suspend fun getAttempsByUserName(
        userName: String
    ): Result<List<DbAttemp>> {

        return withContext(ioDispatcher) {

            try {
                var attemps = attempsInformationDataStore.getAttempsInfo(userName)
                (attemps as? Result.Success).let {
                    return@withContext Result.Success(it!!.data)
                }
            } catch (e: Exception) {
                return@withContext Result.Error(Exception("Illegal state"))
            }

        }

    }
}