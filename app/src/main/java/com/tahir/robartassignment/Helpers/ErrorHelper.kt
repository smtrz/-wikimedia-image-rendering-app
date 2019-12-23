package com.tahir.robortassignment.Helpers

import java.net.UnknownHostException


object ErrorHelper {

// all errors for failure can be catered here.
    fun NetworkError(e: Throwable): Boolean? {
        if (e is UnknownHostException) {
            return true
        } else {

            return false
        }
    }


}