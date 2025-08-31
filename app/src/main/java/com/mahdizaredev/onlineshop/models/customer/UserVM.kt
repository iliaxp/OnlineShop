package com.mahdizaredev.onlineshop.models.customer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mahdizaredev.onlineshop.db.models.UserEntity

data class UserVM(
    var address: String? = "",
    var customerId: Long? = null,
    var firstName: String? = "",
    var id: Long? = null,
    var lastName: String? = "",
    var oldPassword: String? = null,
    var password: String? = null,
    var phone: String? = "",
    var postalCode: String? = "",
    var repeatPassword: String? = null,
    var token: String? = null,
    var username: String?
) {
    fun convertToEntity(): UserEntity {
        return UserEntity(
            userId = id!!,
            address = address,
            customerId = customerId!!,
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            postalCode = postalCode,
            token = token,
            username = username
        )
    }
}