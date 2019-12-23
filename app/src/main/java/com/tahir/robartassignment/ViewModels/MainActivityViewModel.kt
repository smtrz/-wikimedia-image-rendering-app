package com.tahir.robortassignment.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.tahir.robortassignment.Components.App
import com.tahir.robortassignment.Interfaces.RetrofitCallBack
import com.tahir.robortassignment.Models.ExpressioncallBack
import com.tahir.robortassignment.Models.ImageResult
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