package com.mahdizaredev.onlineshop.repositories.site

import com.mahdizaredev.onlineshop.api.site.BlogApi
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.site.Blog
import javax.inject.Inject

class BlogRepository @Inject constructor(private val api: BlogApi){

    suspend fun getBlog(): ServiceResponse<Blog>{
        return try {
            api.getBlog()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getBlogById(id: Long): ServiceResponse<Blog>{
        return try {
            api.getBlogById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}