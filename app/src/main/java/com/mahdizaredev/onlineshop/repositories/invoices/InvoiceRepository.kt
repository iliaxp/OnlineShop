package com.mahdizaredev.onlineshop.repositories.invoices

import com.mahdizaredev.onlineshop.api.invoices.InvoiceApi
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.invoices.Invoice
import com.mahdizaredev.onlineshop.repositories.base.BaseRepository
import java.util.Locale
import javax.inject.Inject

class InvoiceRepository @Inject constructor(private val api: InvoiceApi): BaseRepository() {

    suspend fun getInvoiceById(id: Long,token: String): ServiceResponse<Invoice>{

        return try {
            api.getInvoiceById(id, prepareToken(token).toString())
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }



    suspend fun getInvoiceByUserId(userId: Long,pageIndex: Int,pageSize: Int,token: String): ServiceResponse<Invoice>{
        return try {
            api.getInvoiceByUserId(userId,pageIndex,pageSize,prepareToken(token).toString())
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun addInvoice(data: Invoice,token: String): ServiceResponse<Invoice>{
        return try {
            api.addInvoice(data,prepareToken(token).toString())
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}