package com.mahdizaredev.onlineshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mahdizaredev.onlineshop.db.viewmodels.BasketEntityViewModel
import com.mahdizaredev.onlineshop.ui.components.BasketItemView
import com.mahdizaredev.onlineshop.ui.components.products.formatPrice
import com.mahdizaredev.onlineshop.ui.theme.Dark

@Composable
fun BasketListScreen(navController: NavHostController, basketViewModel: BasketEntityViewModel) {
    var dataList by remember { mutableStateOf(basketViewModel.dataList) }

    var totalPriceLong: Long = 0
    for (item in dataList.value) {
        totalPriceLong += item.quantity * item.price!!
    }
    var totalPrice = remember { mutableLongStateOf(totalPriceLong) }

    LazyColumn {
        item {
            Row {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Shopping Cart", textAlign = TextAlign.Center, fontSize = 20.sp, modifier = Modifier.padding(if(dataList.value.isNotEmpty()) 0.dp else 9.dp,0.dp,0.dp))

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        if (dataList.value.isEmpty()) {
        item {

                Column(
                    Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .height(100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Your Shopping Cart is Empty!", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(40.dp))
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "",
                        Modifier.size(250.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }


        items(dataList.value.size) { index ->
            BasketItemView(dataList.value[index], basketViewModel, totalPrice, navController)
        }
        item {
            if (dataList.value.isNotEmpty()) {
                Spacer(modifier = Modifier.height(30.dp))
                Row() {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text("Total Price:", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "${formatPrice(totalPrice.value)}T",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(modifier = Modifier.padding(20.dp)) {
                    Button(
                        onClick = {
                            navController.navigate("proceedToPayment")
                        },
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Dark
                        )
                    ) {
                        Text(
                            text = "Proceed To Payment",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}