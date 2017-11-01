package service;

public class User {
    private String name;
    private String login;
    private String password;
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public String getLogin(){
        return login;
    }
    
    public void setLogin(String newLogin){
        login = newLogin;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String newPassword){
        password = newPassword;
    }
}
