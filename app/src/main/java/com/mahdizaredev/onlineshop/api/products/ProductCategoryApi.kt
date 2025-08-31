package com.mahdizaredev.onlineshop.api.products

import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.products.ProductCategory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductCategoryApi {

    @GET("/api/productCategory")
    suspend fun getCategory(): ServiceResponse<ProductCategory>

    @GET("/api/productCategory/{id}")
    suspend fun getCategoryById(@Path("id") id: Long): ServiceResponse<ProductCategory>
}