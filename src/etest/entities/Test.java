/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.entities;

import etest.JsonHelper;

/**
 * Abstract class representing a test.
 *
 * @author pmart
 */
public abstract class Test extends TestHeader {
    private Question[] questions;

    private static class Question {
        private String text;
        private String[] options;
    }
    
    protected Test(String type) {
        super(type);
    }
    
    /**
     * Returns number of questions in the test.
     * 
     * @return number of questions in the test 
     */
    public final int getNumberOfQuestions() {
        return questions.length;
    }
    
    /**
     * Returns number of options for a test question.
     * 
     * @param i question number
     * @return number of options for i-th question
     */
    public int getNumberOfOptionsInQuestion(int i) {
        return questions[i].options.length;
    }
    
    /**
     * Returns a question's text.
     * 
     * @param i question number
     * @return text of the i-th question
     */
    public String getQuestionText(int i) {
        return questions[i].text;
    }
    
    /**
     * Returns options for a test question.
     * 
     * @param i question number
     * @return array of question options
     */
    public String[] getQuestionOptions(int i) {
        return questions[i].options;
    }
    
    /**
     * Returns a selection state of question option.
     * 
     * @param questionIndex question number
     * @param questionOptionIndex question's option number
     * @return true if question option is selected
     */
    public abstract boolean isQuestionOptionSelected(int questionIndex, int questionOptionIndex);
    
    /**
     * Sets question option selection state.
     * 
     * @param questionIndex question number
     * @param questionOptionIndex question's option number
     * @param state selection state
     */
    public abstract void setQuestionOption(int questionIndex, int questionOptionIndex, boolean state);
    
    /**
     * Returns a user score for the test.
     * 
     * @return user score 
     */
    public abstract int getUserScore();
    
    
    /**
     * Returns user answers.
     * 
     * @param <T>
     * @return  user answers
     */
    public abstract <T> T[] getUserAnswers();
    
    /**
     * Returns true if user answered all questions.
     * 
     * @return true if user answered all questions
     */
    public abstract boolean userAnsweredAllQuestions();
    
    /**
     * Returns true if user answered any question.
     * 
     * @return true if user answered any question 
     */
    public abstract boolean userAnsweredAnyQuestion();
    
    public static Test fromJson(String json) {
        return JsonHelper.fromJson(json, Test.class);
    }
}
