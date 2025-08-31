package com.mahdizaredev.onlineshop.ui.components.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.approachLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mahdizaredev.onlineshop.models.products.ProductCategory
import com.mahdizaredev.onlineshop.models.site.Slider
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductCategoryItemView(productCategory: ProductCategory,navController: NavController) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp), clip = true),
        shape = RoundedCornerShape(20.dp),
        onClick = {
            navController.navigate("products/${productCategory.id}/${productCategory.title}")
        }

    ) {
        Box {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                imageModel = { productCategory.image!! },
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
                    Text("image request faild")
                })
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = productCategory.title!!,
                    color = androidx.compose.ui.graphics.Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}