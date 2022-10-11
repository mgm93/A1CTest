package com.mgm.a1ctest.db

import androidx.room.*
import com.mgm.a1ctest.utils.Constants

@Dao
interface HistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHist(item: HistModel)

    @Delete
    suspend fun deleteHist(item: HistModel)

    @Query("SELECT * FROM ${Constants.HISTORY_TABLE} ORDER BY id desc")
    fun getAllHistory(): MutableList<HistModel>

    @Query("SELECT EXISTS (SELECT 1 FROM  ${Constants.HISTORY_TABLE} WHERE mnfName = :mnfName and carType = :carType)")
    suspend fun existInHist(mnfName: String, carType: String):Boolean
}