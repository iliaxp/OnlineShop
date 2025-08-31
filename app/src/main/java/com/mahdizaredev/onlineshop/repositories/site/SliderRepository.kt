package com.mahdizaredev.onlineshop.repositories.site

import com.mahdizaredev.onlineshop.api.site.SliderApi
import com.mahdizaredev.onlineshop.models.ServiceResponse
import com.mahdizaredev.onlineshop.models.site.Slider
import javax.inject.Inject

class SliderRepository @Inject constructor(private val api: SliderApi){

    suspend fun getSliders(): ServiceResponse<Slider>{
        return try {
            api.getSliders()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getSliderById(id: Long): ServiceResponse<Slider>{
        return try {
            api.getSliderById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}