package it.pierosilvestri.cmp.firebase

import android.app.Application
import it.pierosilvestri.bookpedia.di.initKoin
import org.koin.android.ext.koin.androidContext

class FirebaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FirebaseApp)
        }
    }
}