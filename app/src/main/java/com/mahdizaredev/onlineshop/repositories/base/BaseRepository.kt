package com.mahdizaredev.onlineshop.repositories.base

import java.util.Locale

open class BaseRepository {
    protected fun prepareToken(token: String) {
        var fixToken = token
        if (!fixToken.lowercase(Locale.getDefault()).startsWith("bearer")) {
            fixToken = "Bearer $token"
        }
    }
}