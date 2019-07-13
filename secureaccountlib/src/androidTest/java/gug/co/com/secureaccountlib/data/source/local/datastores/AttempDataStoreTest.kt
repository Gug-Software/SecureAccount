package gug.co.com.secureaccountlib.data.source.local.datastores

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import gug.co.com.secureaccountlib.MainCoroutineRule
import gug.co.com.secureaccountlib.data.source.local.room.AccountDataBase
import gug.co.com.secureaccountlib.data.source.local.room.datastores.AccountDataStore
import gug.co.com.secureaccountlib.data.source.local.room.datastores.AttempInformationDataStore
import gug.co.com.secureaccountlib.data.source.local.room.entities.DbAttemp
import gug.co.com.secureaccountlib.data.validate.IValidationData
import gug.co.com.secureaccountlib.data.validate.cyxtera.CyxteraValidationData
import gug.co.com.secureaccountlib.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.*

class AttempDataStoreTest {

    private lateinit var validationData: IValidationData
    private lateinit var attempInformationDataStore: AttempInformationDataStore
    private lateinit var accountDataStore: AccountDataStore
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

        validationData = CyxteraValidationData()

        // using an in-memory database for testing, since it doesn't survive killing the process
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AccountDataBase::class.java
        ).allowMainThreadQueries().build()

        attempInformationDataStore = AttempInformationDataStore(
            database.attempDao(),
            Dispatchers.Main
        )

        accountDataStore = AccountDataStore(
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
    fun createAccount_andValidateWrong() = runBlockingTest {

        // GIVEN - an username and password
        val userName = "jcmodev12@gmail.com"
        val password = "Cyxtera-2019"
        val wrongPassword = "elmalo"

        // WHEN  - create account and validate 3 times wrong
        accountDataStore.createAccount(userName, password)
        accountDataStore.validateAccount(userName, wrongPassword)
        accountDataStore.validateAccount(userName, wrongPassword)
        accountDataStore.validateAccount(userName, wrongPassword)
        val result = attempInformationDataStore.getAttempsInfo(userName) as Result.Success<List<DbAttemp>>

        val tasks = result.data
        Assert.assertThat(tasks.size, CoreMatchers.`is`(3))
        Assert.assertThat(tasks[0].result, CoreMatchers.`is`(false))
    }

    @Test
    fun createAccount_andValidateGood() = runBlockingTest {

        // GIVEN - an username and password
        val userName = "jcmodev12@gmail.com"
        val password = "Cyxtera-2019"
        val wrongPassword = "Cyxtera-2019"

        // WHEN  - create account and validate 1 Good
        accountDataStore.createAccount(userName, password)
        accountDataStore.validateAccount(userName, wrongPassword)
        accountDataStore.validateAccount(userName, wrongPassword)
        accountDataStore.validateAccount(userName, wrongPassword)
        val result = attempInformationDataStore.getAttempsInfo(userName) as Result.Success<List<DbAttemp>>

        val tasks = result.data
        Assert.assertThat(tasks.size, CoreMatchers.`is`(3))
        Assert.assertThat(tasks[0].result, CoreMatchers.`is`(true))
    }

    @Test
    fun createAccount_andValidate() = runBlockingTest {

        // GIVEN - an username and password
        val userName = "jcmodev12@gmail.com"
        val password = "Cyxtera-2019"
        val wrongPassword = "elmalo"
        val goodPassword = "Cyxtera-2019"

        // WHEN  - create account and validate 1 Good
        accountDataStore.createAccount(userName, password)
        accountDataStore.validateAccount(userName, wrongPassword)
        accountDataStore.validateAccount(userName, goodPassword)
        accountDataStore.validateAccount(userName, wrongPassword)
        accountDataStore.validateAccount(userName, goodPassword)
        accountDataStore.validateAccount(userName, wrongPassword)
        accountDataStore.validateAccount(userName, goodPassword)
        accountDataStore.validateAccount(userName, wrongPassword)
        accountDataStore.validateAccount(userName, goodPassword)
        accountDataStore.validateAccount(userName, wrongPassword)
        accountDataStore.validateAccount(userName, goodPassword)
        val result = attempInformationDataStore.getAttempsInfo(userName) as Result.Success<List<DbAttemp>>

        val tasks = result.data
        Assert.assertThat(tasks.size, CoreMatchers.`is`(10))
    }


}
