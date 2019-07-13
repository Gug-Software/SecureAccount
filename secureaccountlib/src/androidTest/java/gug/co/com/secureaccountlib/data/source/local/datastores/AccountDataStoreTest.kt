package gug.co.com.secureaccountlib.data.source.local.datastores

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import gug.co.com.secureaccountlib.MainCoroutineRule
import gug.co.com.secureaccountlib.data.source.local.IAccountDataStore
import gug.co.com.secureaccountlib.data.source.local.room.AccountDataBase
import gug.co.com.secureaccountlib.data.source.local.room.datastores.AccountDataStore
import gug.co.com.secureaccountlib.data.validate.IValidationData
import gug.co.com.secureaccountlib.data.validate.cyxtera.CyxteraValidationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.*

class AccountDataStoreTest {

    private lateinit var validationData: IValidationData
    private lateinit var accountDataStoreTest: IAccountDataStore
    private lateinit var database: AccountDataBase

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        // using an in-memory database for testing, since it doesn't survive killing the process
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AccountDataBase::class.java
        ).allowMainThreadQueries().build()

        validationData = CyxteraValidationData()
        accountDataStoreTest = AccountDataStore(
            database.accountDao(),
            database.attempDao(),
            validationData,
            Dispatchers.Main
        )
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun createAccount_wrongMail() = runBlockingTest {

        // GIVEN - an username and password
        val userName = "userName"
        val password = "password"

        // WHEN  - create account
        val result = accountDataStoreTest.createAccount(userName, password)

        // THEN - is failure
        Assert.assertThat(result, CoreMatchers.`is`(false))
    }

    @Test
    fun createAccount_wrongPassword() = runBlockingTest {

        // GIVEN - an username and password
        val userName = "jcmodev12@gmail.com"
        val password = "password"

        // WHEN  - create account
        val result = accountDataStoreTest.createAccount(userName, password)

        // THEN - is failure
        Assert.assertThat(result, CoreMatchers.`is`(false))
    }

    @Test
    fun createAccount_veryWell() = runBlockingTest {

        // GIVEN - an username and password
        val userName = "jcmodev12@gmail.com"
        val password = "Cyxtera-2019"

        // WHEN  - create account
        val result = accountDataStoreTest.createAccount(userName, password)

        // THEN - is failure
        Assert.assertThat(result, CoreMatchers.`is`(true))
    }

}
