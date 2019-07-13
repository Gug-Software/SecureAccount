package gug.co.com.secureaccount.utils

import gug.co.com.secureaccount.data.domain.Attemp
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp

fun List<DbAttemp>.asDomainModel(): List<Attemp> {
    return map {
        Attemp(
            id = it.id,
            accountUserName = it.accountUserName,
            result = it.result,
            timeMillis = it.timeMillis
        )
    }
}