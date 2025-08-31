package com.mahdizaredev.onlineshop.api.site

import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.site.Content
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {

    @GET("/api/content")
    suspend fun getContents(): ServiceResponse<Content>

    @GET("/api/content/{title}")
    suspend fun getContentByTitle(@Path("title") id: String): ServiceResponse<Content>
}