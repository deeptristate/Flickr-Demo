package com.flickrdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.flickrdemo.model.ImageResponse
import com.flickrdemo.repository.ImageRepository

class ImageViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    fun getImage(): LiveData<ImageResponse> {
        return imageRepository.getImage()
    }
}