package com.mahdizaredev.onlineshop.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.site.Blog
import com.mahdizaredev.onlineshop.repositories.site.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(private val repository: BlogRepository) : ViewModel() {

    fun getBlogs(onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getBlog()
            onResponse(response)
        }
    }

    fun getBlogById(id: Long,onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getBlogById(id)
            onResponse(response)
        }
    }
}