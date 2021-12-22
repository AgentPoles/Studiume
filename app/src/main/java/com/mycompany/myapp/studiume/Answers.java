package com.mycompany.myapp.studiume;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.lang.annotation.Repeatable;

@Entity(tableName = "answers")
public class Answers {

     @NonNull
     @PrimaryKey
    private String question_id;
    private String answer_body;
    private String file_id;
    private boolean has_file;
    private long timestamp;


    @Ignore
    public Answers(){

    }


    public Answers( String question_id, String answer_body, String file_id, boolean has_file, long timestamp){

        this.answer_body = answer_body;
        this.file_id = file_id;
        this.has_file = has_file;
        this.question_id = question_id;
        this.timestamp = timestamp;
    }

    public String getAnswer_body() {
        return answer_body;
    }

    public String getFile_id() {
        return file_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setAnswer_body(String answer_body) {
        this.answer_body = answer_body;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public void setHas_file(boolean has_file) {
        this.has_file = has_file;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public boolean isHas_file() {
        return has_file;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
