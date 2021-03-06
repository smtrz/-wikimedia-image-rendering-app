package com.tahir.robartassignment.Modules

import com.google.gson.Gson
import com.tahir.robartassignment.Configurations.AppConstant
import com.tahir.robartassignment.Interfaces.EndpointsInterface
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule// Constructor needs one parameter to instantiate.
{

    internal val httpLoggingInterceptor: HttpLoggingInterceptor
        @Provides
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }


    @Provides
    internal fun getApiInterface(retroFit: Retrofit): EndpointsInterface {
        return retroFit.create(EndpointsInterface::class.java)
    }

    @Provides
    internal fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient(httpLoggingInterceptor))
            .build()
    }


    internal fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .certificatePinner(
                getCertificatePinner(
                    AppConstant.serverHostName,
                    AppConstant.sslpins
                )
            )
            .build()
    }

    @Provides
    internal fun getGson(): Gson {
        return Gson()
    }

    fun getCertificatePinner(BaseUrl: String, pins: Array<String>): CertificatePinner {
        var certificatePinner = CertificatePinner.Builder()
            .add(
                BaseUrl,
                pins.get(0)
            )

            .build()
        return certificatePinner

    }
}
