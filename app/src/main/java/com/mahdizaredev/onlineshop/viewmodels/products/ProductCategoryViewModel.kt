package com.mahdizaredev.onlineshop.viewmodels.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.products.ProductCategory
import com.mahdizaredev.onlineshop.repositories.products.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(private val repository: ProductCategoryRepository) : ViewModel() {

    var dataList = mutableStateOf<List<ProductCategory>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getCategories { response ->
            isLoading.value = false
            if (response.status == "OK".uppercase()){
                dataList.value = response.data!!
            }
        }
    }

    fun getCategories (onResponse: (response: ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getCategory()
            onResponse(response)
        }
    }

    fun getCategoryById(id: Long, onResponse: (response: ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getCategoryById(id)
            onResponse(response)
        }
    }
}