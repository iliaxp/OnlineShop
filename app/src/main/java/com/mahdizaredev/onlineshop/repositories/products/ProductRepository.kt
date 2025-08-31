package com.mahdizaredev.onlineshop.repositories.products

import com.mahdizaredev.onlineshop.api.products.ProductApi
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.products.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(private val api: ProductApi){

    suspend fun getProducts(pageIndex: Int, pageSize: Int): ServiceResponse<Product>{
        return try {
            api.getProduct(pageIndex,pageSize)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getProductsByCategoryId(categoryId:Long,pageIndex: Int, pageSize: Int): ServiceResponse<Product>{
        return try {
            api.getProductsByCategoryId(categoryId,pageIndex,pageSize)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getProductById(id: Long): ServiceResponse<Product>{
        return try {
            api.getProductById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getNewProduct(): ServiceResponse<Product>{
        return try {
            api.getNewProduct()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getPopularProduct(): ServiceResponse<Product>{
        return try {
            api.getPopularProduct()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}