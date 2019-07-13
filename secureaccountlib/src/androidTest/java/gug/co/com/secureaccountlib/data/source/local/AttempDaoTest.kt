package gug.co.com.secureaccountlib.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import gug.co.com.secureaccountlib.MainCoroutineRule
import gug.co.com.secureaccountlib.data.source.local.room.AccountDataBase
import gug.co.com.secureaccountlib.data.source.local.room.dao.AttempDao
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp
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
class AttempDaoTest {

    private lateinit var database: AccountDataBase
    private lateinit var attempDao: AttempDao

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

        attempDao = database.attempDao()

    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertAccountAndGet() = runBlockingTest {

        val dbAttemp = DbAttemp(
            1,
            "jcmodev12@gmail.com",
            true, 1000L
        )

        // GIVEN - insert a DbAttemps
        attempDao.insert(dbAttemp)

        // WHEN - Get the DbAttemp by username
        val loaded = attempDao.getAttempsByUserName(
            dbAttemp.accountUserName
        )

        // THEN - The loaded data contains the expected values
        MatcherAssert.assertThat(loaded.size, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(loaded[0].timeMillis, CoreMatchers.`is`(dbAttemp.timeMillis))
        MatcherAssert.assertThat(loaded[0].result, CoreMatchers.`is`(dbAttemp.result))
    }
}