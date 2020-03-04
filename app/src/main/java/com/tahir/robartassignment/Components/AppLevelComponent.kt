package com.tahir.robartassignment.Components


import com.tahir.robartassignment.Activities.MainActivity
import com.tahir.robartassignment.Modules.ContextModule
import com.tahir.robartassignment.Modules.NetModule
import com.tahir.robartassignment.Modules.RepositoryModule
import com.tahir.robartassignment.Repository.AppRepository
import com.tahir.robartassignment.ViewModels.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, NetModule::class, RepositoryModule::class])
@Singleton
interface AppLevelComponent {
    fun inject(ma: MainActivity)
    fun inject(ar: AppRepository)
    fun inject(ar: MainActivityViewModel)


}

