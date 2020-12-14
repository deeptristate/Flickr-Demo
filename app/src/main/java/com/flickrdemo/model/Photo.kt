package com.flickrdemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val farm: Int?,
    val has_comment: Int?,
    val id: String?,
    val is_primary: Int?,
    val isfamily: Int?,
    val isfriend: Int?,
    val ispublic: Int?,
    val owner: String?,
    val secret: String?,
    val server: String?,
    val title: String?
) : Parcelable