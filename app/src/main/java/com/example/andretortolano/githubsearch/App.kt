package com.example.andretortolano.githubsearch

import android.app.Application
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, Integer.MAX_VALUE.toLong()))
        val built = builder.build()
        if (BuildConfig.DEBUG) {
            built.setIndicatorsEnabled(true)
            built.isLoggingEnabled = true
        }
        Picasso.setSingletonInstance(built)
    }
}
