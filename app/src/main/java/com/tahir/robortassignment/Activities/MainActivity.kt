package com.tahir.robortassignment.Activities

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.tahir.robortassignment.Helpers.UIHelper
import com.tahir.robortassignment.Models.ExpressioncallBack
import com.tahir.robortassignment.R
import com.tahir.robortassignment.ViewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    @Inject
    lateinit var retrofit: Retrofit
    @Inject
    lateinit var gson: Gson
    lateinit var viewModel: MainActivityViewModel
    lateinit var expression_txt: String
    @Inject
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {
        render.setOnClickListener(this)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)


    }

/*

    fun getData() {


        App.app.appLevelComponent.inject(this)

        val endpoints = retrofit!!.create(EndpointsInterface::class.java)
        endpoints.getMemberList().enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

                if (response.isSuccessful) {
                    val alldata: BaseClass? =
                        gson.fromJson(response.body()?.string(), BaseClass::class.java)
                    rv_repos?.layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    adapter = GroupAdapter(applicationContext, sortArr(alldata!!.groups))
                    rv_repos?.addItemDecoration(
                        DividerItemDecoration(
                            applicationContext,
                            LinearLayoutManager.HORIZONTAL
                        )
                    )
                    rv_repos?.setAdapter(adapter)


                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("Error occured: ", t.message)

            }
        })


    }
*/

/*
    fun sortArr(list: ArrayList<Groups>): ArrayList<Groups> {
        lateinit var temp: Groups
        for (i in list.indices) {
            for (j in i + 1 until list.size) {
                if (list.get(i).members.size > list.get(j).members.size) {
                    temp = list.get(i);
                    list.set(i, list.get(j))
                    list.set(j, temp)

                }
            }
        }

        return list
    }*/

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.render -> {
// call the API.
                expression_txt = et_expression.text.toString()
                if (!TextUtils.isEmpty(expression_txt)) {


                    viewModel.callverifyExpressionAPI(et_expression.text.toString()).observe(this,
                        Observer<ExpressioncallBack> { exp_result ->
                            if (exp_result != null) {

                                if (exp_result.success && exp_result.status == 200) {
                                    // call another API


                                } else {
                                    UIHelper.showShortToastInCenter(
                                        context,
                                        "The expression is not valid, please retry."
                                    )

                                }
                            }
                            viewModel.callverifyExpressionAPI(expression_txt).postValue(null)


                        })
                }

            }


        }


    }
}
