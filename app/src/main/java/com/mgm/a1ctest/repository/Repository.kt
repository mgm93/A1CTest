package com.mgm.a1ctest.repository

import com.mgm.a1ctest.api.ApiService
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Majid-Golmoradi on 10/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
class Repository @Inject constructor(private val apiService: ApiService) {

    //Api
    suspend fun getManufacturers( page: Int, pageSize : Int) = apiService.getManufacturers( page = page, pageSize = pageSize)
    suspend fun getCarTypes(manufacturerId: Int) = apiService.getCarTypes(manufacturerId = manufacturerId)
    suspend fun getBuiltDates(manufacturerId: Int, main_type : String) = apiService.getBuiltDates(manufacturerId = manufacturerId, main_type = main_type)
}