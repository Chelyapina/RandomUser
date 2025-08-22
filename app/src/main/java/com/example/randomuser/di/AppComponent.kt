package com.example.randomuser.di

import android.app.Application
import com.example.randomuser.di.modules.AppModule
import com.example.randomuser.di.modules.DaggerViewModelFactory
import com.example.randomuser.di.modules.DataModule
import com.example.randomuser.di.modules.ViewModelModule
import com.example.randomuser.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class ,
        DataModule::class ,
        ViewModelModule::class ,
    ]
)
interface AppComponent {
    fun inject(activity : MainActivity)
    fun getViewModelFactory() : DaggerViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application : Application
        ) : AppComponent
    }
}