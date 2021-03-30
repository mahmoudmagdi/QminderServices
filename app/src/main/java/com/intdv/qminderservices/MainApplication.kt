package com.intdv.qminderservices

import android.app.Application
import com.intdv.qminderservices.network.Router

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Router.initRouter(this)
    }
}