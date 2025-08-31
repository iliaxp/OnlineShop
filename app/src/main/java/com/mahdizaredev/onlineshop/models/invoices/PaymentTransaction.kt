package com.mahdizaredev.onlineshop.models.invoices

import com.mahdizaredev.onlineshop.models.customer.User
import com.mahdizaredev.onlineshop.models.customer.UserVM

data class PaymentTransaction(
    var items: List<InvoiceItem>,
    var user: UserVM,

)
