package com.mahdizaredev.onlineshop.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mahdizaredev.onlineshop.db.OnlineShopDataBase
import com.mahdizaredev.onlineshop.db.dao.UserEntityDao
import com.mahdizaredev.onlineshop.db.models.UserEntity

class UserEntityRepository(application: Application) {

    var userDao: UserEntityDao
    lateinit var currentUserEntity: LiveData<UserEntity>

    init {
        val database = OnlineShopDataBase.getInstance(application)
        userDao = database.userDao()
        currentUserEntity = userDao.get()
    }

    fun getCurrentUser(): LiveData<UserEntity> {
        return currentUserEntity
    }
    suspend fun insert(user: UserEntity) {
        deleteAll()
        userDao.add(user)
    }

    suspend fun update(user: UserEntity) {

        userDao.update(user)
    }

    suspend fun delete(user: UserEntity) {
        userDao.delete(user)
    }

    suspend fun deleteAll() {
        return userDao.deleteAll()
    }
}