package com.tahir.robortassignment.Interfaces


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface EndpointsInterface {
    @FormUrlEncoded
    @POST("check/tex")
    fun checkExpression(
        @Field("q") expression: String
    ): Call<ResponseBody>


    @GET("render/png")
    fun renderImage(
       
    ): Call<ResponseBody>

}



