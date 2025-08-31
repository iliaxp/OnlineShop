package com.mahdizaredev.onlineshop.viewmodels.customers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.customer.User
import com.mahdizaredev.onlineshop.models.customer.UserVM
import com.mahdizaredev.onlineshop.repositories.customer.UserRepository
import com.mahdizaredev.onlineshop.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    var token: String = ThisApp.token
    fun getUserInfo (onResponse: (response: ServiceResponse<User>) -> Unit) {
        //TODO: FIX TOKEN
        viewModelScope.launch {
            val response = repository.getUserInfo(token)
            onResponse(response)
        }
    }

    fun changePassword (data: UserVM,onResponse: (response: ServiceResponse<User>) -> Unit) {
        //TODO: FIX TOKEN
        viewModelScope.launch {
            val response = repository.changePassword(data,token)
            onResponse(response)
        }
    }

    fun login (data: UserVM,onResponse: (response: ServiceResponse<UserVM>) -> Unit) {
        viewModelScope.launch {
            val response = repository.login(data)
            onResponse(response)
        }
    }

    fun register (data: UserVM,onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.register(data)
            onResponse(response)
        }
    }

    fun update (data: UserVM,onResponse: (response: ServiceResponse<User>) -> Unit) {
        //TODO: FIX TOKEN
        viewModelScope.launch {
            val response = repository.update(data,token)
            onResponse(response)
        }
    }
}