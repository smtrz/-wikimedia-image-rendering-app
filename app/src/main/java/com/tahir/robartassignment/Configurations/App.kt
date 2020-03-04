package com.tahir.robartassignment.Components;

import android.app.Application
import com.tahir.robartassignment.Modules.ContextModule
import com.tahir.robartassignment.Modules.NetModule


class App : Application() {
    lateinit var appLevelComponent: AppLevelComponent


    override fun onCreate() {
        super.onCreate()
        app = this
        // we only have to set constructor modules or context modules.
        appLevelComponent = DaggerAppLevelComponent.builder()
            .contextModule(ContextModule(this))
            .netModule(NetModule())
            .build()


    }

    companion object {
        lateinit var app: App
    }


}
