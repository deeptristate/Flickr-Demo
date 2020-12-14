package com.flickrdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.flickrdemo.model.ImageResponse
import com.flickrdemo.model.Photo
import com.flickrdemo.repository.ImageRepository

class ImageViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    var isLastPage = false
    var isLoading = false
    var page = 1

    var list = ArrayList<Photo>()

    fun getImage(query: HashMap<String, Any?>): LiveData<ImageResponse> {
        return imageRepository.getImage(query)
    }
}