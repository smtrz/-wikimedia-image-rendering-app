package com.tahir.robortassignment.Models

import android.graphics.Bitmap

data class ImageResult(
    var type: String?,
    var title: String?,
    var method: String?,
    var detail: String?,
    var uri: String?,
    var bitmap: Bitmap?,
    var code: Int?
)

