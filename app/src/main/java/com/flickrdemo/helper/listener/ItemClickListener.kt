package com.flickrdemo.helper.listener

import android.view.View

interface ItemClickListener {
    fun onItemClick(view: View?, position: Int, data: Any?)
}