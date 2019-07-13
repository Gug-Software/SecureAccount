package gug.co.com.secureaccountlib.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gug.co.com.secureaccountlib.data.source.local.room.dao.AccountDao
import gug.co.com.secureaccountlib.data.source.local.room.dao.AttempDao
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAccount
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp

const val DB_NAME = "AccountDb"

@Database(

    entities = [
        DbAccount::class,
        DbAttemp::class
    ],

    version = 1

)
abstract class AccountDataBase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun attempDao(): AttempDao

    companion object {

        private lateinit var INSTANCE: AccountDataBase

        fun getDatabase(context: Context): AccountDataBase {

            synchronized(AccountDataBase::class.java) {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AccountDataBase::class.java,
                        DB_NAME
                    ).build()
                }
            }

            return INSTANCE

        }
    }
}