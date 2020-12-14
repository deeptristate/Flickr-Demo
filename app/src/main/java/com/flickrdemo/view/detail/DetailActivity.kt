package com.flickrdemo.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flickrdemo.R
import com.flickrdemo.model.Photo
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context, photo: Photo) {
            Intent(context, DetailActivity::class.java).apply {
                putExtra("photo", photo)
                context.startActivity(this)
            }
        }
    }

    private lateinit var photo: Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar=supportActionBar

        actionBar?.title = "Details"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        intent?.run {
            photo = getParcelableExtra("photo")!!
        }
        ivImage.setImageURI("https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}