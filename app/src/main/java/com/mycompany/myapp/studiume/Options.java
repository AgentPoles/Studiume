
     package com.mycompany.myapp.studiume;
     import android.arch.persistence.room.Entity;
     import android.arch.persistence.room.Ignore;
     import android.arch.persistence.room.PrimaryKey;
     import android.support.annotation.NonNull;

     import java.util.List;

     @Entity(tableName = "options")
     public class Options {

         @NonNull
         @PrimaryKey
         private String question_id;
           private String options_a;
           private String options_b;
           private String options_c;
           private String options_d;
           private String options_e;
           private int correct_option;
           private long timestamp;


           @Ignore
           public Options(){

                           }

         public Options( String question_id, String options_a, String options_b, String options_c, String options_d, String options_e, int correct_option, long timestamp){
             this.correct_option = correct_option;
             this.options_a = options_a;
             this.options_b = options_b;
             this.options_c = options_c;
             this.options_d = options_d;
             this.options_e = options_e;
             this.question_id = question_id;
             this.timestamp = timestamp;
         }

    public int getCorrect_option() {
        return correct_option;
    }

         public String getOptions_e() {
             return options_e;
         }

         public String getOptions_c() {
             return options_c;
         }

         public String getOptions_d() {
             return options_d;
         }

         public String getOptions_b() {
             return options_b;
         }

         public String getOptions_a() {
             return options_a;
         }

         public void setCorrect_option(int correct_option) {
        this.correct_option = correct_option;
    }

         public void setOptions_e(String options_e) {
             this.options_e = options_e;
         }

         public void setOptions_d(String options_d) {
             this.options_d = options_d;
         }

         public void setOptions_c(String options_c) {
             this.options_c = options_c;
         }

         public void setOptions_b(String options_b) {
             this.options_b = options_b;
         }

         public void setOptions_a(String options_a) {
             this.options_a = options_a;
         }

         public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
        }

         public long getTimestamp() {
             return timestamp;
         }

         public void setTimestamp(long timestamp) {
             this.timestamp = timestamp;
         }
     }
