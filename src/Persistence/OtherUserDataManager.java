package Persistence;

import Interfaces.UserDataDao;

public class OtherUserDataManager implements UserDataDao{
    
    @Override
    public String login(String login, String pswd){
        //override method here
        return "";
    }
    
    @Override
    public boolean registration(String name, String login, String pswd){
        //override method here
        return true;
    }
    @Override
    public String getStatistics(String user){
        //override method here
        return "";
    }
    
    
}
