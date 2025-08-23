package com.example.randomuser.di.modules

import android.app.Application
import android.content.Context
import com.example.randomuser.presentation.utils.AndroidIntentLauncher
import com.example.randomuser.presentation.utils.IntentLauncher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application : Application) : Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideIntentLauncher(
        context : Context ,
    ) : IntentLauncher {
        return AndroidIntentLauncher(context)
    }
}