package com.tahir.robortassignment.Components


import com.tahir.robortassignment.Adapters.GroupAdapter
import com.tahir.robortassignment.GroupMemeberActivity
import com.tahir.robortassignment.Activities.MainActivity
import com.tahir.robortassignment.Modules.ContextModule
import com.tahir.robortassignment.Modules.NetModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, NetModule::class])
@Singleton
interface AppLevelComponent {
    fun inject(ma: MainActivity)
    fun inject(ma: GroupAdapter)
    fun inject(ma: GroupMemeberActivity)

}

