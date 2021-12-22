package com.mycompany.myapp.studiume;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import link.fls.swipestack.SwipeStack;

public class InnerQuestion extends AppCompatActivity implements nCardAdapter.OnGridClickedListener

{
    @BindView(R.id.inner_question_recycler) RecyclerView recyclerView;
@BindView(R.id.back_to_previous) FloatingActionButton back_to_previous;
@BindView(R.id.proceed_to_next) FloatingActionButton proceed_to_next;
int currentPosition;
    String assessmentname = "";
    static int assessmentType;
    String sampleQuestion = "";
    String question_image_id;
    String answer_image_id;
    public static String ASSESSMENT_NAME = "assessment_name";
    public static String ASSESSMENT_TYPE ="assessment_type";
int regexcurrentposition;
List<QuestionHolder> questionHolderList = new ArrayList<QuestionHolder>();
List<QuestionHolder> cardItems = new ArrayList<>();
nCardAdapter nCardAdapter;

   AppDatabase mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_question);
        ButterKnife.bind(this);
        mdb = AppDatabase.getsInstance(this);
        displayo("swipe left");
        Bundle bundle = getIntent().getExtras();
        assessmentname = bundle.getString(ASSESSMENT_NAME);
        assessmentType = bundle.getInt(ASSESSMENT_TYPE);
//        displayo(assessmentname);
        nCardAdapter = new nCardAdapter(this, this, cardItems);
        nCardAdapter.setOnItemClickedListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(nCardAdapter);
        setUpQuestionH();

    }


    public void setUpQuestionHolders()
    {
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        LiveData<List<Info>> infos = mDataBase.infoDao().LoadAllInfo();
        viewModel.getQuestionHolder().observe(this, new Observer<List<QuestionHolder>>() {
                    @Override
                    public void onChanged(@Nullable List<QuestionHolder> questionHolders) {

                        List<QuestionHolder> questionHolders1 = new ArrayList<QuestionHolder>();
                        questionHolders1.clear();
                        for (int i = 0; i<questionHolders.size();i++){
                     if(questionHolders.get(i).getOptions_a().length()>1) {
                           questionHolders1.add(questionHolders.get(i));
                       }
                        }

//                Processors.getInstance().setInfod(infos);
//                        List<Assessment> anexplore_Articles = new ArrayList<Assessment>();
//                        anexplore_Articles.clear();
//                        if (anexplore_Articles.size() > 0) {
//                            for (int i = 0; i < anexplore_Articles.size(); i++) {
////                            if (questions.get(i).getMclass().equals(name)) {
////                    if (questions.get(i).isAnswered()) {
////                                    questions.get(i).setRead(Spreferences.ReadState(mysharedpreference, questions.get(i).getQueid(), myeditor, questions.get(i).getNoanswer(), questions.get(i).getNocomment()));
//                                Collections.sort(assessments, new Comparator<Explore_Articles>() {
//                                    @Override
//                                    public int compare(Explore_Articles t0, Explore_Articles t1) {
//                                        return (int) (t0.getArticle_id() - t1.getArticle_id());
//                                    }
//                                });
//
//
//                            }
//                        infoAdapter.setInfoLists(anquestions);

//                    Information.InfoListsand = Processors.getInstance().getInfod();
//                    Information.infoAdapterand.notifyDataSetChanged();
                        nCardAdapter.setQuestionHolderList(questionHolders1);
                        nCardAdapter.notifyDataSetChanged();
                    }

                }
        );
        }


        public void setUpQuestionH()
    {
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        LiveData<List<Info>> infos = mDataBase.infoDao().LoadAllInfo();
        viewModel.getQuestions().observe(this, new Observer<List<Question>>() {
                    @Override
                    public void onChanged(@Nullable List<Question> questions) {

                        List<Question> questions1 = new ArrayList<Question>();
                        for (int i = 0; i<questions.size();i++) {
                            if (questions.get(i).getAssessment_id().equals(assessmentname)) {
                                questions1.add(questions.get(i));
                            }
                        }
//                Processors.getInstance().setInfod(infos);
//                        List<Assessment> anexplore_Articles = new ArrayList<Assessment>();
//                        anexplore_Articles.clear();
//                        if (anexplore_Articles.size() > 0) {
//                            for (int i = 0; i < anexplore_Articles.size(); i++) {
////                            if (questions.get(i).getMclass().equals(name)) {
////                    if (questions.get(i).isAnswered()) {
////                                    questions.get(i).setRead(Spreferences.ReadState(mysharedpreference, questions.get(i).getQueid(), myeditor, questions.get(i).getNoanswer(), questions.get(i).getNocomment()));
//                                Collections.sort(assessments, new Comparator<Explore_Articles>() {
//                                    @Override
//                                    public int compare(Explore_Articles t0, Explore_Articles t1) {
//                                        return (int) (t0.getArticle_id() - t1.getArticle_id());
//                                    }
//                                });
//
//
//                            }
//                        infoAdapter.setInfoLists(anquestions);

//                    Information.InfoListsand = Processors.getInstance().getInfod();
//                    Information.infoAdapterand.notifyDataSetChanged();
                 mySingleton.getMy().setQuestions(questions1);

                    prepareQuestion_Holders();
                    }
                }
        );
    }


    public void prepareQuestion_Holders(){

        final List<Question> questione = mySingleton.getMy().getQuestions();
        AppExecutor.getsInstance().diskIO().execute(new Runnable()
     {
         @Override
    public void run() {
        final List<Options> options = new ArrayList<Options>();
        final List<Answers> answers = new ArrayList<Answers>();
        final List<Question> questions = new ArrayList<Question>();
        for (int i = 0;i<mySingleton.getMy().getQuestions().size(); i++){
if(questione.get(i).getAssessment_id().equals(assessmentname)){
    questions.add(questione.get(i));
    if(assessmentType == HomeActivity.OBJECTIVES) {
        options.add(mdb.optionsDao().LoadOrdinaryOptionbyid(questione.get(i).getQuestion_id()));
    }
    else {
        answers.add(mdb.answerDao().LoadOrdinaryAnswerbyid(questione.get(i).getQuestion_id()));
    }
}
        }
        mySingleton.getMy().setQuestions(questions);
        mySingleton.getMy().setAnswers(answers);
        mySingleton.getMy().setOptions(options);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                List<QuestionHolder> questionHolderList = new ArrayList<>();
//                 displayo(String.valueOf(mySingleton.getMy().getQuestions().size()));
//                displayo(String.valueOf(mySingleton.getMy().getAnswers().size()));
//                displayo(String.valueOf(mySingleton.getMy().getOptions().size()));

                if (mySingleton.getMy().getQuestions() != null) {
                    List<Question> questiones = mySingleton.getMy().getQuestions();
                    List<Answers> answersList = mySingleton.getMy().getAnswers();
                    List<Options> optionsList = mySingleton.getMy().getOptions();
                    Collections.sort(questiones, new Comparator<Question>() {
                        @Override
                        public int compare(Question t0, Question t1) {
                            return (int) (t0.getTimestamp() - t1.getTimestamp());
                        }
                    });
                    mySingleton.getMy().setQuestions(questiones);
try {
    Collections.sort(answersList, new Comparator<Answers>() {
        @Override
        public int compare(Answers t0, Answers t1) {
            return (int) (t0.getTimestamp() - t1.getTimestamp());
        }
    });
    mySingleton.getMy().setAnswers(answersList);
}
catch (Exception e){

}
                    try {
                        Collections.sort(optionsList, new Comparator<Options>() {
                            @Override
                            public int compare(Options t0, Options t1) {
                                return (int) (t0.getTimestamp() - t1.getTimestamp());
                            }
                        });
                        mySingleton.getMy().setOptions(optionsList);
                    }
                    catch (Exception e){

                    }
                    for (int i = 0; i < mySingleton.getMy().getQuestions().size(); i++) {
                        String answer_body;
                        String question_id;
                        String question_body;
                        long question_timestamp;
                        question_body = mySingleton.getMy().getQuestions().get(i).getQuestionsBody();
                         question_timestamp = mySingleton.getMy().getQuestions().get(i).getTimestamp();
                         question_image_id = mySingleton.getMy().getQuestions().get(i).getImageUriString();
                        QuestionHolder questionHolder = null;
                        String assessment_id = mySingleton.getMy().getQuestions().get(i).getAssessment_id();
                        int type = assessmentType;
                        if (assessmentType == HomeActivity.OBJECTIVES) {
                            question_id = mySingleton.getMy().getOptions().get(i).getQuestion_id();
                            String ops_a = options.get(i).getOptions_a();
                            String ops_b = options.get(i).getOptions_b();
                            String ops_c = options.get(i).getOptions_c();
                            String ops_d = options.get(i).getOptions_d();
                            String ops_e = options.get(i).getOptions_e();
                            int correct_option = options.get(i).getCorrect_option();
                            String correct_optione = "";
                            switch (correct_option) {
                                case 0: {
                                    correct_optione = "option A";
                                    break;
                                }
                                case 1: {
                                    correct_optione = "option B";
                                    break;
                                }
                                case 2: {
                                    correct_optione = "option C";
                                    break;
                                }
                                case 3: {
                                    correct_optione = "option D";
                                    break;
                                }
                                case 4: {
                                    correct_optione = "option E";
                                    break;
                                }

                                default:
                                    break;
                            }

                            answer_body = correct_optione;
                            questionHolder = new QuestionHolder(question_id, assessment_id, assessmentType, false, ops_a, ops_b, ops_c, ops_d, ops_e, question_body, question_image_id, false, answer_body, "",question_timestamp);
                        } else {
                            question_id = mySingleton.getMy().getAnswers().get(i).getQuestion_id();
                            answer_body = answers.get(i).getAnswer_body();
                            answer_image_id = answers.get(i).getFile_id();

                            questionHolder = new QuestionHolder(question_id, assessment_id, assessmentType, false, null, null, null, null, null, question_body, question_image_id, false, answer_body, answer_image_id,question_timestamp);
                        }
//                    displayo(String.valueOf(mySingleton.getMy().getQuestionHolders().size()));

                        questionHolderList.add(questionHolder);

//                    displayo(String.valueOf(mySingleton.getMy().getQuestionHolders().size()));
                    }
                    Collections.sort(questionHolderList, new Comparator<QuestionHolder>() {
                        @Override
                        public int compare(QuestionHolder t0, QuestionHolder t1) {
                            return (int) (t0.getQuestion_timestamp() - t1.getQuestion_timestamp());
                        }
                    });
                    mySingleton.getMy().setQuestionHolders(questionHolderList);
                    nCardAdapter.setQuestionHolderList(mySingleton.getMy().getQuestionHolders());
                    nCardAdapter.notifyDataSetChanged();
                }
            }
        });
    }

});
    }




    @Override
    public void OnGridClicked(int position) {

    }


    public void displayo(String ji)
        {
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



}
