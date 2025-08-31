package com.mahdizaredev.onlineshop.ui.components.invoices

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.mahdizaredev.onlineshop.models.invoices.Invoice
import com.mahdizaredev.onlineshop.ui.components.AdvancedButton
import com.mahdizaredev.onlineshop.ui.theme.Red

@Composable
fun InvoiceListItemView(invoice: Invoice, navController: NavController) {
    val Green = CardDefaults.cardColors(containerColor = Color(0xFF74FB00))
    val Red = CardDefaults.cardColors(containerColor = Color(0xFFff6b6b))
    AdvancedButton(
        invoice.addDate!!, invoice.status!!,
        if (invoice.status == "NotPayed") Icons.Filled.Close else
            Icons.Filled.Check,
        if (invoice.status == "NotPayed") Red else
            Green
    ) {
        navController.navigate("invoice/${invoice.id}")
    }
}