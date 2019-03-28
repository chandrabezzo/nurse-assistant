package com.widyatama.univcare.data.local.sampleDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.widyatama.univcare.constanta.AppConstans
import com.widyatama.univcare.data.local.sampleDB.dao.*
import com.widyatama.univcare.data.model.*

/**
 * Created by bezzo on 11/01/18.
 * Add more entities = arrayOf(UserLokal::class, SampleBTable::class)
 * Add more converter must unique
 */
@Database(entities =
    [(UserResponse.User::class)], version = 1)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun user() : UserDao

    companion object {
        @Volatile private var INSTANCE: SampleDatabase? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }

        fun getInstance(context: Context): SampleDatabase {
            if (INSTANCE == null) {
                synchronized(SampleDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context,
                                SampleDatabase::class.java, AppConstans.DB_NAME)
                                .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun clearAllTables() {

    }
}