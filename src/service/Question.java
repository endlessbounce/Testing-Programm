package service;

import java.util.ArrayList;

public class Question {
    private String question;
    private int rightAnswer;
    private ArrayList<String> answers = new ArrayList<>();
    
    public String getQuestion(){
        return question;
    }
    
    public void setQuestion(String newQuestion){
        question =  newQuestion;
    }
    
    public int getRAnswer(){
        return rightAnswer;
    }
    
    public void setRAnswer(int newRAnswer){
        rightAnswer =  newRAnswer;
    }
    
    public ArrayList<String> getAnswers(){
        return answers;
    }
    
    public void clearAnswers(){
        answers.clear();
    }
}
