package com.widyatama.nurseassistant.data.local.sampleDB.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.widyatama.nurseassistant.constanta.AppConstans;
import com.widyatama.nurseassistant.data.model.Pasien;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PasienDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pasien value);

    @Query("SELECT * FROM "+ AppConstans.PASIEN)
    Flowable<List<Pasien>> getAll();

    @Query("SELECT * FROM "+ AppConstans.PASIEN + " WHERE id =:id")
    Flowable<Pasien> getById(int id);

    @Query("DELETE FROM " + AppConstans.PASIEN
            + " WHERE id=:id")
    void delete(int id);
}
