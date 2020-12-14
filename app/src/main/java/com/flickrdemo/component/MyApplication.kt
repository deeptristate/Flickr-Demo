package com.flickrdemo.component

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.flickrdemo.repository.ImageRepository
import com.flickrdemo.repository.api.APIClient
import com.flickrdemo.viewmodel.ImageViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
        bind() from singleton { APIClient() }
        bind() from singleton { ImageRepository(instance()) }
        bind() from provider { ImageViewModelFactory(instance()) }
    }
}