package com.mahdizaredev.onlineshop.repositories.customer

import com.mahdizaredev.onlineshop.api.customer.UserApi
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.customer.User
import com.mahdizaredev.onlineshop.models.customer.UserVM
import com.mahdizaredev.onlineshop.repositories.base.BaseRepository
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserApi): BaseRepository() {

    suspend fun getUserInfo(token: String): ServiceResponse<User> {
        return try {
            api.getUserInfo(prepareToken(token).toString())
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun changePassword(user:UserVM,token: String): ServiceResponse<User> {
        return try {
            api.changePassword(user,prepareToken(token).toString())
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun login(data:UserVM): ServiceResponse<UserVM> {
        return try {
            api.login(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun register(user: UserVM): ServiceResponse<User> {
        return try {
            api.register(user)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun update(user:UserVM,token: String): ServiceResponse<User> {
        return try {
            api.changePassword(user,prepareToken(token).toString())
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}