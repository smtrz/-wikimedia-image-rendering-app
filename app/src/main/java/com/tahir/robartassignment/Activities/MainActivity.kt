package com.tahir.robartassignment.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.tahir.robartassignment.Activities.ImageViewingActivity
import com.tahir.robartassignment.Components.App
import com.tahir.robartassignment.Helpers.ErrorHelper
import com.tahir.robartassignment.Helpers.UIHelper
import com.tahir.robartassignment.Interfaces.RetrofitCallBack
import com.tahir.robartassignment.Models.ExpressioncallBack
import com.tahir.robartassignment.Models.ImageResult
import com.tahir.robartassignment.R
import com.tahir.robartassignment.ViewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject


class MainActivity : AppCompatActivity(), View.OnClickListener, RetrofitCallBack {
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

    // initial function
    fun init() {
        App.app.appLevelComponent.inject(this)
        render.setOnClickListener(this)
        //setting up the view-model
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
// observing the verification of the entered expression
        viewModel.getVerificationResult().observe(this, Observer<ExpressioncallBack> { exp_result ->

            if (exp_result != null) {
// if the expression is valid and successful
                if (exp_result.success && exp_result.status == 200) {
                    // call another API for image rendering
                    viewModel.callImageRenderingAPI(exp_result.resource_location!!, this)

                } else {
                    UIHelper.showShortToastInCenter(
                        context,
                        "Entered expression is not valid, please retry."
                    )

                }
                // posting null back, because in this case we dont need it to be observed after the result has come.
                viewModel.getVerificationResult().postValue(null)
            }


        })

// observing image rendering API.

        viewModel.getImageRendered().observe(this, Observer<ImageResult> { data ->
            if (data != null) {
                // if the data is not null and the status code is 200
                if (data.code == 200) {
                    // sending bitmap to next activity on success
                    val intent = Intent(this, ImageViewingActivity::class.java)
                    intent.putExtra("BitmapImage", data.bitmap)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                } else {
                    UIHelper.showShortToastInCenter(
                        context,
                        "Image not found, please retry."
                    )

                }
                viewModel.getImageRendered().postValue(null)

            }

        })
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.render -> {
// once the expression is entered in the edit text then then validate the input and then call the verify-expression API
                expression_txt = et_expression.text.toString()
                if (!TextUtils.isEmpty(expression_txt)) {
                    UIHelper.hideSoftKeyboard(this, et_expression)
                    //calling verify expression API.
                    viewModel.callverifyExpressionAPI(expression_txt, this)


                }

            }


        }


    }

    // invoked in case of API failure.
    override fun IsError(error: Throwable) {
        if (ErrorHelper.NetworkError(error)!!) {


            UIHelper.showAlertDialog("Network Error, please try again !", "", this)


        }
    }
}
