package gug.co.com.secureaccountlib.data.source.local

import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp
import gug.co.com.secureaccountlib.util.Result

interface IAttempInformationDataStore {

    /**
     * get attemps information from datalocal for an account
     */
    suspend fun getAttempsInfo(
        userName: String
    ): Result<List<DbAttemp>>


}