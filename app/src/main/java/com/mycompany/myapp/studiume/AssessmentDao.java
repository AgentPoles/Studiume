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
public interface AssessmentDao {
    @Query("SELECT*FROM assessment ORDER BY assessment_id")
    LiveData<List<Assessment>> LoadAllAssessments();
    @Query("SELECT*FROM assessment ORDER BY assessment_id")
    List<Assessment> LoadAllOrdinaryAssessment();
    @Insert
    void insertAssessment(Assessment assessment);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAssessment(Assessment assessment);
    @Delete
    void deleteAssessment (Assessment assessment);
    @Query("SELECT*FROM assessment WHERE assessment_id = :id")
    LiveData<Assessment> LoadAssessmentbyId (String id);
    @Query("SELECT*FROM assessment WHERE assessment_id= :id")
    Assessment LoadOrdinaryAssessmentbyid (String id);
}
