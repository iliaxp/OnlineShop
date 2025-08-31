package com.mahdizaredev.onlineshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.mahdizaredev.onlineshop.db.viewmodels.UserEntityViewModel
import com.mahdizaredev.onlineshop.ui.screens.SplashScreen
import com.mahdizaredev.onlineshop.ui.theme.OnlineShopTheme
import com.mahdizaredev.onlineshop.utils.ThisApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isLoad by remember { mutableStateOf(false) }
            val userEntityViewModel =
                ViewModelProvider(this).get(UserEntityViewModel::class.java)
            userEntityViewModel.getCurrentUser().observe(this) {
                isLoad = true
                userEntityViewModel.currentUser.value = it
            }
            OnlineShopTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (userEntityViewModel.currentUser.value != null) {
                        ThisApp.token = userEntityViewModel.currentUser.value!!.token!!
                    }
                    if (isLoad)
                        SplashScreen(this, userEntityViewModel)
                }
            }
        }
    }
}