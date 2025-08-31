package com.mahdizaredev.onlineshop.viewmodels.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.products.ProductColor
import com.mahdizaredev.onlineshop.repositories.products.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(private val repository: ColorRepository) : ViewModel() {

    fun getColors (onResponse: (response: ServiceResponse<ProductColor>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getColor()
            onResponse(response)
        }
    }

    fun getColorById(id: Long, onResponse: (response: ServiceResponse<ProductColor>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getColorById(id)
            onResponse(response)
        }
    }
}