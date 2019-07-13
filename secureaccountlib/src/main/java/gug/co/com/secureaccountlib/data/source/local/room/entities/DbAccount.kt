package gug.co.com.secureaccountlib.data.source.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbAccount(

    @PrimaryKey
    val userName: String,

    val password: String

)