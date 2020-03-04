package com.tahir.robartassignment.Repository

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.tahir.robartassignment.Components.App
import com.tahir.robartassignment.Interfaces.EndpointsInterface
import com.tahir.robartassignment.Interfaces.RetrofitCallBack
import com.tahir.robartassignment.Models.ExpressionResponse
import com.tahir.robartassignment.Models.ExpressioncallBack
import com.tahir.robartassignment.Models.ImageResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class AppRepository {


    @Inject

    lateinit var retrofit: Retrofit

    @Inject
    lateinit var gson: Gson
    internal var dataLoading = MutableLiveData<Boolean>()

    internal var postImageHeader = MutableLiveData<ExpressioncallBack>()
    internal var postImageResult = MutableLiveData<ImageResult>()

    init {
        App.app.appLevelComponent.inject(this)

    }

    fun submitExpression(
        expression: String,
        callback: RetrofitCallBack
    ) {
        verifyExpression(expression, callback)

    }

    fun getImageData(): MutableLiveData<ImageResult> {
        return postImageResult

    }

    fun getExpressionVerification(): MutableLiveData<ExpressioncallBack> {
        return postImageHeader

    }


    fun verifyExpression(expression: String, callback: RetrofitCallBack) {


        dataLoading.value = true

        val endpoints = retrofit!!.create(EndpointsInterface::class.java)
        endpoints.checkExpression(expression).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                dataLoading.value = false
                val exp_res = ExpressioncallBack()
                exp_res.status = response.code()
                exp_res.success = false

                if (response.isSuccessful) {

                    var expression_reponse: ExpressionResponse =
                        gson.fromJson(response.body()?.string(), ExpressionResponse::class.java)
                    if (expression_reponse.success) {


                        exp_res.resource_location = response.headers().get("x-resource-location")
                        exp_res.success = true


                    }


                }
                postImageHeader.postValue(exp_res)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.IsError(t)

                dataLoading.value = false

            }
        })
    }

    fun downloadImage(header_location: String, callback: RetrofitCallBack) {
        var imageResult = ImageResult(null, null, null, null, null, null, 0)

        dataLoading.value = true

        val endpoints = retrofit!!.create(EndpointsInterface::class.java)
        endpoints.renderImage(header_location).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

                imageResult?.code = response.code()
                dataLoading.value = false
                if (response.isSuccessful) {

                    imageResult?.bitmap = BitmapFactory.decodeStream(response.body()?.byteStream())

                } else {
                    imageResult =
                        gson.fromJson(response.errorBody()?.string(), ImageResult::class.java)

                }

                postImageResult.postValue(imageResult)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.IsError(t)
                dataLoading.value = false

            }
        })
    }

    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dataLoading

    }


}