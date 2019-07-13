package gug.co.com.secureaccountlib.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp

@Dao
interface AttempDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(attemp: DbAttemp)

    @Query("SELECT * FROM DBATTEMP WHERE accountUserName = :userName ")
    suspend fun getAttempsByUserName(
        userName: String
    ): List<DbAttemp>

}