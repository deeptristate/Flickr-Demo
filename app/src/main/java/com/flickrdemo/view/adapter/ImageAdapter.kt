package com.flickrdemo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flickrdemo.R
import com.flickrdemo.helper.listener.ItemClickListener
import com.flickrdemo.model.Photo
import kotlinx.android.synthetic.main.item_image.view.*

class ImageAdapter(
    context: Context,
    private val list: ArrayList<Photo>,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = list[position]
        holder.itemView.ivImage.setImageURI("https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg")
        holder.itemView.ivImage.setOnClickListener {
            listener.onItemClick(it, position, photo)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}