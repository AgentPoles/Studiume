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
public interface QuestionDao {
    @Query("SELECT*FROM question ORDER BY question_id")
    LiveData<List<Question>> LoadAllQuestion();
    @Query("SELECT*FROM question ORDER BY question_id")
    List<Question> LoadAllOrdinaryQuestion();
    @Insert
    void insertQuestion(Question question);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateQuestion(Question question);
    @Delete
    void deleteQuestion(Question question);
    @Query("SELECT*FROM question WHERE question_id = :id")
    LiveData<Question> LoadQuestionbyId (String id);
    @Query("SELECT*FROM question WHERE question_id= :id")
    Question LoadOrdinaryQuestionbyid (String id);
}
