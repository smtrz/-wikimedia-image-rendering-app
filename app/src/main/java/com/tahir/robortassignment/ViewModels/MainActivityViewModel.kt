package com.tahir.robortassignment.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tahir.robortassignment.Components.App
import com.tahir.robortassignment.Models.ExpressioncallBack
import com.tahir.robortassignment.Repository.AppRepository
import javax.inject.Inject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    @Inject

    lateinit var repo: AppRepository


    init {


        App.app.appLevelComponent.inject(this)
    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return repo!!.ifDataIsloading()

    }

    fun callverifyExpressionAPI(expession: String): MutableLiveData<ExpressioncallBack> {
        return repo!!.submitExpression(expession)

    }

}