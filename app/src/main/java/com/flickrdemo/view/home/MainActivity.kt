package com.flickrdemo.view.home

import APIConst
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.flickrdemo.R
import com.flickrdemo.helper.*
import com.flickrdemo.helper.listener.ItemClickListener
import com.flickrdemo.model.Photo
import com.flickrdemo.view.adapter.ImageAdapter
import com.flickrdemo.view.detail.DetailActivity
import com.flickrdemo.viewmodel.ImageViewModel
import com.flickrdemo.viewmodel.ImageViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private var adapter: ImageAdapter? = null
    private var imageViewModel: ImageViewModel? = null

    private val imageViewModelFactory: ImageViewModelFactory by instance()

    private var list = ArrayList<Photo>()

    private var isLastPage = false
    private var isLoading = false
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewModel =
            ViewModelProvider(this, imageViewModelFactory).get(ImageViewModel::class.java)

        rvImages.addItemDecoration(GridSpacingItemDecoration(3, 5.dpToPx(), true))
//        setAdapter()

        callImageAPI()
    }

    private fun callImageAPI() {
        progressBar.show(page == 1)
        progressBarBottom.show(page != 1)
        isLoading = true
        imageViewModel?.getImage()?.observe(this, Observer {
            progressBar.show(false)
            progressBarBottom.show(false)
            isLoading = false
            it?.run {
                if (this.status != APIConst.ON_FAILURE) {
                    this.photos?.photo?.run {
                        list.addAll(this)

                        setAdapter()
                    }
                } else {
                    this@MainActivity.showToast(getString(R.string.something_went_wrong))
                }
            }
        })
    }

    private fun setAdapter() {
        if (adapter == null) {
            adapter = ImageAdapter(this@MainActivity, list, object : ItemClickListener {
                override fun onItemClick(view: View?, position: Int, data: Any?) {
                    DetailActivity.startActivity(this@MainActivity, data as Photo)
                }

            })
            rvImages.adapter = adapter

            rvImages.addOnScrollListener(object :
                PaginationScrollListener(rvImages.layoutManager!!) {
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    page += 1
                    callImageAPI()
                }
            })

        } else {
            adapter?.notifyDataSetChanged()
        }
    }
}