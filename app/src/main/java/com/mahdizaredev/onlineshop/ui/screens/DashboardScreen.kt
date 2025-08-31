package com.mahdizaredev.onlineshop.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mahdizaredev.onlineshop.db.viewmodels.UserEntityViewModel
import com.mahdizaredev.onlineshop.ui.components.AdvancedButton
import com.mahdizaredev.onlineshop.ui.theme.Aqua
import com.mahdizaredev.onlineshop.ui.theme.LightBlue
import com.mahdizaredev.onlineshop.ui.theme.Orange
import com.mahdizaredev.onlineshop.ui.theme.Red
import com.mahdizaredev.onlineshop.utils.ThisApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(navController: NavHostController, userEntityViewModel: UserEntityViewModel) {

    val currentUser by remember { mutableStateOf(userEntityViewModel.currentUser) }

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(
                Modifier
                    .width(5.dp)
            )
            Column {
                Text(
                    "User Profile",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(0.dp, 9.dp, 0.dp, 0.dp)
                )
                Spacer(
                    Modifier
                        .width(5.dp)
                )
            }
        }

        Row(Modifier.padding(20.dp)) {
            Card(
                modifier = Modifier.padding(0.dp),
                shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray // یا هر رنگ دلخواه مثل Color(0xFF...),
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "",
                    Modifier.size(70.dp),
                    tint = Color.White
                )
            }
            Spacer(
                Modifier
                    .width(15.dp)
            )
            Column(Modifier.weight(1f)) {
                Text(
                    "${currentUser.value!!.firstName} ${currentUser.value!!.lastName}",
                    fontSize = 22.sp
                )
                Spacer(Modifier.height(5.dp))
                Text("${currentUser.value!!.username}", fontSize = 18.sp, color = Color.DarkGray)
            }
            IconButton(onClick = {
                ThisApp.token = currentUser.value!!.token!!
                navController.navigate("editProfile")
            }) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = "")
            }
        }
        val LightBlueCard = CardDefaults.cardColors(containerColor = Color(0xFF81D4FA))
        val AquaCard = CardDefaults.cardColors(containerColor = Color(0xFF00BCD4))
        val OrangeCard = CardDefaults.cardColors(containerColor = Color(0xFFFF9800))
        val RedCard = CardDefaults.cardColors(containerColor = Color(0xFFF44336))
        Spacer(Modifier.height(20.dp))
        Text("Account", Modifier.padding(20.dp), fontSize = 22.sp)
        LazyColumn {
            item {
                AdvancedButton(
                    "Invoices",
                    "Show Your Invoices",
                    Icons.Outlined.Star,
                    LightBlueCard
                ) {
                    ThisApp.userId = currentUser.value!!.userId
                    ThisApp.token = currentUser.value!!.token!!
                    navController.navigate("invoices")
                }
                AdvancedButton(
                    "Change Password",
                    "Change Your Password",
                    Icons.Outlined.Lock,
                    AquaCard
                ) {
                    navController.navigate("changePassword")
                }
                AdvancedButton("Help", "Help And Feedback", Icons.Outlined.Info, OrangeCard) {
                }
                AdvancedButton("Logout", "Logout", Icons.Outlined.ExitToApp, RedCard) {
                    CoroutineScope(Dispatchers.IO).launch {
                        userEntityViewModel.deleteAll()

                    }
                    navController.navigate("home")
                }
            }
        }

    }
}