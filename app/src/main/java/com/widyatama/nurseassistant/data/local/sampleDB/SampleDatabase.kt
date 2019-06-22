package com.widyatama.nurseassistant.data.local.sampleDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.widyatama.nurseassistant.data.local.sampleDB.converter.StringOfListConverter
import com.widyatama.nurseassistant.data.local.sampleDB.dao.*
import com.widyatama.nurseassistant.data.model.*

/**
 * Created by bezzo on 11/01/18.
 * Add more entities = arrayOf(UserLokal::class, SampleBTable::class)
 * Add more converter must unique
 */
@Database(entities =
    [(UserResponse.User::class), (Pasien::class), (Profile::class), (Patient::class),
     (Nurse::class), (Jadwal::class), (HealingPlan::class), (RiwayatPenyakit::class),
     (Account::class)],
        version = 1)
@TypeConverters(StringOfListConverter::class)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun user() : UserDao
    abstract fun pasien() : PasienDao
    abstract fun profile() : ProfileDao
    abstract fun patient() : PatientDao
    abstract fun nurse() : OtherNurseDao
    abstract fun jadwal() : JadwalDao
    abstract fun healingPlan() : HealingPlanDao
    abstract fun riwayatPenyakit() : RiwayatPenyakitDao
    abstract fun account() : AccountDao

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