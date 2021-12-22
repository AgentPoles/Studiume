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
public interface AnswerDao {
    @Query("SELECT*FROM answers ORDER BY question_id")
    LiveData<List<Answers>> LoadAllAnswers();
    @Query("SELECT*FROM answers ORDER BY question_id")
    List<Answers> LoadAllOrdinaryAnswers();
    @Insert
    void insertAnswers(Answers answers);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAnswer(Answers answers);
    @Delete
    void deleteAnswer(Answers answers);
    @Query("SELECT*FROM answers WHERE question_id = :id")
    LiveData<Answers> LoadAnswersbyId (String id);
    @Query("SELECT*FROM answers WHERE question_id= :id")
    Answers LoadOrdinaryAnswerbyid (String id);
}

