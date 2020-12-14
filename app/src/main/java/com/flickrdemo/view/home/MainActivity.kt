package com.flickrdemo.view.home

import APIConst
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
    private lateinit var imageViewModel: ImageViewModel

    private val imageViewModelFactory: ImageViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewModel =
            ViewModelProvider(this, imageViewModelFactory).get(ImageViewModel::class.java)


        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        rvImages.layoutManager = layoutManager
//        rvImages.addItemDecoration(GridSpacingItemDecoration(2, 5.dpToPx(), true))
//        rvImages.addItemDecoration(SpacesItemDecoration(5.dpToPx()))
//        setAdapter()

        if (imageViewModel.list.isNullOrEmpty()) {
            callImageAPI()
        } else {
            setAdapter()
        }

    }

    private fun callImageAPI() {
        progressBar.show(imageViewModel.page == 1)
        progressBarBottom.show(imageViewModel.page != 1)
        imageViewModel.isLoading = true

        val query = HashMap<String, Any?>()
        query["per_page"] = 10
        query["page"] = imageViewModel.page

        imageViewModel.getImage(query).observe(this, Observer {
            progressBar.show(false)
            progressBarBottom.show(false)
            imageViewModel.isLoading = false
            it?.run {
                if (this.status != APIConst.ON_FAILURE) {
                    this.photos?.photo?.run {
                        if (this.isNullOrEmpty()) {
                            imageViewModel.isLastPage = true
                        } else {
                            imageViewModel.list.addAll(this)
                            setAdapter()
                        }
                    }
                } else {
                    this@MainActivity.showToast(getString(R.string.something_went_wrong))
                }
            }
        })
    }

    private fun setAdapter() {
        if (adapter == null) {
            adapter =
                ImageAdapter(this@MainActivity, imageViewModel.list, object : ItemClickListener {
                    override fun onItemClick(view: View?, position: Int, data: Any?) {
                        DetailActivity.startActivity(this@MainActivity, data as Photo)
                    }

                })
            rvImages.adapter = adapter

            rvImages.addOnScrollListener(object :
                PaginationScrollListener(rvImages.layoutManager!!) {
                override fun isLastPage(): Boolean {
                    return imageViewModel.isLastPage
                }

                override fun isLoading(): Boolean {
                    return imageViewModel.isLoading
                }

                override fun loadMoreItems() {
                    imageViewModel.page += 1
                    callImageAPI()
                }
            })

        } else {
            adapter?.notifyDataSetChanged()
        }
    }
}