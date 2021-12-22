package com.mycompany.myapp.studiume;

import java.util.List;

public class mySingleton {

    List<Question> questions;
    List<Options> options;
    List<Answers> answers;
    String questionimageUri;
    String neutralImageUri;
    Assessment assessment;
    String questionFilepath;
    String answerFilepath;
    String filepath;
    String theoryimageUri;
    List<QuestionHolder> questionHolders;


    public static mySingleton my = new mySingleton();

    public static mySingleton getMy() {
        return my;
    }

    public static void setMy(mySingleton my) {
        mySingleton.my = my;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public List<QuestionHolder> getQuestionHolders() {
        return questionHolders;
    }

    public void setQuestionHolders(List<QuestionHolder> questionHolders) {
        this.questionHolders = questionHolders;
    }

    public String getQuestionimageUri() {
        return questionimageUri;
    }

    public void setQuestionimageUri(String questionimageUri) {
        this.questionimageUri = questionimageUri;
    }

    public String getTheoryimageUri() {
        return theoryimageUri;
    }

    public void setTheoryimageUri(String theoryimageUri) {
        this.theoryimageUri = theoryimageUri;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public String getNeutralImageUri() {
        return neutralImageUri;
    }

    public void setNeutralImageUri(String neutralImageUri) {
        this.neutralImageUri = neutralImageUri;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getAnswerFilepath() {
        return answerFilepath;
    }

    public void setAnswerFilepath(String answerFilepath) {
        this.answerFilepath = answerFilepath;
    }

    public void setQuestionFilepath(String questionFilepath) {
        this.questionFilepath = questionFilepath;
    }

    public String getQuestionFilepath() {
        return questionFilepath;
    }
}
