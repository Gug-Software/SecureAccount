package gug.co.com.secureaccountlib.data.source.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbAttemp(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val accountUserName: String,
    val result: Boolean,
    val timeMillis: Long

)