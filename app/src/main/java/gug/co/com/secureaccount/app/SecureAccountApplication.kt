package gug.co.com.secureaccount.app

import android.app.Application
import gug.co.com.secureaccount.di.SecureAccountModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SecureAccountApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        // Start Koin
        startKoin {

            // Koin Android logger
            androidLogger()

            //inject Android context
            androidContext(this@SecureAccountApplication)

            // use modules
            modules(SecureAccountModule.appModule)

        }

    }
}