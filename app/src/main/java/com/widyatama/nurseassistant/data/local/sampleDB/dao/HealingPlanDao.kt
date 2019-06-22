package com.widyatama.nurseassistant.data.local.sampleDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.widyatama.nurseassistant.constanta.AppConstans
import com.widyatama.nurseassistant.data.model.HealingPlan
import io.reactivex.Flowable


/**
 * Created by bezzo on 11/01/18.
 * if you want to check the value is null or not use "attributeName is null"
 * Room can't check boolean, boolean type will convert to numeric
 * if your column name isAttribute, will detected automatic same as boolean value
 * if your column value is object or array you must add converter
 */

@Dao
interface HealingPlanDao {

    @Query("SELECT * FROM " + AppConstans.HEALING_PLAN)
    fun getAll(): Flowable<List<HealingPlan>>

    @Query("SELECT * FROM " + AppConstans.HEALING_PLAN
            + " LIMIT 1")
    fun get(): Flowable<HealingPlan>

    @Query("SELECT * FROM " + AppConstans.HEALING_PLAN
            + " LIMIT :limit")
    fun getLimit(limit : Int): Flowable<HealingPlan>

    @Query("SELECT * FROM " + AppConstans.HEALING_PLAN
            + " WHERE id=:id")
    fun get(id: Int): Flowable<HealingPlan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value : HealingPlan)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(values : ArrayList<HealingPlan>)

    @Query("DELETE FROM " + AppConstans.HEALING_PLAN)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.HEALING_PLAN
            + " WHERE id=:id")
    fun delete(id: Int)

    @Query("SELECT COUNT(*) FROM " + AppConstans.HEALING_PLAN)
    fun count(): Int
}