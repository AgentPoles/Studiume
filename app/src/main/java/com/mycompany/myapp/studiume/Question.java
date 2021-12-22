package com.mycompany.myapp.studiume;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "question")
public class Question {
 @NonNull
 @PrimaryKey
    private String question_id;
    private String questionsBody;
    private boolean has_files;
    private int answer_pattern;
    private String assessment_id;
    private long timestamp;
    private String imageUriString;

    @Ignore
    public Question(){

    }

    public Question(String question_id, String questionsBody, String assessment_id, int answer_pattern, boolean has_files, long timestamp, String imageUriString){
        this.answer_pattern = answer_pattern;
        this.question_id = question_id;
        this.has_files = has_files;
        this.answer_pattern = answer_pattern;
        this.assessment_id = assessment_id;
        this.questionsBody = questionsBody;
        this.timestamp = timestamp;
        this.imageUriString = imageUriString;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public String getAssessment_id() {
        return assessment_id;
    }

    public String getQuestionsBody() {
        return questionsBody;
    }

    public int getAnswer_pattern() {
        return answer_pattern;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public void setAssessment_id(String assessment_id) {
        this.assessment_id = assessment_id;
    }

    public void setAnswer_pattern(int answer_pattern) {
        this.answer_pattern = answer_pattern;
    }

    public void setHas_files(boolean has_files) {
        this.has_files = has_files;
    }

    public void setQuestionsBody(String questionsBody) {
        this.questionsBody = questionsBody;
    }

    public boolean isHas_files() {
        return has_files;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageUriString() {
        return imageUriString;
    }

    public void setImageUriString(String imageUriString) {
        this.imageUriString = imageUriString;
    }
}
