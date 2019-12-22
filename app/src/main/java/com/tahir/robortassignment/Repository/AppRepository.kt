package com.tahir.robortassignment.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.tahir.robortassignment.Components.App
import com.tahir.robortassignment.Interfaces.EndpointsInterface
import com.tahir.robortassignment.Models.ExpressionResponse
import com.tahir.robortassignment.Models.ExpressioncallBack
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
    /* internal var charityData = MutableLiveData<BaseClass>()
     internal var donationData = MutableLiveData<DonationResponse>()
     internal var paymentCodeData = MutableLiveData<Int>()*/
    @Inject
    lateinit var context: Context

    init {
        App.app.appLevelComponent.inject(this)

    }

    fun submitExpression(expression: String): MutableLiveData<ExpressioncallBack> {
        verifyExpression(expression)
        return postImageHeader

    }

    /*  fun getallCharities(): LiveData<BaseClass> {
          getData()
          return charityData

      }

      fun performDonation(
          name: String,
          cardnumber: String,
          amount: String
      ) {
          doPaymentProcess(name, cardnumber, "10", "2020", amount)
      }*/


    fun verifyExpression(expression: String) {


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
                dataLoading.value = false

            }
        })
    }

    fun downloadImage(header_location: String) {


        dataLoading.value = true

        val endpoints = retrofit!!.create(EndpointsInterface::class.java)
        endpoints.renderImage().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                dataLoading.value = false
                if (response.isSuccessful) {

                    //  charityData.postValue(response.body())


                } else {
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dataLoading.value = false

            }
        })
    }
    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dataLoading

    }
/*
    fun ifDataIsloading(): MutableLiveData<Boolean> {
        return dataLoading

    }

    fun getDonationResponseData(): MutableLiveData<DonationResponse> {


        return donationData
    }


    fun getPaymentCodesData(): MutableLiveData<Int> {
        return paymentCodeData
    }*/

    /* fun donate(donation: Donation) {

         var basic_auth = Credentials.basic(AppConstant.OMISE_SKEY, "")

         dataLoading.value = true
         //  pd.show();
         val endpoints = retrofit_omise!!.create(EndpointsInterface::class.java)
         endpoints.createCharge(basic_auth, donation.amount, "thb", donation.token)
             .enqueue(object : Callback<DonationResponse> {
                 override fun onResponse(
                     call: Call<DonationResponse>,
                     response: Response<DonationResponse>
                 ) {
                     dataLoading.value = false

                     val updated_response: DonationResponse = response.body()!!

                     updated_response.status_code = response.code()

                     donationData.postValue(updated_response)


                 }

                 override fun onFailure(call: Call<DonationResponse>, t: Throwable) {
                     dataLoading.value = false

                 }
             })
     }*/


    /*  fun doPaymentProcess(
          cardName: String,
          cardNumber: String,
          cardExpMonth: String,
          cardExpyear: String, amount: String
      ) {

          var basic_auth = Credentials.basic(AppConstant.OMISE_PKEY, "")

          dataLoading.value = true
          val endpoints = retrofit_omise_token!!.create(EndpointsInterface::class.java)
          endpoints.createToken(
              basic_auth, cardName,
              cardNumber,
              cardExpMonth,
              cardExpyear
          )
              .enqueue(object : Callback<TokenResponse> {
                  override fun onResponse(
                      call: Call<TokenResponse>,
                      response: Response<TokenResponse>
                  ) {
                      dataLoading.value = false


                      if (response.isSuccessful) {

                          *//*  var tokenResponse: TokenResponse =
                             gson.fromJson(response.body()?.string(), TokenResponse::class.java)
 *//*


                        if (!response.body()?.object_val.equals("error")) {
                            val token: String? = response.body()?.id
                            val d = Donation(
                                cardName,
                                token!!,
                                amount.toInt()
                            )
                            donate(d)


                        } else {

                            UIHelper.showLongToastInCenter(context, "Something went wrong.");
                        }
                        *//* } else {
                         UIHelper.showLongToastInCenter(context, "Error occured.");


                     }*//*
                    } else {
                        var tokenResponse: TokenResponse =
                            gson.fromJson(response.errorBody()?.string(), TokenResponse::class.java)
                        UIHelper.showLongToastInCenter(context, tokenResponse.message!!);

                    }

                }

                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    dataLoading.value = false

                }
            })
    }
*/

}