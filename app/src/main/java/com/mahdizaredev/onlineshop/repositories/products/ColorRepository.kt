package com.mahdizaredev.onlineshop.repositories.products

import com.mahdizaredev.onlineshop.api.products.ColorApi
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.products.ProductColor
import javax.inject.Inject

class ColorRepository @Inject constructor(private val api: ColorApi){

    suspend fun getColor(): ServiceResponse<ProductColor>{
        return try {
            api.getColor()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getColorById(id: Long): ServiceResponse<ProductColor>{
        return try {
            api.getColorById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}