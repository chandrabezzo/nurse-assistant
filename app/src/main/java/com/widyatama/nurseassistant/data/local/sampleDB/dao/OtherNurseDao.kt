package com.widyatama.nurseassistant.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.nurseassistant.data.model.Nurse
import io.reactivex.Flowable


/**
 * Created by bezzo on 11/01/18.
 * if you want to check the value is null or not use "attributeName is null"
 * Room can't check boolean, boolean type will convert to numeric
 * if your column name isAttribute, will detected automatic same as boolean value
 * if your column value is object or array you must add converter
 */

@Dao
interface OtherNurseDao {

    @Query("SELECT * FROM " + AppConstans.NURSE)
    fun getAll(): Flowable<List<Nurse>>

    @Query("SELECT * FROM " + AppConstans.NURSE
            + " LIMIT 1")
    fun get(): Flowable<Nurse>

    @Query("SELECT * FROM " + AppConstans.NURSE
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<Nurse>

    @Query("SELECT * FROM " + AppConstans.NURSE
            + " WHERE nip=:nip")
    fun get(nip: String): Flowable<Nurse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Nurse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : ArrayList<Nurse>)

    @Query("DELETE FROM " + AppConstans.NURSE)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.NURSE
            + " WHERE nip=:nip")
    fun delete(nip: String)

    @Query("SELECT COUNT(*) FROM " + AppConstans.NURSE)
    fun count(): Int
}