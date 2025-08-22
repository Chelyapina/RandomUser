package com.example.randomuser.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.example.randomuser.RandomUserApp
import com.example.randomuser.di.modules.DaggerViewModelFactory
import com.example.randomuser.presentation.navigation.AppNavGraph
import com.example.randomuser.ui.theme.RandomUserTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory : DaggerViewModelFactory

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        (application as RandomUserApp).appComponent.inject(this)

        setContent {
            RandomUserTheme {
                AppNavGraph(
                    viewModelFactory = viewModelFactory ,
                    modifier = Modifier
                )
            }
        }
    }
}
