package com.mgm.a1ctest.repository

import com.mgm.a1ctest.api.ApiService
import com.mgm.a1ctest.db.HistDao
import com.mgm.a1ctest.db.HistModel
import com.mgm.a1ctest.di.DatabaseModule
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Majid-Golmoradi on 10/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
class Repository @Inject constructor(private val apiService: ApiService, private val dao: HistDao) {

    //Api
    suspend fun getManufacturers(page: Int, pageSize: Int) =
        apiService.getManufacturers(page = page, pageSize = pageSize)

    suspend fun getCarTypes(manufacturerId: Int) =
        apiService.getCarTypes(manufacturerId = manufacturerId)

    suspend fun getBuiltDates(manufacturerId: Int, main_type: String) =
        apiService.getBuiltDates(manufacturerId = manufacturerId, main_type = main_type)

    //database
    suspend fun insertHist(entity: HistModel) = dao.saveHist(entity)
    suspend fun deleteHist(entity: HistModel) = dao.deleteHist(entity)
    suspend fun existInHist(mnfName: String, carType: String) = dao.existInHist(mnfName, carType)
    suspend fun getAllHist() = dao.getAllHistory()
}