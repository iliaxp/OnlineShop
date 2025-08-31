package com.mahdizaredev.onlineshop.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.mahdizaredev.onlineshop.db.models.BasketEntity
import com.mahdizaredev.onlineshop.db.viewmodels.BasketEntityViewModel
import com.mahdizaredev.onlineshop.ui.components.LoadingInColumn
import com.mahdizaredev.onlineshop.ui.components.LoadingInRow
import com.mahdizaredev.onlineshop.viewmodels.products.ProductViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.collections.get

@Composable
fun ShowProductScreen(
    productId: Long,
    navController: NavHostController,
    basketViewModel: BasketEntityViewModel,
    viewModel: ProductViewModel = hiltViewModel()
) {
    var data by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(true) }
    var selectedSize by remember { mutableStateOf(0) }
    var selectedColor by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }


    viewModel.getProductById(productId) { response ->
        isLoading = false
        if (response.status == "OK") {
            if (response.data!!.isNotEmpty()) {
                viewModel.data.value = response.data!![0]
            } else {
                Toast.makeText(context, "Error on load data!", Toast.LENGTH_LONG).show()
                navController.popBackStack()
            }
        }
    }

    if (isLoading) {
        LoadingInColumn(modifier = Modifier.fillMaxSize())
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box {
                GlideImage(
                    imageModel = { data.value!!.image!! },
                    modifier = Modifier.fillMaxSize(),
                    loading = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    // shows an error text message when request failed.
                    failure = {
                        Text(text = "image request failed.")
                    })
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
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
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column() {
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
                        Text(
                            text = data.value!!.title!!,
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500, 800),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500, 800),
                        ),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "${data.value!!.price!!}T",
                            color = Color.White,
                            fontSize = 26.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500, 1300),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500, 1300),
                        ),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "Sizes",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500, 1500),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500, 1500),
                        ),
                        exit = fadeOut()
                    ) {
                        LazyRow {
                            items(data.value!!.sizes!!.size) { index ->
                                TextButton(
                                    onClick = { selectedSize = index },
                                    shape = RoundedCornerShape(15.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor =
                                            if (selectedSize == index) Color.White else Color.Transparent
                                    ),
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Text(
                                        text = data.value!!.sizes!![index].title!!,
                                        fontWeight = FontWeight.Bold,
                                        color = if (selectedSize == index) Color.Black else Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500, 2000),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500, 2000),
                        ),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = "Colors",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500, 2200),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500, 2200),
                        ),
                        exit = fadeOut()
                    ) {
                        LazyRow {
                            items(data.value!!.colors!!.size) { index ->
                                TextButton(
                                    onClick = { selectedColor = index },
                                    shape = RoundedCornerShape(50.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = safeHexToColor(data.value!!.colors!![index].hexValue)
                                    ),
                                    modifier = Modifier.size(40.dp),
                                    border = BorderStroke(1.dp, Color.White)
                                ) {
                                    if (selectedColor == index) {
                                        Icon(
                                            imageVector = Icons.Filled.Check,
                                            contentDescription = "",
                                            tint = if (data.value!!.colors!![index].hexValue?.lowercase() == "ffffff") Color.Black else Color.White
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    AnimatedVisibility(
                        visibleState = animatedVisibleState,
                        enter = slideInVertically(
                            animationSpec = tween(500, 2700),
                            initialOffsetY = { -40 }
                        ) + fadeIn(
                            animationSpec = tween(500, 2700),
                        ),
                        exit = fadeOut()
                    ) {
                        Button(
                            onClick = {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val basket = BasketEntity(
                                        productId = productId,
                                        quantity = 1,
                                        sizeId = data.value!!.sizes!![selectedSize].id!!,
                                        colorId = data.value!!.colors!![selectedColor].id!!,
                                        image = data.value!!.image,
                                        price = data.value!!.price,
                                        title = data.value!!.title,
                                        colorHex = data.value!!.colors?.get(selectedColor)!!.hexValue!!,
                                        size = data.value!!.sizes?.get(selectedSize)!!.title!!
                                    )
                                    basketViewModel.addToBasket(basket)
                                }
                                Toast.makeText(
                                    context,
                                    "Product added to your basket!",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.popBackStack()
                            },
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White, // رنگ بک‌گراند دکمه
                                contentColor = Color.Black    // رنگ متن داخل دکمه
                            )
                        ) {
                            Text(
                                text = "Buy Now", fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

}

fun safeHexToColor(hex: String?): Color {
    return try {
        if (hex.isNullOrBlank() || hex.equals("null", true)) {
            Color.Gray // مقدار نامعتبر
        } else {
            val cleaned = hex.trim().removePrefix("#")
            if (cleaned.length == 6) {
                Color(android.graphics.Color.parseColor("#$cleaned"))
            } else {
                Color.Gray
            }
        }
    } catch (e: Exception) {
        Color.Gray
    }
}