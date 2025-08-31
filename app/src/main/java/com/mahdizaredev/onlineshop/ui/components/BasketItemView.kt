package com.mahdizaredev.onlineshop.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mahdizaredev.onlineshop.db.models.BasketEntity
import com.mahdizaredev.onlineshop.db.viewmodels.BasketEntityViewModel
import com.mahdizaredev.onlineshop.ui.components.products.formatPrice
import com.mahdizaredev.onlineshop.ui.screens.safeHexToColor
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BasketItemView(
    basketEntity: BasketEntity,
    viewModel: BasketEntityViewModel,
    totalPrice: MutableLongState,
    navController: NavController
) {

    var quantity by remember { mutableStateOf(basketEntity.quantity) }
    Row(modifier = Modifier.fillMaxWidth().padding(20.dp,0.dp)) {
        Card(
            modifier = Modifier.size(100.dp),
            shape = RoundedCornerShape(20.dp),
            onClick = {
                navController.navigate("showProduct/${basketEntity.productId}")
            }
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                imageModel = { basketEntity.image!! },
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
                failure = {
                    Text("image request failed")
                })
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(text = basketEntity.title!!, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${formatPrice(basketEntity.price)}T",
                fontWeight = FontWeight.Light,
                color = Color.Gray,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.decrementQuantity(basketEntity)
                        }
                        quantity--
                        totalPrice.value -= basketEntity.price!!
                    },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = quantity.toString(), fontSize = 18.sp)
                IconButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.incrementQuantity(basketEntity)
                        }
                        quantity++
                        totalPrice.value += basketEntity.price!!
                    },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "",
                        Modifier.size(25.dp)
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.delete(basketEntity)
                    }
                }, Modifier.size(25.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        tint = Color.Red
                    )
                }

            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Card(
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier.size(40.dp),
            colors = CardDefaults.cardColors(
                containerColor = safeHexToColor(basketEntity.colorHex!!)
            ),
            border = BorderStroke(1.dp, Color.White),
            content = {}
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(basketEntity.size!!, fontSize = 22.sp, color = Color.Gray)
    }
    Spacer(modifier = Modifier.height(10.dp))
}