package com.mgm.a1ctest.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mgm.a1ctest.utils.Constants

@Entity(tableName = Constants.HISTORY_TABLE)
data class HistModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var mnfKey: String = "",
    var mnfName: String = "",
    var carType: String = "",
)