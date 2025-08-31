package com.mahdizaredev.onlineshop.api.products

import androidx.compose.ui.graphics.Color
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.products.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface ColorApi {

    @GET("/api/color")
    suspend fun getColor(): ServiceResponse<ProductColor>

    @GET("/api/color/{id}")
    suspend fun getColorById(@Path("id") id: Long): ServiceResponse<ProductColor>
}