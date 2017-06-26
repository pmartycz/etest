/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.entities;

import etest.JsonHelper;
import java.util.Arrays;

/**
 *
 * @author pmart
 */
public class SingleChoiceTest extends Test {
    private Integer[] userAnswers;
    private Integer[] correctAnswers;
    
    protected SingleChoiceTest() {
        super("single");
    }
    
    @Override
    public void init() {
        if (userAnswers == null) 
            userAnswers = new Integer[getNumberOfQuestions()];
        super.init();
    }
    
    @Override
    public int getUserScore() {
        int score = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] == correctAnswers[i])
                score++;
        }
        return score;
    }

    @Override
    public boolean isQuestionOptionSelected(int answerIndex, int answerItemIndex) {
        return Integer.valueOf(answerItemIndex).equals(userAnswers[answerIndex]);
    }

    @Override
    public void setQuestionOption(int answerIndex, int answerItemIndex, boolean state) {
        userAnswers[answerIndex] = state ? answerItemIndex : null; 
    }

    @Override
    public Integer[] getUserAnswers() {
        return userAnswers;
    }

    @Override
    public boolean userAnsweredAllQuestions() {
        return !Arrays.asList(userAnswers).contains(null);
    }
    
    @Override
    public boolean userAnsweredAnyQuestion() {
        return !Arrays.asList(userAnswers).contains(null);
    }
}
