package com.mahdizaredev.onlineshop.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mahdizaredev.onlineshop.ui.components.AdvancedButton
import com.mahdizaredev.onlineshop.ui.components.LoadingInColumn
import com.mahdizaredev.onlineshop.viewmodels.invoices.InvoiceItemViewModel
import com.mahdizaredev.onlineshop.viewmodels.invoices.InvoiceViewModel

@Composable
fun InvoiceScreen(
    navController: NavController,
    id: Long,
    viewModel: InvoiceItemViewModel = hiltViewModel()
) {

    var invoice by remember { mutableStateOf(viewModel.data) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }


    val Green = CardDefaults.cardColors(containerColor = Color(0xFF74FB00))
    val Red = CardDefaults.cardColors(containerColor = Color(0xFFff6b6b))
    if (isLoading.value || invoice.value == null) {
        LoadingInColumn(Modifier.fillMaxSize())
    } else {
        Column() {
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
                        "Invoices",
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.padding(5.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = if (invoice.value!!.status == "NotPayed") Red else
                        Green
                ) {
                    Icon(
                        imageVector =
                            if (invoice.value!!.status == "NotPayed") Icons.Filled.Close else
                                Icons.Filled.Check,
                        contentDescription = "",
                        Modifier
                            .size(200.dp)
                            .padding(30.dp),
                        tint = Color.White
                    )
                }

            }

        }
        Column(modifier = Modifier.padding(20.dp)) {

        }
        Spacer(modifier = Modifier.height(25.dp))
        Text("Status: ${invoice.value!!.status!!}", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text("Add Date: ${invoice.value!!.addDate!!}", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(5.dp))
        if (invoice.value!!.paymentDate.isNullOrEmpty()) {
            Text("Payment Date: ${invoice.value!!.paymentDate!!}", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(15.dp))
        }
        val LightBlueCard = CardDefaults.cardColors(containerColor = Color(0xFF81D4FA))
        val AquaCard = CardDefaults.cardColors(containerColor = Color(0xFF00BCD4))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(invoice.value!!.items!!.size) { index ->
                AdvancedButton(
                    invoice.value!!.items!![index].product!!.title!!,
                    invoice.value!!.items!![index].quantity!!.toString(),
                    Icons.Filled.List,
                    LightBlueCard
                ) {

                }
            }
        }
    }
}