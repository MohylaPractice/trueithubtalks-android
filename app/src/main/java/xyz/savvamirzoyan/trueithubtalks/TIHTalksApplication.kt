package xyz.savvamirzoyan.trueithubtalks

import android.app.Application
import timber.log.Timber

class TIHTalksApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}