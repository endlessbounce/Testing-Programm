package Persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import service.Question;
import service.Test;
import service.View;
import Interfaces.TestsDataDao;

public class TestsDataManager extends DataManager implements TestsDataDao{
    
    @Override
    public ArrayList<String> getAllTests(){
        ArrayList<String> arr = new ArrayList<>();
        
        query = "SELECT testName FROM tests";
        try {
            rs = stmnt.executeQuery(query);
            while(rs.next()){
                String testName = rs.getString("testName");
                arr.add(testName);
            }
        } catch (SQLException ex) {
        }
        
        return arr;
    }
    
    @Override
    public ArrayList<Question> getQuestions(String testName){
        ArrayList<Question> questArr = new ArrayList<>();
        
        query = "SELECT answer1, answer2, answer3, answer4, rightAnswer, questions.question " +
                "FROM questions, answers, tests WHERE " +
                "tests.testName = '" + testName + "' AND " +
                "answers.test = tests.num AND " +
                "answers.question = questions.num;";
        try {
            rs = stmnt.executeQuery(query);

            while(rs.next()){
                Question question = new Question();
                ArrayList<String> answerArr = question.getAnswers();
                
                for(int i = 1; i <= 4; i++){
                    answerArr.add(rs.getString("answer" + i));
                }
                
                question.setRAnswer(rs.getInt("rightAnswer"));
                question.setQuestion(rs.getString("question"));
                
                questArr.add(question);
            }
        } catch (SQLException ex) {
        } 
        
        return questArr;
    }
    
    @Override
    public Test getTest(String testName){
        Test test = new Test();
        test.setQuestions(getQuestions(testName));
        test.setName(testName);
          
        return test;
    }
    
    @Override
    public boolean saveTestResults(View view){
        String nameOfTest = view.getTest().getName();
        String date = view.getTest().getDate();
        int result = view.getTest().getResult();
        String login = view.getUser().getLogin();
        
        int added = 0;
        query = "INSERT INTO testresults (Login, TestName, `Date`, Score) "
                + "VALUES ('" + login + "', '" + nameOfTest + "', '" 
                + date + "', " + result + ");";
        try {
            added = stmnt.executeUpdate(query);
        } catch (SQLException ex) {
        }
        return added == 1;
    }
    
    @Override
    public int getTestNumber(String testName){
        int testNum = 0;
        
        try {
            query = "SELECT num FROM tests WHERE testName = '" + testName + "';";
            rs = stmnt.executeQuery(query);
            
            if(rs.next()){
                testNum = rs.getInt("num");
            }
        } catch (SQLException ex) {   
        }
        
        return testNum;
    }
    
    @Override
    public boolean deleteTest(String testName){
        int numberOfQueries = 0;
        int testNum = 0;
        
        try {
            testNum = getTestNumber(testName);
            
            query = "DELETE FROM answers WHERE test = " + testNum + ";";
            numberOfQueries += stmnt.executeUpdate(query);
            
            query = "DELETE FROM tests WHERE testName = '" + testName + "';";
            numberOfQueries += stmnt.executeUpdate(query);
            
        } catch (SQLException ex) {
        }
        
        return numberOfQueries == 2;
    }
    
    @Override
    public boolean addQuestion(Question question, String testName){
        int testNum = 0;
        int numberOfRecords = recordsCounter("questions");
        int numOfQuest = numberOfRecords+1;
        int numberOfQueries = 0;
        ArrayList<String> answers = question.getAnswers();
        
        try {
            testNum = getTestNumber(testName);
            
            query = "INSERT INTO questions VALUES (" + numOfQuest +
                    ", '" + question.getQuestion() + "');";
            numberOfQueries += stmnt.executeUpdate(query);
            
            query = "INSERT INTO answers VALUES ('" + answers.get(0) + 
                    "', '" + answers.get(1) + "', '" + answers.get(2) + 
                    "', '" + answers.get(3) + "', " + question.getRAnswer() + 
                    ", " + numOfQuest + ", " + testNum + ");";
            numberOfQueries += stmnt.executeUpdate(query);
            
        } catch (SQLException ex) {
        }
        
        return numberOfQueries == 2;
    }
    
    @Override
    public boolean addTest(String testName){
        int added = 0;
        
        try {
            int numberOfRecords = recordsCounter("tests");
            query = "INSERT INTO tests VALUES (" + (numberOfRecords + 1) + ", '" + testName + "');";
            added = stmnt.executeUpdate(query);
            
        } catch (SQLException ex) {
        }
        
        return added == 1;
    }
    
    @Override
    public boolean deleteQuestion(String question){
        int numOfQueries = 0;
        int numOfQuestion = 0;
        
        try {
            query = "SELECT num FROM questions WHERE question = '" + question + "';";
            rs = stmnt.executeQuery(query);
            
            if(rs.next()){
                numOfQuestion = rs.getInt("num");
            }
            
            query = "DELETE FROM questions WHERE question = '" + question + "';";
            numOfQueries += stmnt.executeUpdate(query);
            
            query = "DELETE FROM answers WHERE question = " + numOfQuestion + ";";
            numOfQueries += stmnt.executeUpdate(query);
            
        } catch (SQLException ex) {
        }
        
        return numOfQueries == 2;
    }
}
