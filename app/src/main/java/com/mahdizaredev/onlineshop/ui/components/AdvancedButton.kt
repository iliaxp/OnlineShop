package com.mahdizaredev.onlineshop.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdvancedButton(
    title:String,
    subtitle:String,
    imageVector: ImageVector,
    colors: CardColors = CardDefaults.cardColors(),
    onClick: () -> Unit
) {
    Card(onClick = { onClick() }, elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),colors = CardDefaults.cardColors(Color.Transparent)) {
        Row(Modifier.padding(20.dp)) {
            Card(
                modifier = Modifier.padding(5.dp),
                shape = RoundedCornerShape(50.dp),
                colors = colors
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "",
                    Modifier.size(50.dp).padding(10.dp),
                    tint = Color.White
                )
            }
            Spacer(
                modifier = Modifier
                    .width(15.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = subtitle,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}