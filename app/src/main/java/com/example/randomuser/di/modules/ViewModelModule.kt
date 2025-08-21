package com.example.randomuser.di.modules

import androidx.lifecycle.ViewModel
import com.example.randomuser.UsersScreenViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UsersScreenViewModel::class)
    fun bindUsersScreenViewModel(viewModel : UsersScreenViewModel) : ViewModel
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value : KClass<out ViewModel>)