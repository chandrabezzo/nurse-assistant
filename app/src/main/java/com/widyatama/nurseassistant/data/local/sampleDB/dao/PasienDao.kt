package com.widyatama.nurseassistant.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.nurseassistant.data.model.Pasien
import io.reactivex.Flowable


/**
 * Created by iman on 16/05/2019.
 */

@Dao
interface PasienDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Pasien)

    @Query("SELECT * FROM "+ AppConstans.PASIEN)
    fun getAll() : Flowable<List<Pasien>>

    @Query("SELECT * FROM "+ AppConstans.PASIEN + " WHERE id =:id")
    fun getById(id : Int) : Flowable<Pasien>

    @Query("DELETE FROM " + AppConstans.PASIEN
            + " WHERE id=:id")
    fun delete(id: Int)

}