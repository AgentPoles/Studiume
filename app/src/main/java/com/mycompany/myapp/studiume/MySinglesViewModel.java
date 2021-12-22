package com.mycompany.myapp.studiume;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

    public class MySinglesViewModel extends ViewModel {
        //    private LiveData<List<Info>> info;
//    private LiveData<List<Documents>> doc;
//    private LiveData<List<Folders>> folder;
//    private LiveData<List<User>> user;
//    private LiveData<question> question;
//    private LiveData<Answer> answer;
        private LiveData<Question> questionLiveData;
        private LiveData<Answers> answersLiveData;
        private LiveData<Assessment> assessmentLiveData;
        private LiveData<QuestionHolder> questionHolderLiveData;
        private LiveData<Options> optionsLiveData;

        public MySinglesViewModel(AppDatabase database, int id, String ide){
//        question = database.questionDao().LoadQuestionById(id);
            questionLiveData = database.questionDao().LoadQuestionbyId(ide);
            questionHolderLiveData = database.questionHolderDao().LoadQuestionHolderbyId(ide);
            assessmentLiveData = database.assessmentDao().LoadAssessmentbyId(ide);
            answersLiveData = database.answerDao().LoadAnswersbyId(ide);
            optionsLiveData = database.optionsDao().LoadOptionsbyId(ide);
        }

        public LiveData<Answers> getAnswersLiveData() {
            return answersLiveData;
        }

        public LiveData<Assessment> getAssessmentLiveData() {
            return assessmentLiveData;
        }

        public LiveData<Question> getQuestionLiveData() {
            return questionLiveData;
        }

        public LiveData<Options> getOptionsLiveData() {
            return optionsLiveData;
        }

        public LiveData<QuestionHolder> getQuestionHolderLiveData() {
            return questionHolderLiveData;
        }

        public void setAnswersLiveData(LiveData<Answers> answersLiveData) {
            this.answersLiveData = answersLiveData;
        }

        public void setQuestionLiveData(LiveData<Question> questionLiveData) {
            this.questionLiveData = questionLiveData;
        }

        public void setAssessmentLiveData(LiveData<Assessment> assessmentLiveData) {
            this.assessmentLiveData = assessmentLiveData;
        }

        public void setOptionsLiveData(LiveData<Options> optionsLiveData) {
            this.optionsLiveData = optionsLiveData;
        }

        public void setQuestionHolderLiveData(LiveData<QuestionHolder> questionHolderLiveData) {
            this.questionHolderLiveData = questionHolderLiveData;
        }

    }
