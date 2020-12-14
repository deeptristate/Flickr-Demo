package com.flickrdemo.repository.api

import com.flickrdemo.model.ImageResponse
import retrofit2.Call
import retrofit2.http.GET

interface APICalls {

    /*@POST
    fun postRequest(
        @Url url: String, @HeaderMap headers: HashMap<String, Any>,
        @Body query: String
    ): Call<String>

    @POST
    fun postRequest1(
        @Url url: String,
        @HeaderMap headers: HashMap<String, Any>,
        @QueryMap query: HashMap<String, Any?>
    ): Call<String>

    @PUT
    fun putRequest(
        @Url url: String,
        @HeaderMap headers: HashMap<String, Any>,
        @Body query: String
    ): Call<String>

    @GET
    fun getRequest(
        @Url url: String, @HeaderMap headers: HashMap<String, Any>,
        @QueryMap query: HashMap<String, Any?>
    ): Call<String>*/


    @GET("services/rest/?method=flickr.galleries.getPhotos&api_key=d14c14722aecbb1783190a9ba52efa90&gallery_id=66911286-72157647277042064&format=json&nojsoncallback=1")
    fun getPhotos(): Call<ImageResponse>
}