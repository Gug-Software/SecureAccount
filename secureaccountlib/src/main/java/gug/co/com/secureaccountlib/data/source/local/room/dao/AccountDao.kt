package gug.co.com.secureaccountlib.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAccount

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: DbAccount)

    @Query("SELECT * FROM DBACCOUNT WHERE userName = :userName AND password = :password")
    suspend fun getAccountByUserNameAndPassword(
        userName: String,
        password: String
    ): DbAccount?

}