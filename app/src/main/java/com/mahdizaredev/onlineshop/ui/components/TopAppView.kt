package com.mahdizaredev.onlineshop.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mahdizaredev.onlineshop.db.viewmodels.BasketEntityViewModel
import com.mahdizaredev.onlineshop.db.viewmodels.UserEntityViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppView(
    navController: NavController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    showHomeButton: Boolean = false
) {
    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }
    TopAppBar(
        title = {
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500),
                ),
                exit = fadeOut()
            ) {
                Text(text = "Online Shop", fontSize = 25.sp)
            }
        },
        actions = {
            if (showHomeButton) {
                AnimatedVisibility(
                    visibleState = animatedVisibleState,
                    enter = slideInVertically(
                        animationSpec = tween(500),
                        initialOffsetY = { -40 }
                    ) + fadeIn(
                        animationSpec = tween(500),
                    ),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = {
                        navController.navigate("home")
                    }
                    ) {
                        Icon(imageVector = Icons.Outlined.Home, contentDescription = "home")
                    }
                }
            }
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500, 500),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500, 500),
                ),
                exit = fadeOut()
            ) {
                IconButton(onClick = { navController.navigate("basket") }) {
                    Box(
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
                        if (basketViewModel.dataList.value.isNotEmpty()) {
                            Card(
                                shape = RoundedCornerShape(50.dp),
                                colors = CardDefaults.cardColors(Color.Red),
                                elevation = CardDefaults.cardElevation(0.dp),
                                modifier = Modifier.size(14.dp)
                            ) {
                                Text(
                                    text = "${basketViewModel.dataList.value.size}",
                                    color = Color.White,
                                    fontSize = 8.sp,
                                    modifier = Modifier.padding(1.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500, 1000),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500, 1000),
                ),
                exit = fadeOut()
            ) {
                IconButton(onClick = {
                    if (!userEntityViewModel.isLoggedIn()) {
                        navController.navigate("login")
                    } else {
                        navController.navigate("dashboard")
                    }
                }
                ) {
                    Icon(imageVector = Icons.Outlined.Person, contentDescription = "")
                }
            }
        }
    )
}