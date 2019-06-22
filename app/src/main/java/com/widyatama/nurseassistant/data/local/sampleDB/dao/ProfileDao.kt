package com.widyatama.nurseassistant.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.nurseassistant.constanta.AppConstans
import com.widyatama.nurseassistant.data.model.Profile
import io.reactivex.Flowable


/**
 * Created by bezzo on 11/01/18.
 * if you want to check the value is null or not use "attributeName is null"
 * Room can't check boolean, boolean type will convert to numeric
 * if your column name isAttribute, will detected automatic same as boolean value
 * if your column value is object or array you must add converter
 */

@Dao
interface ProfileDao {

    @Query("SELECT * FROM " + AppConstans.PROFILE)
    fun getAll(): Flowable<List<Profile>>

    @Query("SELECT * FROM " + AppConstans.PROFILE
            + " LIMIT 1")
    fun get(): Flowable<Profile>

    @Query("SELECT * FROM " + AppConstans.PROFILE
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<Profile>

    @Query("SELECT * FROM " + AppConstans.PROFILE
            + " WHERE full_name=:fullName")
    fun get(fullName: String): Flowable<Profile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Profile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : ArrayList<Profile>)

    @Query("DELETE FROM " + AppConstans.PROFILE)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.PROFILE
            + " WHERE full_name=:fullName")
    fun delete(fullName: String)

    @Query("SELECT COUNT(*) FROM " + AppConstans.PROFILE)
    fun count(): Int
}