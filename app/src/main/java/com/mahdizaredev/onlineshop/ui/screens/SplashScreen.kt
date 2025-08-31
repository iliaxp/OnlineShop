package com.mahdizaredev.onlineshop.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahdizaredev.onlineshop.MainActivity
import com.mahdizaredev.onlineshop.SplashActivity
import com.mahdizaredev.onlineshop.db.viewmodels.BasketEntityViewModel
import com.mahdizaredev.onlineshop.db.viewmodels.UserEntityViewModel
import com.mahdizaredev.onlineshop.viewmodels.customers.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun SplashScreen(
    splashActivity: SplashActivity,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    var isLoading by remember { mutableStateOf(false)}

    if(!isLoading) {
        userViewModel.getUserInfo { response ->
            if (response.status != "OK") {
                if (response.message!!.lowercase(Locale.ROOT).startsWith("failed to connect to")) {
                    Toast.makeText(
                        splashActivity,
                        "Unable to connect to the server",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    return@getUserInfo
                } else if (response.message!!.lowercase(Locale.ROOT).startsWith("http 417")) {
                    CoroutineScope(Dispatchers.IO).launch {
                        userEntityViewModel.deleteAll()
                    }
                }
            }
            splashActivity.startActivity(Intent(splashActivity, MainActivity::class.java))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "",
            Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        CircularProgressIndicator()
    }
}