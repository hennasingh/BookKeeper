package com.geek.bookkeeper

import android.app.Application
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import timber.log.Timber

const val appId = "bookmanagement-rawjd"
lateinit var bookKeeperApp: App

class BookKeeperApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        //RealmLog.setLevel(LogLevel.ALL)

        Timber.plant(Timber.DebugTree())


        bookKeeperApp = App(AppConfiguration.Builder(appId).build())

    }

}

