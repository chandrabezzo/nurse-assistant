package com.widyatama.nurseassistant.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.nurseassistant.data.model.Account
import io.reactivex.Flowable


/**
 * Created by bezzo on 11/01/18.
 * if you want to check the value is null or not use "attributeName is null"
 * Room can't check boolean, boolean type will convert to numeric
 * if your column name isAttribute, will detected automatic same as boolean value
 * if your column value is object or array you must add converter
 */

@Dao
interface AccountDao {

    @Query("SELECT * FROM " + AppConstans.ACCOUNT)
    fun getAll(): Flowable<List<Account>>

    @Query("SELECT * FROM " + AppConstans.ACCOUNT
            + " LIMIT 1")
    fun get(): Flowable<Account>

    @Query("SELECT * FROM " + AppConstans.ACCOUNT
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<Account>

    @Query("SELECT * FROM " + AppConstans.ACCOUNT
            + " WHERE npm=:npm")
    fun get(npm: String): Flowable<Account>

    @Query("SELECT * FROM " + AppConstans.ACCOUNT
            + " WHERE username=:username and password=:password")
    fun get(username: String, password: String): Flowable<Account>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : Account)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : ArrayList<Account>)

    @Query("DELETE FROM " + AppConstans.ACCOUNT)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.ACCOUNT
            + " WHERE npm=:npm")
    fun delete(npm: String)

    @Query("SELECT COUNT(*) FROM " + AppConstans.ACCOUNT)
    fun count(): Int
}