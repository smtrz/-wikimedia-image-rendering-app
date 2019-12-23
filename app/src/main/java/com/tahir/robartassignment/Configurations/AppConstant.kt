package com.tahir.robortassignment.Configurations

import java.lang.System.loadLibrary


object AppConstant {

    init {
        loadLibrary("keys")
    }

    external fun getBaseUrl(): String
    external fun SSLKey1(): String
    external fun SSLKey2(): String
    external fun SSLKey3(): String
    external fun getHostName(): String

    val serverHostName = getHostName()
    val BASE_URL = getBaseUrl()
    val sslpins = arrayOf(
        SSLKey1()

    )

}