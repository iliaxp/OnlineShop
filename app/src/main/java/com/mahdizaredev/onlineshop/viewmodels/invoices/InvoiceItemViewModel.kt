package com.mahdizaredev.onlineshop.viewmodels.invoices

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.invoices.Invoice
import com.mahdizaredev.onlineshop.repositories.invoices.InvoiceRepository
import com.mahdizaredev.onlineshop.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoiceItemViewModel @Inject constructor(private val repository: InvoiceRepository) :
    ViewModel() {

    var token: String = ThisApp.token
    var invoiceId: Long = ThisApp.invoiceId
    var data = mutableStateOf<Invoice?>(null)
    var isLoading = mutableStateOf(true)

    init {
        getInvoiceById(invoiceId) { response ->
            isLoading.value  = false
            if (response.status == "OK".uppercase()) {
                data.value = response.data!![0]
            }
        }
    }

    fun getInvoiceById(
        id: Long,
        onResponse: (response: ServiceResponse<Invoice>) -> Unit
    ) {
        //TODO: FIX TOKEN
        viewModelScope.launch {
            val response = repository.getInvoiceById(id, token)
            onResponse(response)
        }
    }


}