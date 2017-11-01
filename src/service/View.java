package service;

public class View {
    private boolean status = true;
    private String input = "0";
    private Test test = new Test();
    private User user = new User();
    private String message = ">>>\"The Testing Programm v1.3.\"";
    
    //MENU FLAGS
    private boolean startMenu = true;
    private boolean userMenu = false;
    private boolean adminMenu = false;
    private boolean testMenu = false;
    
    public boolean getStartMenu(){
        return startMenu;
    }
    
    public void setStartMenu(boolean status){
        startMenu = status;
    }
    
    public boolean getUserMenu(){
        return userMenu;
    }
    
    public void setUserMenu(boolean status){
        userMenu = status;
    }
    
    public boolean getAdminMenu(){
        return adminMenu;
    }
    
    public void setAdminMenu(boolean status){
        adminMenu = status;
    }
    
    public boolean getTestMenu(){
        return testMenu;
    }
    
    public void setTestMenu(boolean status){
        testMenu = status;
    }
    
    public User getUser(){
        return user;
    }
    
    public void setUser(User newUser){
        user = newUser;
    }
    
    public String getInput(){
        return input;
    }
    
    public void setInput(String newInput){
        input = newInput;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String newMessage){
        message = newMessage;
    }
    
    public boolean getStatus(){
        return status;
    }
    
    public void setStatus(boolean newStatus){
        status = newStatus;
    }
    
    public Test getTest(){
        return test;
    }
    
    public void setTest(Test newTest){
        test = newTest;
    }
    
    @Override
    public String toString(){
        return message;
    }
}
