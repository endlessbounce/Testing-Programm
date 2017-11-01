package Persistence;

import Interfaces.TestsDataDao;
import java.util.ArrayList;
import service.Question;
import service.Test;
import service.View;

public class OtherTestsDataManager implements TestsDataDao {
    
    @Override
    public ArrayList<String> getAllTests(){
        //override method here
        return new ArrayList<>();
    }
    
    @Override
    public ArrayList<Question> getQuestions(String testName){
        //override method here
        return new ArrayList<Question>();
    }
    
    @Override
    public Test getTest(String testName){
        //override method here
        return new Test();
    }
    
    @Override
    public boolean saveTestResults(View view){
        //override method here
        return true;
    }
    
    @Override
    public int getTestNumber(String testName){
        //override method here
        return 0;
    }
    
    @Override
    public boolean deleteTest(String testName){
        //override method here
        return true;
    }
    
    @Override
    public boolean addQuestion(Question question, String testName){
        //override method here
        return true;
    }
    
    @Override
    public boolean addTest(String testName){
        //override method here
        return true;
    }
    
    @Override
    public boolean deleteQuestion(String question){
        //override method here
        return true;
    }
}
