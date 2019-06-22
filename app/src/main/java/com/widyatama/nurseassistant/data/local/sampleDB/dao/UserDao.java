package com.widyatama.nurseassistant.data.local.sampleDB.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.widyatama.nurseassistant.constanta.AppConstans;
import com.widyatama.nurseassistant.data.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM " + AppConstans.USER)
    public Flowable<List<UserResponse.User>> getAll();

    @Query("SELECT * FROM " + AppConstans.USER
            + " LIMIT 1")
    public Flowable<UserResponse.User> get();

    @Query("SELECT * FROM " + AppConstans.USER
            + " LIMIT :limit")
    public Flowable<UserResponse.User> getLimit(int limit);

    @Query("SELECT * FROM " + AppConstans.USER
            + " WHERE id=:id")
    public Flowable<UserResponse.User> get(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserResponse.User value);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserts(ArrayList<UserResponse.User> values);

    @Query("DELETE FROM " + AppConstans.USER)
    void deleteAll();

    @Query("DELETE FROM " + AppConstans.USER
            + " WHERE id=:id")
    void delete(int id);

    @Query("SELECT COUNT(*) FROM " + AppConstans.USER)
    int count();
}
