package com.mycompany.myapp.studiume;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OptionsDao {
    @Query("SELECT*FROM options ORDER BY question_id")
    LiveData<List<Options>> LoadAllOptions();
    @Query("SELECT*FROM options ORDER BY question_id")
    List<Options> LoadAllOrdinaryOptions();
    @Insert
    void insertOptions(Options options);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateOptions(Options options);
    @Delete
    void deleteOptions(Options options);
    @Query("SELECT*FROM options WHERE question_id = :id")
    LiveData<Options> LoadOptionsbyId (String id);
    @Query("SELECT*FROM options WHERE question_id= :id")
    Options LoadOrdinaryOptionbyid (String id);
}
