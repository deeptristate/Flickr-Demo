package com.flickrdemo.repository

import APIConst
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flickrdemo.model.ImageResponse
import com.flickrdemo.repository.api.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRepository(private val apiClient: APIClient) {

    fun getImage(query: HashMap<String, Any?>): LiveData<ImageResponse> {

        val data = MutableLiveData<ImageResponse>()

        apiClient.getClient()?.getPhotos(query)?.enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                data.value = ImageResponse(null, null, APIConst.ON_FAILURE)
            }

        })
        return data
    }
}