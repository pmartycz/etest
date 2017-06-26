/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest.entities;

import etest.Utils;
import java.util.Arrays;

/**
 *
 * @author pmart
 */
public class MultiChoiceTest extends Test {
    private boolean[][] userAnswers;
    private boolean[][] correctAnswers;
    
    protected MultiChoiceTest() {
        super("multi");
    }
    
    @Override
    public void init() {
        if (userAnswers == null) {
            int numberOfQuestions = getNumberOfQuestions();
            userAnswers = new boolean[numberOfQuestions][];
            for (int i = 0; i < numberOfQuestions; i++)
                userAnswers[i] = new boolean[getNumberOfOptionsInQuestion(i)];
        }
        super.init();
    }

    @Override
    public int getUserScore() {
        int score = 0;
        for (int i = 0; i < userAnswers.length; i++)
            if (Arrays.equals(userAnswers[i], correctAnswers[i]))
                score++;
        return score;
    }

    @Override
    public boolean isQuestionOptionSelected(int answerIndex, int answerItemIndex) {
        return userAnswers[answerIndex][answerItemIndex];
    }

    @Override
    public void setQuestionOption(int answerIndex, int answerItemIndex, boolean state) {
        userAnswers[answerIndex][answerItemIndex] = state;
    }
    
    @Override
    public boolean[][] getUserAnswers() {
        return userAnswers;
    }

    @Override
    public boolean userAnsweredAllQuestions() {
        for (boolean[] userAnswer : userAnswers)
            if (!Utils.contains(userAnswer, true))
                return false;
        return true;
    }
    
    @Override
    public boolean userAnsweredAnyQuestion() {
        for (boolean[] userAnswer : userAnswers)
            if (!Utils.contains(userAnswer, true))
                return true;
        return false;
    }
}
