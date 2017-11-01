package View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import service.Question;
import service.Test;
import service.View;

public class Indicator {
    private Checker checker = new Checker();
    private View view;
    
    public Indicator(View view){
        this.view = view;
    }
    
    //READS LOGIN, PASSWORD, SETS IT TO USER PARAMETERS
    public void loginSequence(){
        System.out.println(">>>Please, enter your login:");
        view.getUser().setLogin(checker.readLine());
        System.out.println(">>>Enter your password:");
        view.getUser().setPassword(checker.readLine());
    }
    
    //READS NAME, LOGIN, CHECKS PASSWORDS, SETS IT TO USER PARAMETERS
    public void regSequence(){
        String pswd = null, pswdCheck = null;
        boolean pswdsNotEqual = true;
        
        System.out.println(">>>Please, enter your name:");
        view.getUser().setName(checker.readLine());
        System.out.println(">>>Enter your login:");
        view.getUser().setLogin(checker.readLine());
        
        while(pswdsNotEqual){
            System.out.println(">>>Enter your password:");
            pswd = checker.readLine();
            System.out.println(">>>Repeat your password:");
            pswdCheck = checker.readLine();
            
            if(pswd.equals(pswdCheck)){
                pswdsNotEqual = false;
            }else{
                System.out.println(">>>The passwords are not equal.");
            }
        }
        
        view.getUser().setPassword(pswd);
    }
    
    //IMPLEMENTS TESTING
    public void testSequence(){
        Test test = view.getTest();
        ArrayList<Question> questArr = test.getQuestions();
        int numOfQuestions = questArr.size();
        int correctAnswers = 0;

        for(int i = 0; i < questArr.size(); i++){
            Question question = questArr.get(i);
            ArrayList<String> answersArr = question.getAnswers();
            int numOfAnswers = answersArr.size() + 1;
            String answers = "";

            for(int j = 0; j < answersArr.size(); j++){
                answers += (j+1) + ". " + answersArr.get(j) + "\n";
            }
            
            view.setMessage((i+1) + ". " + question.getQuestion() + "\n" + answers);
            show();//DYSPLAY MESSAGE
            Integer givenAnswer = readNumber(numOfAnswers);
            
            if(question.getRAnswer() == givenAnswer){
                correctAnswers++;
                view.setMessage("Correct answer!");
                show();//DYSPLAY MESSAGE
            }else{
                view.setMessage("Incorrect answer. The right one is: " + question.getRAnswer());
                show();//DYSPLAY MESSAGE
            }
        }
        test.setResult((int)(correctAnswers * 100.0 / numOfQuestions));
        test.setDate(LocalDateTime.now().toString());
        
        view.setMessage("Your result: " + test.getResult());
        show();//DYSPLAY MESSAGE
    }
    
    public Question addQuestionSequence(){
        Question question = new Question();
        ArrayList<String> answers = question.getAnswers();
        String text = null;
        
        System.out.println(">>>Please, enter question: ");
        text = checker.correctInputCheck(checker.readLine());
        question.setQuestion(text);
        
        for(int i = 0; i < 4; i++){
            System.out.println(">>>Enter answer " + (i+1) + ": ");
            text = checker.correctInputCheck(checker.readLine());
            answers.add(text);
        }
        
        System.out.println(">>>Enter right answer: ");
        question.setRAnswer(readNumber(4));
        
        return question;
    }
    
    public int readNumber(int maxOptionsNumber){
        return checker.readNumber(maxOptionsNumber);
    }
    
    public String readLine(){
        return checker.correctInputCheck(checker.readLine());
    }
    
    public void show(){
        System.out.println(view.toString());
    }
}
