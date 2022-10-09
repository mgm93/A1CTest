package com.mgm.a1ctest.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistModel::class], version = 0, exportSchema = false)
abstract class HistDatabase : RoomDatabase() {
    abstract fun histDao() : HistDao
}