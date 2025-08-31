package com.mahdizaredev.onlineshop.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahdizaredev.onlineshop.models.products.ProductCategory
import com.mahdizaredev.onlineshop.models.products.ProductColor
import com.mahdizaredev.onlineshop.models.products.ProductSize

@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var productId: Long,
    var quantity: Int,
    var colorId: Long,
    var sizeId: Long,
    var image: String?,
    var price: Long?,
    var title: String?,
    var colorHex: String?,
    var size: String?,
)
