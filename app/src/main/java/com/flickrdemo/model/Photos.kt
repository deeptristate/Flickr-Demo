package com.flickrdemo.model

data class Photos(
    val page: Int?,
    val pages: Int?,
    val perpage: Int?,
    val photo: ArrayList<Photo>?,
    val total: Int?
)