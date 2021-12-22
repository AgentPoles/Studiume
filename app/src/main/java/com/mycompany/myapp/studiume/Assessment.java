package com.mycompany.myapp.studiume;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "assessment")
public class Assessment {
//    private int assessement_id;

    @NonNull
   @PrimaryKey
    private String assessment_id;
    private String assessmetntName;
    private String date;
    private String expiringDate;
    private long timestamp;
    private String time;
    private int total_markObtainable;
    private boolean has_been_asked;

    private int number_of_Questions;
    private int type;
    private String sample_question;
    private boolean iscomplete;

    @Ignore
     public Assessment(){

    }

@Ignore
public Assessment(int type, String assessmetntName, String expiringDate, String date,int number_of_Questions, String sample_question, int total_markObtainable, boolean iscomplete, boolean has_been_asked, long timestamp, String time){
    this.assessmetntName = assessmetntName;
    this.date = date;
    this.expiringDate = expiringDate;
    this.has_been_asked = has_been_asked;
    this.sample_question = sample_question;
    this.iscomplete = iscomplete;
    this.total_markObtainable = total_markObtainable;
    this.type = type;
    this.number_of_Questions = number_of_Questions;
    this.timestamp = timestamp;
}

    public Assessment(String assessment_id , int type, String assessmetntName, String expiringDate, String date,int number_of_Questions, String sample_question, int total_markObtainable, boolean iscomplete, boolean has_been_asked, long timestamp, String time){
        this.assessmetntName = assessmetntName;
        this.assessment_id = assessment_id;
        this.date = date;
        this.expiringDate = expiringDate;
        this.has_been_asked = has_been_asked;
        this.sample_question = sample_question;
        this.iscomplete = iscomplete;
        this.total_markObtainable = total_markObtainable;
        this.type = type;
        this.number_of_Questions = number_of_Questions;
        this.timestamp = timestamp;
    }

    public int getNumber_of_Questions() {
        return number_of_Questions;
    }

    public void setAssessment_id(String assessment_id) {
        this.assessment_id = assessment_id;
    }

    public int getTotal_markObtainable() {
        return total_markObtainable;
    }

    public void setAssessmetntName(String assessmetntName) {
        this.assessmetntName = assessmetntName;
    }

    public int getType() {
        return type;
    }



    public String getAssessment_id() {
        return assessment_id;
    }

    public void setExpiringDate(String expiringDate) {
        this.expiringDate = expiringDate;
    }

    public String getAssessmetntName() {
        return assessmetntName;
    }

    public void setHas_been_asked(boolean has_been_asked) {
        this.has_been_asked = has_been_asked;
    }

    public String getDaate() {
        return date;
    }

    public void setNumber_of_Questions(int number_of_Questions) {
        this.number_of_Questions = number_of_Questions;
    }

    public String getExpiringDate() {
        return expiringDate;
    }

    public void setTotal_markObtainable(int total_markObtainable) {
        this.total_markObtainable = total_markObtainable;
    }

    public String getSample_question() {
        return sample_question;
    }

    public void setSample_question(String sample_question) {
        this.sample_question = sample_question;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setIscomplete(boolean iscomplete) {
        this.iscomplete = iscomplete;
    }

    public boolean isHas_been_asked() {
        return has_been_asked;
    }

    public boolean isIscomplete() {
        return iscomplete;
    }

    public void setDate(String daate) {
        date = daate;
    }

    public String getDate() {
        return date;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}