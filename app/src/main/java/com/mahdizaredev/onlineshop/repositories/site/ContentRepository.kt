package com.mahdizaredev.onlineshop.repositories.site

import com.mahdizaredev.onlineshop.api.site.ContentApi
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.site.Content
import javax.inject.Inject

class ContentRepository @Inject constructor(private val api: ContentApi){

    suspend fun getContent(): ServiceResponse<Content>{
        return try {
            api.getContents()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getContentByTitle(title: String
    ): ServiceResponse<Content>{
        return try {
            api.getContentByTitle(title)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}