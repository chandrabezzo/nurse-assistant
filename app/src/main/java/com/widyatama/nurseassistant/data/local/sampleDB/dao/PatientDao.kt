package com.widyatama.nurseassistant.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.nurseassistant.data.model.Patient
import io.reactivex.Flowable


/**
 * Created by bezzo on 11/01/18.
 * if you want to check the value is null or not use "attributeName is null"
 * Room can't check boolean, boolean type will convert to numeric
 * if your column name isAttribute, will detected automatic same as boolean value
 * if your column value is object or array you must add converter
 */

@Dao
interface PatientDao {

    @Query("SELECT * FROM " + AppConstans.PATIENT)
    fun getAll(): Flowable<List<Patient>>

    @Query("SELECT * FROM " + AppConstans.PATIENT
            + " LIMIT 1")
    fun get(): Flowable<Patient>

    @Query("SELECT * FROM " + AppConstans.PATIENT
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<Patient>

    @Query("SELECT * FROM " + AppConstans.PATIENT
            + " WHERE no_rm=:noRm")
    fun get(noRm: String): Flowable<Patient>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Patient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : ArrayList<Patient>)

    @Query("DELETE FROM " + AppConstans.PATIENT)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.PATIENT
            + " WHERE no_rm=:noRm")
    fun delete(noRm: String)

    @Query("SELECT COUNT(*) FROM " + AppConstans.PATIENT)
    fun count(): Int
}