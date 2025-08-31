package com.mahdizaredev.onlineshop.viewmodels.site

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.site.Slider
import com.mahdizaredev.onlineshop.repositories.site.SliderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(private val repository: SliderRepository) : ViewModel() {

    var dataList = mutableStateOf<List<Slider>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getSliders { response ->
            isLoading.value = false
            if(response.status == "OK".uppercase()){
                dataList.value = response.data!!
            }
        }
    }

    fun getSliders(onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getSliders()
            onResponse(response)
        }
    }

    fun getSliderById(id: Long,onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getSliderById(id)
            onResponse(response)
        }
    }
}