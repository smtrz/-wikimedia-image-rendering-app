package com.tahir.robartassignment;

import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.tahir.robartassignment.Components.App;
import com.tahir.robartassignment.Interfaces.RetrofitCallBack;
import com.tahir.robartassignment.Models.ExpressioncallBack;
import com.tahir.robartassignment.Repository.AppRepository;
import com.tahir.robartassignment.ViewModels.MainActivityViewModel;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(AndroidJUnit4.class)

public class TestCases {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @InjectMocks
    AppRepository dbrep;
    @Mock
    Observer<ExpressioncallBack> data_observer;
    MainActivityViewModel viewModel;
    @Mock
    Context context;
    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;


    @Before
    public void setUp() throws Exception {


        MockitoAnnotations.initMocks(this);
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new MainActivityViewModel(App.app);


        viewModel.getVerificationResult().observeForever(data_observer);
    }

    // Best Case
    // Correct Expression.
    @Test
    public void checkCorrectExpression() {
        viewModel.callverifyExpressionAPI("a+b", new RetrofitCallBack() {
            @Override
            public void IsError(@NotNull Throwable error) {
                Log.d("##", "Something went wrong");
            }
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Assert.assertTrue(viewModel.getVerificationResult().getValue().getSuccess());


    }

    // Best Case
    // Wrong Expression.
    @Test
    public void checkWrongExpression() {
        viewModel.callverifyExpressionAPI("a^", new RetrofitCallBack() {
            @Override
            public void IsError(@NotNull Throwable error) {
                Log.d("##", "Something went wrong");
            }
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Assert.assertFalse(viewModel.getVerificationResult().getValue().getSuccess());


    }
// successful image retrieval from the call.

    // Best Case

    @Test
    public void successfulImageRetrieval() {

        checkCorrectExpression();
        try {
            Thread.sleep(10000);// waiting for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (viewModel.getVerificationResult().getValue().getSuccess()) {

            viewModel.callImageRenderingAPI(viewModel.getVerificationResult().getValue().getResource_location(), new RetrofitCallBack() {
                @Override
                public void IsError(@NotNull Throwable error) {
                    Log.d("##", "Something went wrong");

                }
            });

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.assertNotEquals(viewModel.getImageRendered().getValue().getBitmap().toString(), null);


    }


    @After
    public void tearDown() throws Exception {

        viewModel = null;
        dbrep = null;
    }
}
