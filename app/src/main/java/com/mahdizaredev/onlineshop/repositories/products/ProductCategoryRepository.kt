package com.mahdizaredev.onlineshop.repositories.products

import com.mahdizaredev.onlineshop.api.products.ProductCategoryApi
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.products.ProductCategory
import javax.inject.Inject

class ProductCategoryRepository @Inject constructor(private val api: ProductCategoryApi){

    suspend fun getCategory(): ServiceResponse<ProductCategory>{
        return try {
            api.getCategory()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getCategoryById(id: Long): ServiceResponse<ProductCategory>{
        return try {
            api.getCategoryById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}