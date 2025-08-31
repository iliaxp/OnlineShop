package com.mahdizaredev.onlineshop.models.invoices

data class Transaction (
    var amount: Int?,
    var cardHolder: String?,
    var code: Int?,
    var custom: String?,
    var customerPhone: String?,
    var id: Long?,
    var orderId: String?,
    var refId: String?,
    var refundRequest: String?,
    var shaparakRefId: String?,
    var transId: String?
)