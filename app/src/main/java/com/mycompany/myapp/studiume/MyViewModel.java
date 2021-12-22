package com.mycompany.myapp.studiume;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    //    private LiveData<List<Info>> info;
//    private LiveData<List<Documents>> doc;
//    private LiveData<List<Folders>> folder;
//    private LiveData<List<User>> user;
//    private LiveData<List<question>> questions;
//    private LiveData<List<Answer>> answers;
//    private LiveData<List<Classors>> classors;lFolders();
////        doc = appDatabase.docDao().LoadAllDocs(
    private LiveData<List<Question>> questions;
    private LiveData<List<QuestionHolder>> questionHolder;
    private LiveData<List<Assessment>> assessments;
    private LiveData<List<Options>> options;
    private LiveData<List<Answers>> answers;

    public MyViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getsInstance(getApplication());
//        info = appDatabase.infoDao().LoadAllInfo();
//        folder = appDatabase.folderDao().LoadAl);
//        user = appDatabase.userDao().LoadAllUsers();
//        questions = appDatabase.questionDao().LoadAllQuestions();
//        answers = appDatabase.answerdao().LoadAllAnswer();
//        classors = appDatabase.classorsDao().LoadAllClasses();
        questions = appDatabase.questionDao().LoadAllQuestion();
        questionHolder = appDatabase.questionHolderDao().LoadAllQuestionHolders();
        options = appDatabase.optionsDao().LoadAllOptions();
        assessments = appDatabase.assessmentDao().LoadAllAssessments();
        answers = appDatabase.answerDao().LoadAllAnswers();
    }

    public LiveData<List<Answers>> getAnswers() {
        return answers;
    }

    public LiveData<List<Assessment>> getAssessments() {
        return assessments;
    }

    public LiveData<List<Options>> getOptions() {
        return options;
    }

    public LiveData<List<Question>> getQuestions() {
        return questions;
    }

    public LiveData<List<QuestionHolder>> getQuestionHolder() {
        return questionHolder;
    }

    public void setOptions(LiveData<List<Options>> options) {
        this.options = options;
    }

    public void setAnswers(LiveData<List<Answers>> answers) {
        this.answers = answers;
    }

    public void setQuestions(LiveData<List<Question>> questions) {
        this.questions = questions;
    }

    public void setAssessments(LiveData<List<Assessment>> assessments) {
        this.assessments = assessments;
    }

    public void setQuestionHolder(LiveData<List<QuestionHolder>> questionHolder) {
        this.questionHolder = questionHolder;
    }

}
