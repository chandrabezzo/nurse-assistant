package com.widyatama.nurseassistant.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.widyatama.nurseassistant.data.local.sampleDB.dao.PasienDao
import com.widyatama.nurseassistant.data.local.sampleDB.dao.UserDao
import com.widyatama.nurseassistant.data.model.Pasien
import com.widyatama.nurseassistant.data.model.UserResponse


/**
 * Created by Caca Rusmana on 12/11/2018.
 */

@Database(entities = [UserResponse.User::class,Pasien::class], version = 8, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun pasienDao(): PasienDao

}