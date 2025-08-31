package com.mahdizaredev.onlineshop.api.invoices

import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.invoices.PaymentTransaction
import com.mahdizaredev.onlineshop.models.products.ProductColor
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {

    @POST("/api/trx/gotoPayment")
    suspend fun goToPayment(@Body data: PaymentTransaction): ServiceResponse<String>
}