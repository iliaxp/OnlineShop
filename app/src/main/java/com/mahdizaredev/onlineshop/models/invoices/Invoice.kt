package com.mahdizaredev.onlineshop.models.invoices

import android.os.UserHandle
import com.mahdizaredev.onlineshop.models.customer.User

data class Invoice (
    val id: Long?,
    val addDate: String?,
    var items: List<InvoiceItem>?,
    var paymentDate: String?,
    var status: String?,
    var transactions: List<Transaction>?,
    var user: User?
)