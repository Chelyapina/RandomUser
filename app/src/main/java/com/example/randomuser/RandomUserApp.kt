package com.example.randomuser

import android.app.Application
import com.example.randomuser.di.AppComponent
import com.example.randomuser.di.DaggerAppComponent

class RandomUserApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}