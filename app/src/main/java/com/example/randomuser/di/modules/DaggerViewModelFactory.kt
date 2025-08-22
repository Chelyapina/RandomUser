package com.example.randomuser.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class DaggerViewModelFactory @Inject constructor(
    private val viewModelProviders : Map<Class<out ViewModel> ,
            @JvmSuppressWildcards Provider<ViewModel>> ,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        val provider = viewModelProviders[modelClass]
            ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        return provider.get() as T
    }
}