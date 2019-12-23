package com.tahir.robortassignment.Modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val c: Context) {


    @Provides
    @Singleton
    fun provideContext(): Context {

        return c
    }
}
