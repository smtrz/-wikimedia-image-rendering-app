package com.tahir.robartassignment.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.tahir.robartassignment.Components.App
import com.tahir.robartassignment.Interfaces.RetrofitCallBack
import com.tahir.robartassignment.Models.ExpressioncallBack
import com.tahir.robartassignment.Models.ImageResult
import com.tahir.robartassignment.Repository.AppRepository
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

    fun callverifyExpressionAPI(
        expession: String,
        callback: RetrofitCallBack
    ) {
        repo!!.submitExpression(expession, callback)

    }

    fun getVerificationResult() : MutableLiveData<ExpressioncallBack>{
        return repo!!.getExpressionVerification()

    }
    fun getImageRendered(): MutableLiveData<ImageResult> {
        return repo!!.getImageData()

    }

    fun callImageRenderingAPI(header_value: String, callback: RetrofitCallBack) {
        return repo!!.downloadImage(header_value, callback)

    }

}