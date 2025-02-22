package ie.setu.wishfulgames.main

import android.app.Application
import timber.log.Timber

class WishfulGamesMainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Starting WishfulGames Application")
    }
}