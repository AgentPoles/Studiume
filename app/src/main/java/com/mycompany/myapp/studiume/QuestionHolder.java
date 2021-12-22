package com.mycompany.myapp.studiume;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity(tableName = "questionholder")
public class QuestionHolder {
    @NonNull
    @PrimaryKey
    private String question_id;
    private String assessment_id;
    private int type;
    private boolean has_question_image;
    private String question_body;
    private String image_id;
    private String options_a;
    private String options_b;
    private String options_c;
    private String options_d;
    private String options_e;
    private long question_timestamp;
    private boolean has_answer_image;
    private String answer_body;
    private String answer_image_id;

    @Ignore
    public QuestionHolder(){

    }

    public QuestionHolder(String question_id, String assessment_id, int type, boolean has_question_image, String options_a, String options_b, String options_c, String options_d, String options_e, String question_body, String image_id, boolean has_answer_image, String answer_body, String answer_image_id, long question_timestamp){
        this.question_id = question_id;
        this.assessment_id =assessment_id;
        this.type = type;
        this.has_answer_image = has_answer_image;
        this.question_body = question_body;
        this.image_id = image_id;
        this.options_a = options_a;
        this.options_b = options_b;
        this.options_c = options_c;
        this.options_d = options_d;
        this.options_e = options_e;
        this.has_question_image = has_question_image;
        this.answer_body = answer_body;
        this.answer_image_id = answer_image_id;
    }

    public String getAssessment_id() {
        return assessment_id;
    }

    public String getAnswer_body() {
        return answer_body;
    }

    public int getType() {
        return type;
    }

    public String getOptions_a() {
        return options_a;
    }

    public String getOptions_b() {
        return options_b;
    }

    public String getOptions_c() {
        return options_c;
    }

    public String getOptions_d() {
        return options_d;
    }

    public String getOptions_e() {
        return options_e;
    }

    public void setOptions_a(String options_a) {
        this.options_a = options_a;
    }

    public void setOptions_b(String options_b) {
        this.options_b = options_b;
    }

    public void setOptions_c(String options_c) {
        this.options_c = options_c;
    }

    public void setOptions_d(String options_d) {
        this.options_d = options_d;
    }

    public void setOptions_e(String options_e) {
        this.options_e = options_e;
    }


    public String getImage_id() {
        return image_id;
    }

    public String getAnswer_image_id() {
        return answer_image_id;
    }

    public String getQuestion_body() {
        return question_body;
    }

    public boolean isHas_answer_image() {
        return has_answer_image;
    }

    public boolean isHas_question_image() {
        return has_question_image;
    }

    public void setAssessment_id(String assessment_id) {
        this.assessment_id = assessment_id;
    }

    public void setAnswer_body(String answer_body) {
        this.answer_body = answer_body;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setAnswer_image_id(String answer_image_id) {
        this.answer_image_id = answer_image_id;
    }

    public void setHas_answer_image(boolean has_answer_image) {
        this.has_answer_image = has_answer_image;
    }

    public void setHas_question_image(boolean has_question_image) {
        this.has_question_image = has_question_image;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    @NonNull
    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(@NonNull String question_id) {
        this.question_id = question_id;
    }

    public void setQuestion_body(String question_body) {

        this.question_body = question_body;
    }

    public long getQuestion_timestamp() {
        return question_timestamp;
    }

    public void setQuestion_timestamp(long question_timestamp) {
        this.question_timestamp = question_timestamp;
    }
}
