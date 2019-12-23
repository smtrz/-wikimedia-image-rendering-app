package com.tahir.robortassignment.Modules

import com.tahir.robortassignment.Repository.AppRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {


    @Provides
    @Singleton
    fun provideRepository(): AppRepository {

        return AppRepository()
    }
}