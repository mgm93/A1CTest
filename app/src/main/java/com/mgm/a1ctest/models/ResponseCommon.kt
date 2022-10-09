package com.mgm.a1ctest.models


import com.google.gson.annotations.SerializedName

data class ResponseCommon(
    @SerializedName("page")
    val page: Int?, // 0
    @SerializedName("pageSize")
    val pageSize: Int?, // 2147483647
    @SerializedName("totalPageCount")
    val totalPageCount: Int?, // 1
    @SerializedName("wkda")
    val wkda: Map<String, String> //ArrayList<Pair<String, String>>
)