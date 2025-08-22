package com.example.randomuser.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomuser.presentation.oneUser.OneUserScreenViewModel
import com.example.randomuser.presentation.users.UsersScreenViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class DaggerViewModelFactory @Inject constructor(
    private val viewModelProviders : Map<Class<out ViewModel> ,
            @JvmSuppressWildcards Provider<ViewModel>> ,
    private val oneUserScreenViewModelFactory : OneUserScreenViewModel.Factory
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        val creator = viewModelProviders[modelClass]
            ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        return creator.get() as T
    }

    fun createOneUserViewModel(userId : String) : OneUserScreenViewModel {
        return oneUserScreenViewModelFactory.create(userId)
    }

    fun createUsersScreenViewModel() : UsersScreenViewModel {
        return create(UsersScreenViewModel::class.java)
    }
}