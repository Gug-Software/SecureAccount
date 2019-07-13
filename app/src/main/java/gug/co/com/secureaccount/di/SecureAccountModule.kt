package gug.co.com.secureaccount.di

import gug.co.com.secureaccount.contract.account.IContractAccount
import gug.co.com.secureaccount.repository.account.AccountRepository
import gug.co.com.secureaccount.viewmodels.account.AccountViewModel
import gug.co.com.secureaccount.viewmodels.splash.SplashViewModel
import gug.co.com.secureaccountlib.data.source.local.IAccountDataStore
import gug.co.com.secureaccountlib.data.source.local.IAttempInformationDataStore
import gug.co.com.secureaccountlib.data.source.local.room.AccountDataBase
import gug.co.com.secureaccountlib.data.source.local.room.datastores.AccountDataStore
import gug.co.com.secureaccountlib.data.source.local.room.datastores.AttempInformationDataStore
import gug.co.com.secureaccountlib.data.validate.IValidationData
import gug.co.com.secureaccountlib.data.validate.cyxtera.CyxteraValidationData
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class SecureAccountModule {

    companion object {

        val appModule = module {

            //            // Retrofit instance for moviesApi remote
//            single<MoviesAPI> { MoviesRetrofit.moviesApi }
//
            // From Library
            single { AccountDataBase.getDatabase(get()).accountDao() }
            single { AccountDataBase.getDatabase(get()).attempDao() }
            single<IValidationData> { CyxteraValidationData() }
            single<IAccountDataStore> { AccountDataStore(get(), get(), get()) }
            single<IAttempInformationDataStore> { AttempInformationDataStore(get()) }

            single<IContractAccount.Model> { AccountRepository(get()) }

            viewModel { SplashViewModel() }
            viewModel { AccountViewModel(get()) }

        }

    }
}