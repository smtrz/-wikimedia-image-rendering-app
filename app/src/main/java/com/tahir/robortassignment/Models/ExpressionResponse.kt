package com.tahir.robortassignment.Models

class ExpressionResponse {

    var success: Boolean = false
    var checked: String? = null
    var identifiers: ArrayList<String> = ArrayList()
    var requiredPackages: ArrayList<String> = ArrayList()
    var endsWithDot: Boolean = false

}


//{
/* "type": "https://mediawiki.org/wiki/HyperSwitch/errors/bad_request",
 "title": "Invalid parameters",
 "method": "get",
 "detail": "data.params.hash should NOT be shorter than 1 characters",
 "uri": "/en.wikipedia.org/v1/media/math/render/png/"*/
//}