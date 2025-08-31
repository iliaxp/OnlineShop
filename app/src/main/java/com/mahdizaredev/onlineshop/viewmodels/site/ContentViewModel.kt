package com.mahdizaredev.onlineshop.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.site.Content
import com.mahdizaredev.onlineshop.repositories.site.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private val repository: ContentRepository) : ViewModel() {

    fun getContent(onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getContent()
            onResponse(response)
        }
    }

    fun getContentByTitle(title: String,onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getContentByTitle(title)
            onResponse(response)
        }
    }
}