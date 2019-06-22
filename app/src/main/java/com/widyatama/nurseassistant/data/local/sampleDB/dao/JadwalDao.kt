package com.widyatama.nurseassistant.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.nurseassistant.constanta.AppConstans
import com.widyatama.nurseassistant.data.model.Jadwal
import io.reactivex.Flowable


/**
 * Created by bezzo on 11/01/18.
 * if you want to check the value is null or not use "attributeName is null"
 * Room can't check boolean, boolean type will convert to numeric
 * if your column name isAttribute, will detected automatic same as boolean value
 * if your column value is object or array you must add converter
 */

@Dao
interface JadwalDao {

    @Query("SELECT * FROM " + AppConstans.JADWAL)
    fun getAll(): Flowable<List<Jadwal>>

    @Query("SELECT * FROM " + AppConstans.JADWAL
            + " LIMIT 1")
    fun get(): Flowable<Jadwal>

    @Query("SELECT * FROM " + AppConstans.JADWAL
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<Jadwal>

    @Query("SELECT * FROM " + AppConstans.JADWAL
            + " WHERE tanggal=:tanggal")
    fun get(tanggal: String): Flowable<Jadwal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Jadwal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : ArrayList<Jadwal>)

    @Query("DELETE FROM " + AppConstans.JADWAL)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.JADWAL
            + " WHERE tanggal=:tanggal")
    fun delete(tanggal: String)

    @Query("SELECT COUNT(*) FROM " + AppConstans.JADWAL)
    fun count(): Int
}