package gug.co.com.secureaccount.contract.attemps

import gug.co.com.secureaccount.data.domain.Attemp
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp
import gug.co.com.secureaccountlib.util.Result

interface IContractAttemps {

    interface View {

    }

    interface ViewModel {

        fun loadAttemps(userName: String)

    }

    interface Model {

        suspend fun getAttempsByUserName(userName: String): Result<List<DbAttemp>>
    }

}