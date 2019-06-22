package com.dwidasa.app.proline.di

import androidx.room.Room
import com.widyatama.nurseassistant.MvpApp
import com.widyatama.nurseassistant.data.local.AppDatabase
import com.widyatama.nurseassistant.data.local.sampleDB.dao.PasienDao
import com.widyatama.nurseassistant.data.local.sampleDB.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by iman on 12/28/2018.
 */

@Module
class DatabaseModule(private val currencyApplication: MvpApp) {

    private lateinit var database: AppDatabase


    @Singleton
    @Provides
    fun DatabaseModule() = Room.databaseBuilder(currencyApplication, AppDatabase::class.java, "proline.db")
        .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun providesPasienDao(database: AppDatabase): PasienDao {
        return database.pasienDao()
    }

}