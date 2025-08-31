package com.mahdizaredev.onlineshop.api.customer

import androidx.compose.ui.graphics.Color
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.customer.User
import com.mahdizaredev.onlineshop.models.customer.UserVM
import com.mahdizaredev.onlineshop.models.products.ProductColor
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {

    @GET("/api/user")
    suspend fun getUserInfo(@Header("Authorization") token: String): ServiceResponse<User>

    @PUT("/api/user/changePassword")
    suspend fun changePassword(
        @Body user: UserVM,
        @Header("Authorization") token: String
    ): ServiceResponse<User>

    @POST("/api/user/login")
    suspend fun login(
        @Body user: UserVM
    ): ServiceResponse<UserVM>

    @POST("/api/user/register")
    suspend fun register(
        @Body user: UserVM
    ): ServiceResponse<User>

    @PUT("/api/user/update")
    suspend fun update(
        @Body user: UserVM,
        @Header("Authorization") token: String
    ): ServiceResponse<User>
}