package com.example.randomuser.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.randomuser.RandomUserApp
import com.example.randomuser.di.modules.DaggerViewModelFactory
import com.example.randomuser.presentation.navigation.AppNavGraph
import com.example.randomuser.presentation.navigation.Navigator
import com.example.randomuser.ui.theme.RandomUserTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory : DaggerViewModelFactory

    @Inject
    lateinit var navigator : Navigator

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        (application as RandomUserApp).appComponent.inject(this)

        setContent {
            RandomUserTheme {
                AppNavGraph(
                    viewModelFactory = viewModelFactory ,
                    navigator = navigator
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.unbind()
    }
}
