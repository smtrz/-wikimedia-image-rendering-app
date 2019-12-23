package com.tahir.robortassignment.Components


import com.tahir.robortassignment.Activities.MainActivity
import com.tahir.robortassignment.Modules.ContextModule
import com.tahir.robortassignment.Modules.NetModule
import com.tahir.robortassignment.Modules.RepositoryModule
import com.tahir.robortassignment.Repository.AppRepository
import com.tahir.robortassignment.ViewModels.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, NetModule::class, RepositoryModule::class])
@Singleton
interface AppLevelComponent {
    fun inject(ma: MainActivity)
    fun inject(ar: AppRepository)
    fun inject(ar: MainActivityViewModel)


}

