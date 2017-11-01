package service.processor;
import java.util.ArrayList;
import service.Question;
import service.User;
import service.View;

public class CentralProcessor extends Processor{
    private SubProcessor subProc;
    private String input;
    private User user;
    private String login, stats;
    
    public CentralProcessor(View view){
        super(view);
        subProc = new SubProcessor(view);
    }
    
    public void processView(){
        user = view.getUser();
        input = view.getInput();
        
        if(view.getStartMenu()){
            processStartMenu();
        }else if(view.getUserMenu()){
            processUserMenu();
        }else if(view.getAdminMenu()){
            processAdminMenu();
        }else if(view.getTestMenu()){
            processTestMenu();
        }
        
    }
    
    public void processStartMenu(){
        if(input.equals("1")){
            subProc.registration();
            view.setInput("0");
        }else if(input.equals("2")){
            subProc.login();
            view.setInput("0");
        }else{
            view.setMessage("Please enter:\n" +
                            "'1' - Create new account;\n" +
                            "'2' - Log in;\n" +
                            "'quit' - Quit the programm any time you need.");
            
            indicator.show();//DYSPLAY MESSAGE
            Integer num = indicator.readNumber(2);
            view.setInput(num.toString());
        }
    }
    
    public void processUserMenu(){
        
        if(input.equals("1")){
            login = user.getLogin();
            stats = userDataManager.getStatistics(login);
            
            if(!stats.equals("")){
                view.setMessage(">>>Your statistics:\n" + stats);
            }else{
                view.setMessage(">>>There are no tests implemented yet...");
            }
            
            indicator.show();//DYSPLAY MESSAGE
            view.setInput("0");
        }else if(input.equals("2")){
            subProc.testingPresettings();//CHOOSE A TEST
            
            if(noQuestionsCheck()){
                return;
            }
            
            view.setTestMenu(true);
            view.setUserMenu(false);
            view.setInput("0");
        }else if(input.equals("3")){
            subProc.logoff();
            view.setInput("0");
        }else{

            view.setMessage(">>>Please, for further actions, enter:\n" +
                            "'1' - Show my statistics;\n" +
                            "'2' - Testing mode;\n" +
                            "'3' - Log off;\n" +
                            "'quit' - Quit the programm any time you need.");
            
            indicator.show();//DYSPLAY MESSAGE
            Integer num = indicator.readNumber(3);
            view.setInput(num.toString());
        }
    }
    
    public void processAdminMenu(){
        if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("5")){
            subProc.testingPresettings();//CHOOSE A TEST
        }

        if(input.equals("1")){
            Question question = indicator.addQuestionSequence();
            boolean added = testsDataManager.addQuestion(question, view.getTest().getName());
            
            if(added){
                view.setMessage(">>>The question has been added successfully.");
            }else{
                view.setMessage(">>>The question has not been added. Please, try again.");
            }
            
            indicator.show();//DYSPLAY MESSAGE
            view.setInput("0");
        }else if(input.equals("2")){
            
            if(noQuestionsCheck()){
                return;
            }
            
            ArrayList<Question> questions = testsDataManager.getQuestions(view.getTest().getName());
            String message = "";
            
            for(int i = 0; i < questions.size(); i++){
                message += (i+1) + ". " + questions.get(i).getQuestion() + "\n";
            }
            
            view.setMessage(">>>Please, choose a question:\n" + message);
            indicator.show();
            Integer questToDelete = indicator.readNumber(questions.size());
            boolean deleted = testsDataManager.deleteQuestion(questions.get(questToDelete-1).getQuestion());
            
            if(deleted){
                view.setMessage(">>>The question has been deleted successfully.");
            }else{
                view.setMessage(">>>The question has not been deleted. Please, try again.");
            }
            
            indicator.show();//DYSPLAY MESSAGE
            view.setInput("0");
        }else if(input.equals("3")){
            String testName = view.getTest().getName();
            boolean deleted = testsDataManager.deleteTest(testName);
            
            if(deleted){
                view.setMessage(">>>The test has been deleted successfully.");
            }else{
                view.setMessage(">>>The test has not been deleted. Please, try again.");
            }
            
            indicator.show();//DYSPLAY MESSAGE
            view.setInput("0");
        }else if(input.equals("4")){
            login = user.getLogin();
            stats = userDataManager.getStatistics(login);
            view.setMessage(">>>Statistics of all users:\n" + stats);
            indicator.show();//DYSPLAY MESSAGE
            view.setInput("0");
        }else if(input.equals("5")){
            
            if(noQuestionsCheck()){
                return;
            }
            
            view.setTestMenu(true);
            view.setAdminMenu(false);
            view.setInput("0");
        }else if(input.equals("6")){
            subProc.logoff();
            view.setInput("0");
        }else if(input.equals("7")){
            view.setMessage(">>>Enter the name of new test:");
            indicator.show();
            String newTest = indicator.readLine();
            boolean added = testsDataManager.addTest(newTest);
            
            if(added){
                view.setMessage(">>>The test has been added successfully.");
            }else{
                view.setMessage(">>>The test has not been added. Please, try again.");
            }
            
            indicator.show();//DYSPLAY MESSAGE
            view.setInput("0");
        }else{
            view.setMessage(">>>Please, for further actions, enter:\n" +
                            "'1' - Add new question;\n" +
                            "'2' - Delete a question;\n" +
                            "'3' - Delete a test;\n" +
                            "'4' - Show statistics of all users;\n" +
                            "'5' - Testing mode;\n" +
                            "'6' - Log off;\n" +
                            "'7' - Add new test;\n" +
                            "'quit' - Quit the programm any time you need");
            
            indicator.show();//DYSPLAY MESSAGE
            Integer num = indicator.readNumber(7);
            view.setInput(num.toString());
        }
    }
    
    public void processTestMenu(){
        indicator.testSequence();
            
        if(testsDataManager.saveTestResults(view)){
            view.setMessage(">>>Results have been saved successfully.");
        }else{
            view.setMessage(">>>Results have not been saved.");
        }

        if(view.getUser().getLogin().equalsIgnoreCase("admin")){
            view.setAdminMenu(true);
        }else{
            view.setUserMenu(true);
        }

        view.setTestMenu(false);
    }
    
    public boolean noQuestionsCheck(){
        
        if(view.getTest().getQuestions().isEmpty()){
            view.setMessage(">>>There are no questions in this test yet...");

            indicator.show();//DYSPLAY MESSAGE
            view.setInput("0");
            return true;
        }
        return false;
    }
}
