package com.mgm.a1ctest.api

import com.mgm.a1ctest.models.ResponseCommon
import com.mgm.a1ctest.models.ResponseWkda
import com.mgm.a1ctest.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Majid-Golmoradi on 9/6/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface ApiService {

    @GET("car-types/manufacturer")
    suspend fun getManufacturers(
        @Query("wa_key") wa_key: String = Constants.API_KEY,
        @Query("page") page: Int, @Query("pageSize") pageSize: Int
    ): Response<ResponseCommon>

    @GET("car-types/main-types")
    suspend fun getCarTypes(
        @Query("wa_key") wa_key: String = Constants.API_KEY,
        @Query("manufacturer") manufacturerId: Int
    ): Response<ResponseCommon>

    @GET("car-types/built-dates")
    suspend fun getBuiltDates(
        @Query("wa_key") wa_key: String = Constants.API_KEY,
        @Query("manufacturer") manufacturerId: Int,
        @Query("main-type") main_type: String
    ): Response<ResponseWkda>

}