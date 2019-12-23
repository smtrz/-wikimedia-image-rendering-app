package com.tahir.robortassignment.Interfaces


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface EndpointsInterface {
    @FormUrlEncoded
    @POST("check/tex")
    fun checkExpression(
        @Field("q") expression: String
    ): Call<ResponseBody>


    @GET("render/png/{location}")
    fun renderImage(
        @Path("location") location_header: String
    ): Call<ResponseBody>

}



