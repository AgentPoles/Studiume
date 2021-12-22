package com.mycompany.myapp.studiume;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingActiivity extends AppCompatActivity {

     int type;
     int total_number_of_question, current_number_of_question;
     EditText assessment_name;
     EditText number_of_questions;
    long timestamp;
    static String strDate, time;
     EditText total_marks_obtainable;
     EditText number_of_options;
     Button button, button_back;
     AppDatabase mDb;
     String sampleQuestione = "";
   static String assessmentName;
    String question_id;
    String assessmentExpiringDate;
    String sample_question;
    Date date;
    @BindView(R.id.card_options) CardView options_card;
    @BindView(R.id.card_answer) CardView answer_card;
    @BindView(R.id.card_question) CardView question_card;
    @BindView(R.id.current_question) TextView current_question;
    @BindView(R.id.total_question) TextView total_question;
    @BindView(R.id.counter_fab) CounterFab counterFab;
    @BindView(R.id.assemment_name_view) TextView assessment_name_view;
    @BindView(R.id.question_edit_text) EditText question_body;
    @BindView(R.id.edi_options_a) EditText options_a;
    @BindView(R.id.edi_options_b) EditText options_b;
    @BindView(R.id.edi_options_c) EditText options_c;
    @BindView(R.id.edi_options_d) EditText options_d;
    @BindView(R.id.edi_options_e) EditText options_e;
    @BindView(R.id.question_attach) ImageView question_attach;
    @BindView(R.id.attach_answer) ImageView attach_answer;
    @BindView(R.id.answer_image) ImageView answerImage;
    @BindView(R.id.question_image) ImageView questionImage;
    @BindView(R.id.answer_edit_text) EditText answer_edit_text;
    Dialog form;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_questions);
         ButterKnife.bind(this);
        form = new Dialog(this);
        form.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        form.setContentView(R.layout.assessment_form);
        mDb = AppDatabase.getsInstance(this);
        assessment_name = (EditText)form.findViewById(R.id.edi_assessment_name);
        number_of_options = (EditText)form.findViewById(R.id.edi_number_of_options);
        total_marks_obtainable = (EditText)form.findViewById(R.id.edi_total_marks_obtainable);
        number_of_questions = (EditText)form.findViewById(R.id.edi_number_of_questions);
        button = (Button)form.findViewById(R.id.button_done);
        button_back = (Button)form.findViewById(R.id.button_back);
//        ButterKnife.bind(form);
        date = new Date();

        form.setCancelable(false);
        form.show();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        assessment_name_view.setSelected(true);
        Bundle bundle = getIntent().getExtras();
        type = bundle.getInt("question_type");
        if(type ==HomeActivity.OBJECTIVES){
            number_of_options.setVisibility(View.VISIBLE);
            answer_card.setVisibility(View.GONE);
        }
        else {
            number_of_options = null;
            options_card.setVisibility(View.GONE);
        }

        button.setOnClickListener(doneListener);
        button_back.setOnClickListener(backListener);
        counterFab.setOnClickListener(counterFabListener);
       question_attach.setOnClickListener(openQuestionFileListener);
       attach_answer.setOnClickListener(openAnswerFileListener);
       answerImage.setOnClickListener(openTheoryImageListener);
       questionImage.setOnClickListener(openQuestionImageListener);

    }


    View.OnClickListener doneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                if (number_of_questions.getText().toString().equals("") || number_of_questions.getText() == null) {
                    displayo("enter number of questions intended");
                } else {
                    if (total_marks_obtainable.getText().toString().equals("") || total_marks_obtainable.getText() == null) {
                        displayo("enter the total marks for this assessment");
                    } else {
                        if (number_of_options != null) {
                            if (number_of_options.getText().toString().equals("") || number_of_options.getText() == null) {
                                displayo("enter the number of options for each question");
                            } else {
                                total_number_of_question = Integer.parseInt(number_of_questions.getText().toString());
                                current_number_of_question = 1;
                                total_question.setText(String.valueOf(total_number_of_question));
                                current_question.setText(String.valueOf(current_number_of_question));
                                assessment_name_view.setText(assessment_name.getText().toString());
                                counterFab.setCount(0);
                                form.dismiss();

                                assessmentName = assessment_name.getText().toString();
                                assessmentExpiringDate = "";
                                java.util.Calendar c = java.util.Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
                                SimpleDateFormat sdfa = new SimpleDateFormat("h:mm a");
                                strDate = sdf.format(c.getTime());
                                time = sdfa.format(c.getTime());
                                 timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                                final Assessment assessment = new Assessment(assessmentName,type,assessmentName,assessmentExpiringDate,strDate,Integer.parseInt(number_of_questions.getText().toString()),"did you know that the starfish is also called the salmosa", Integer.parseInt(total_marks_obtainable.getText().toString()),false,false,timestamp,time);
                                mySingleton.getMy().setAssessment(assessment);
                                AppExecutor.getsInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        try{
                                            mDb.assessmentDao().insertAssessment(assessment);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    form.dismiss();
                                                }
                                            });

                                        }
                                        catch (Exception e){
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    displayo("these assessment name  has been used already, use another one ");
                                                }
                                            });

                                        }
                                    }
                                });
                            }
                        }
                        else {
                            total_number_of_question = Integer.parseInt(number_of_questions.getText().toString());
                            current_number_of_question = 1;
                            total_question.setText(String.valueOf(total_number_of_question));
                            current_question.setText(String.valueOf(current_number_of_question));
                            assessment_name_view.setText(assessment_name.getText().toString());
                            counterFab.setCount(0);
//
//                            public Assessment(String assessment_id , int type, String assessmetntName,
//                                    String expiringDate, String date,int number_of_Questions, String sample_question,
//                            int total_markObtainable, boolean iscomplete, boolean has_been_asked){
//                                this.assessmetntName = assessmetntName;
//                                this.assessment_id = assessment_id;
//                                this.date = date;
//                                this.expiringDate = expiringDate;
//                                this.has_been_asked = has_been_asked;
//                                this.sample_question = sample_question;
//                                this.iscomplete = iscomplete;
//                                this.total_markObtainable = total_markObtainable;
//                                this.type = type;
//                                this.number_of_Questions = number_of_Questions;
//                            }
                            java.util.Calendar c = java.util.Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy");
                            SimpleDateFormat sdfa = new SimpleDateFormat("h:mm a");
                            strDate = sdf.format(c.getTime());
                            time = sdfa.format(c.getTime());
                            assessmentName = assessment_name.getText().toString();
                            assessmentExpiringDate = "";
                             timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

                            final Assessment assessment = new Assessment(assessmentName,type,assessmentName,assessmentExpiringDate,strDate,Integer.parseInt(number_of_questions.getText().toString()),"did you know that the starfish is also called the salmosa", Integer.parseInt(total_marks_obtainable.getText().toString()),false,false,timestamp,time);
                            mySingleton.getMy().setAssessment(assessment);
                            AppExecutor.getsInstance().diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        mDb.assessmentDao().insertAssessment(assessment);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                form.dismiss();
                                            }
                                        });

                                    }
                                    catch (Exception e){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                displayo("these assessment name  has been used already, use another one ");
                                            }
                                        });

                                    }
                                }
                            });

                        }
                    }

                }

            }



    };

    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            form.setCancelable(true);
    form.dismiss();
    onBackPressed();
        }
    };

    View.OnClickListener counterFabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (counterFab.getCount() + 1 <= total_number_of_question) {
                if (question_body.getText().toString().equals("") || question_body.getText() == null) {
                    displayo("please enter your question");
                } else {
                     timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                    question_id = assessmentName + String.valueOf(Math.random() + timestamp);
                    final Question question = new Question(question_id, question_body.getText().toString(), assessmentName, 0, true, timestamp,mySingleton.getMy().getQuestionimageUri());
                       sample_question = question_body.getText().toString();
                    switch (type) {
                        case HomeActivity.OBJECTIVES: {
                            if (options_a.getText().toString().equals("") || options_a.getText() == null) {
                                displayo("options_a not entered");
                            } else {
                                if (options_b.getText().toString().equals("") || options_b.getText() == null) {
                                    displayo("options_b not entered");
                                } else {
                                    if (options_c.getText().toString().equals("") || options_c.getText() == null) {
                                        displayo("options_c not entered");
                                    } else {
                                        if (options_d.getText().toString().equals("") || options_d.getText() == null) {
                                            displayo("options_d not entered");
                                        } else {
                                            if (options_e.getText().toString().equals("") || options_e.getText() == null) {
                                                displayo("options_e not entered");
                                            } else {
                                                if (counterFab.getCount() + 1 <= total_number_of_question) {
                                                    String ops_a = options_a.getText().toString();
                                                    String ops_b = options_b.getText().toString();
                                                    String ops_c = options_c.getText().toString();
                                                    String ops_d = options_d.getText().toString();
                                                    String ops_e = options_e.getText().toString();
                                                    timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
//                                                    assessmentName = assessment_name.getText().toString();
                                                    final Options options = new Options(question_id, ops_a, ops_b, ops_c, ops_d, ops_e, 5, timestamp);
                                                    final Assessment assessment = mySingleton.getMy().getAssessment();
                                                    assessment.setSample_question(sample_question);
//                                                    final QuestionHolder questionHolder = new QuestionHolder(question_id,"aggahhaha",type,false,ops_a,ops_b,ops_c,ops_d,ops_e,question_body.getText().toString(),"",false,"ddd","");
//                                                    questionHolder.setAssessment_id(assessmentName);
                                                    AppExecutor.getsInstance().diskIO().execute(new Runnable() {
                                                        @Override
                                                        public void run() {

//                                                            mDb.questionHolderDao().insertQuestionHolder(questionHolder);
                                                            mDb.assessmentDao().updateAssessment(assessment);
                                                            mDb.questionDao().insertQuestion(question);
                                                            mDb.optionsDao().insertOptions(options);
                                                        }
                                                    });

                                                    counterFab.increase();
                                                    question_body.setText(null);
                                                    options_a.setText(null);
                                                    options_b.setText(null);
                                                    options_c.setText(null);
                                                    options_d.setText(null);
                                                    options_e.setText(null);
                                                    mySingleton.getMy().setQuestionimageUri(null);
                                                    current_number_of_question = counterFab.getCount();
                                                    current_question.setText(String.valueOf(current_number_of_question));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }

                        default: {
                            if (answer_edit_text.getText().toString().equals("") || answer_edit_text.getText() == null) {
                                displayo("answer not entered");
                            } else {
                                if (counterFab.getCount() + 1 <= total_number_of_question) {
//                                       assessmentName = assessment_name.getText().toString();
                                    timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                                    final Answers answers = new Answers(question_id, answer_edit_text.getText().toString(), mySingleton.getMy().getTheoryimageUri(), false,timestamp);

                                    final Assessment assessment = mySingleton.getMy().getAssessment();
                                    assessment.setSample_question(sample_question);
//                                    final QuestionHolder questionHolder = new QuestionHolder(question_id,"sjsjsjjs",type,false,null,null,null,null,null,question_body.getText().toString(),"",false,answer_edit_text.getText().toString(),"");
//                                    questionHolder.setAssessment_id(assessmentName);
                                    AppExecutor.getsInstance().diskIO().execute(new Runnable() {
                                        @Override
                                        public void run() {
//                                            mDb.questionHolderDao().insertQuestionHolder(questionHolder);
                                            mDb.assessmentDao().updateAssessment(assessment);
                                            mDb.questionDao().insertQuestion(question);
                                            mDb.answerDao().insertAnswers(answers);
                                        }
                                    });
                                    counterFab.increase();
                                    question_body.setText(null);
                                    answer_edit_text.setText(null);
                                    mySingleton.getMy().setTheoryimageUri(null);
                                    mySingleton.getMy().setQuestionimageUri(null);
                                    current_number_of_question = counterFab.getCount();
                                    current_question.setText(String.valueOf(current_number_of_question));
                                }
                            }
break;
                        }
                    }
                }
            }
            else{
                displayo("number of questions has been covered");
            }
        }
    };



    View.OnClickListener openTheoryImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openImagesfile(1);
        }
    };

    View.OnClickListener openQuestionImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openImagesfile(0);
        }
    };

    View.OnClickListener openQuestionFileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
      openGeneralFile(0);
        }
    };

    View.OnClickListener openAnswerFileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
      openGeneralFile(1);
        }
    };










    public void openImagesfile(int forwhat){
        switch (forwhat){
            case 0: {openimages();
                mySingleton.getMy().setQuestionimageUri(mySingleton.getMy().getNeutralImageUri());
                break;
            }
            case 1: {openimages();
                mySingleton.getMy().setTheoryimageUri(mySingleton.getMy().getNeutralImageUri());
                break;
            }
            default:break;
        }
    }







    public void openGeneralFile(int forwhat){
        switch (forwhat){
            case 0: {openfile();
                mySingleton.getMy().setQuestionFilepath(mySingleton.getMy().getFilepath());
                break;
            }
            case 1: {openimages();
                mySingleton.getMy().setAnswerFilepath(mySingleton.getMy().getFilepath());
                break;
            }
            default:break;
        }
    }
    public void openimages(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,0);
    }

    public void openfile(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==0){
            if(resultCode == RESULT_OK){
                Uri targetUri = data.getData();
                mySingleton.getMy().setNeutralImageUri(targetUri.toString());
            }
            else {
                displayo("image obtain process cancelled!");
            }
        }
        if(requestCode ==1){
            if(resultCode == RESULT_OK){
                Uri targetUri = data.getData();
                if (targetUri.toString().contains(".doc") || targetUri.toString().contains(".docx")) {
                    // Word document
//                    intent.setDataAndType(uri, "application/msword");
                    mySingleton.getMy().setFilepath(targetUri.toString());
                } else if (targetUri.toString().contains(".pdf")) {
                    // PDF file
//                    intent.setDataAndType(uri, "application/pdf");
                    mySingleton.getMy().setFilepath(targetUri.toString());
                }
                else {
                    displayo("file selected is not a document");
                }
            }
            else {
                displayo("file obtain process cancelled!");
            }
        }
    }

    public void displayo(String ji) {
        Context context = this;
        String msg = ji;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView myTextView = new TextView(context);
        ImageView cv = new ImageView(context);
        cv.setImageResource(R.drawable.ann);
        myTextView.setText(msg);
        Resources g = getResources();
        myTextView.setTextColor(Color.parseColor("#0a2149"));
        myTextView.setBackgroundDrawable(g.getDrawable(android.R.drawable.editbox_background));
        int lHeight = LinearLayout.LayoutParams.WRAP_CONTENT;

        int lWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
        ll.addView(cv, new LinearLayout.LayoutParams(lHeight, lWidth));
        ll.addView(myTextView, new LinearLayout.LayoutParams(lHeight, lWidth));
        ll.setPadding(40, 50, 0, 50);
        toast.setView(ll);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        if(form.isShowing()){
            form.dismiss();
        }
        super.onBackPressed();
    }


}
