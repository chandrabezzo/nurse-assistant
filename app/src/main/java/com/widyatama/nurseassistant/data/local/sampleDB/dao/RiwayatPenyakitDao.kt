package com.widyatama.nurseassistant.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.nurseassistant.data.model.RiwayatPenyakit
import io.reactivex.Flowable


/**
 * Created by bezzo on 11/01/18.
 * if you want to check the value is null or not use "attributeName is null"
 * Room can't check boolean, boolean type will convert to numeric
 * if your column name isAttribute, will detected automatic same as boolean value
 * if your column value is object or array you must add converter
 */

@Dao
interface RiwayatPenyakitDao {

    @Query("SELECT * FROM " + AppConstans.RIWAYAT_PENYAKIT)
    fun getAll(): Flowable<List<RiwayatPenyakit>>

    @Query("SELECT * FROM " + AppConstans.RIWAYAT_PENYAKIT
            + " LIMIT 1")
    fun get(): Flowable<RiwayatPenyakit>

    @Query("SELECT * FROM " + AppConstans.RIWAYAT_PENYAKIT
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<RiwayatPenyakit>

    @Query("SELECT * FROM " + AppConstans.RIWAYAT_PENYAKIT
            + " WHERE nama=:nama")
    fun get(nama: String): Flowable<RiwayatPenyakit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : RiwayatPenyakit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : ArrayList<RiwayatPenyakit>)

    @Query("DELETE FROM " + AppConstans.RIWAYAT_PENYAKIT)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.RIWAYAT_PENYAKIT
            + " WHERE nama=:nama")
    fun delete(nama: String)

    @Query("SELECT COUNT(*) FROM " + AppConstans.RIWAYAT_PENYAKIT)
    fun count(): Int
}