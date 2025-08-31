package com.mahdizaredev.onlineshop.repositories.invoices

import com.mahdizaredev.onlineshop.api.invoices.TransactionApi
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.invoices.PaymentTransaction
import com.mahdizaredev.onlineshop.models.invoices.Transaction
import com.mahdizaredev.onlineshop.repositories.base.BaseRepository
import java.util.Locale
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val api: TransactionApi) {

    suspend fun goToPayment(data: PaymentTransaction): ServiceResponse<String>{
        return try {
            api.goToPayment(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}