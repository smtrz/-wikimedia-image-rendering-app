package com.tahir.robartassignment.Modules

import com.tahir.robartassignment.Repository.AppRepository
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