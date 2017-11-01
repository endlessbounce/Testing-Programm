package service.processor;

import java.util.ArrayList;
import service.Test;
import service.User;
import service.View;

public class SubProcessor extends Processor{
    
    public SubProcessor(View view){
        super(view);
    }
    
    //ADDS INFO ABOUT NEW USER TO THE DATABASE
    public void registration(){
        String login, pswd, name;
        User user = view.getUser();
        
        indicator.regSequence();
        
        login = user.getLogin();
        pswd = user.getPassword();
        name = user.getName();
        boolean registered = userDataManager.registration(name, login, pswd);

        if(registered){
            view.setMessage(">>>User registered successfully. Please log on.");
        }else{
            view.setMessage(">>>Registration failed. Please try again.");
        }

        view.setInput("0");
        indicator.show();//DYSPLAY MESSAGE
    }
    
    //LOGS IN
    public void login(){
        String login, pswd, name;
        User user = view.getUser();      
        
        indicator.loginSequence();
        
        login = user.getLogin();
        pswd = user.getPassword();
        name = userDataManager.login(login, pswd);

        if(name != null){
            user.setName(name);
            view.setMessage(">>>User logged in successfully.");
            
            view.setStartMenu(false);
            if(login.equals("admin")){
                view.setAdminMenu(true);
            }else{
                view.setUserMenu(true);
            }
            
        }else{
            view.setMessage(">>>Failed to log in. Please try again.");
        }

        view.setInput("0");
        indicator.show();//DYSPLAY MESSAGE
    }
    
    //LOGS OFF
    public void logoff(){
        view.setUser(new User());
        view.setStartMenu(true);
        view.setAdminMenu(false);
        view.setUserMenu(false);       
        view.setMessage(">>>User logged off.");
        indicator.show();//DYSPLAY MESSAGE
    }
    
    //DISPLAYS TESTS AVAILABLE, GETS CHOSEN TEST
    public void testingPresettings(){
        ArrayList<String> allTests = testsDataManager.getAllTests();
        int numberOfTests = allTests.size();
        String tests = "";

        for(int i = 0; i < allTests.size(); i++){
            tests += (i+1) + ". " + allTests.get(i) + "\n";
        }

        view.setMessage(">>>Please choose a test:\n" + tests);
        indicator.show();
        int chosenNum = indicator.readNumber(numberOfTests);
        String chosenTest = allTests.get(chosenNum-1);
        Test test = testsDataManager.getTest(chosenTest);
        view.setTest(test);

    }
    
}
