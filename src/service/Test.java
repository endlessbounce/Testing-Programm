package service;

import java.util.ArrayList;

public class Test {
    private String name;
    private String date;
    private int result;
    private ArrayList<Question> questions = new ArrayList<>();
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String newDate){
        date = newDate;
    }
    
    public int getResult(){
        return result;
    }
    
    public void setResult(int newResult){
        result = newResult;
    }
    
    public ArrayList<Question> getQuestions(){
        return questions;
    }
    
    public void setQuestions(ArrayList<Question> newQuestions){
        questions = newQuestions;
    }
    
    public void clearQuestions(){
        questions.clear();
    }
}
