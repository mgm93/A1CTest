package com.mgm.a1ctest.models


import com.google.gson.annotations.SerializedName

data class ResponseWkda(
    @SerializedName("wkda")
    var wkda: Map<String, String>
){}