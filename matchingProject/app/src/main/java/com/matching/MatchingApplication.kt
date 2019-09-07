package com.matching

import android.app.Application
import io.realm.Realm

class MatchingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // realm setup
        Realm.init(this)
    }
}