package gug.co.com.secureaccountlib.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import gug.co.com.secureaccountlib.MainCoroutineRule
import gug.co.com.secureaccountlib.data.source.local.room.AccountDataBase
import gug.co.com.secureaccountlib.data.source.local.room.dao.AccountDao
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAccount
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AccountDaoTest {

    private lateinit var database: AccountDataBase
    private lateinit var accountDao: AccountDao

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AccountDataBase::class.java
        ).allowMainThreadQueries().build()

        accountDao= database.accountDao()

    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertAccountAndGet() = runBlockingTest {

        val dbAccount = DbAccount(
            "jcmodev12@gmail.com",
            "JkVillavo12-Col"
        )
        // GIVEN - insert a DbAccount
        accountDao.insert(dbAccount)

        // WHEN - Get the DbAccount by username and password
        val loaded = accountDao.getAccountByUserNameAndPassword(
            "jcmodev12@gmail.com",
            "JkVillavo12-Col"
        )

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat<DbAccount>(loaded as DbAccount, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded.userName, CoreMatchers.`is`(dbAccount.userName))
        MatcherAssert.assertThat(loaded.password, CoreMatchers.`is`(dbAccount.password))
    }
}