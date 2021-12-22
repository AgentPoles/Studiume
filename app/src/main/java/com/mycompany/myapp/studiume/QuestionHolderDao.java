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
public interface QuestionHolderDao {
    @Query("SELECT*FROM questionholder ORDER BY question_id")
    LiveData<List<QuestionHolder>> LoadAllQuestionHolders();
    @Query("SELECT*FROM questionholder ORDER BY question_id")
    List<QuestionHolder> LoadAllOrdinaryQuestionHolder();
    @Insert
    void insertQuestionHolder(QuestionHolder questionHolder);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateQuestionHolder(QuestionHolder questionHolder);
    @Delete
    void deleteQuestionHolder(QuestionHolder questionHolder);
    @Query("SELECT*FROM questionholder WHERE question_id = :id")
    LiveData<QuestionHolder> LoadQuestionHolderbyId (String id);
    @Query("SELECT*FROM questionholder WHERE question_id= :id")
    QuestionHolder LoadOrdinaryQuestionHolderbyid (String id);
}
