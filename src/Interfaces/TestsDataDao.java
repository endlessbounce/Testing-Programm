package Interfaces;

import java.util.ArrayList;
import service.Question;
import service.Test;
import service.View;

public interface TestsDataDao {
    public ArrayList<String> getAllTests();
    public ArrayList<Question> getQuestions(String testName);
    public Test getTest(String testName);
    public boolean saveTestResults(View view);
    public int getTestNumber(String testName);
    public boolean deleteTest(String testName);
    public boolean addQuestion(Question question, String testName);
    public boolean addTest(String testName);
    public boolean deleteQuestion(String question);
}
