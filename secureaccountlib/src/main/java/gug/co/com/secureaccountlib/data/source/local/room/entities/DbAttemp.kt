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

) {

    companion object {

        fun createAttemp(userName: String, result: Boolean): DbAttemp =
            DbAttemp(0, userName, result, System.currentTimeMillis())
    }

}



