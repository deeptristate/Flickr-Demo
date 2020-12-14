package com.flickrdemo.repository.api

import com.flickrdemo.model.ImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface APICalls {

    @GET("https://api.flickr.com/services/rest/?method=flickr.galleries.getPhotos&api_key=d14c14722aecbb1783190a9ba52efa90&gallery_id=66911286-72157647277042064&format=json&nojsoncallback=1")
    fun getPhotos(@QueryMap query: HashMap<String, Any?>): Call<ImageResponse>
}