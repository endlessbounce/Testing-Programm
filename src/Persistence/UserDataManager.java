package Persistence;

import Interfaces.UserDataDao;
import java.sql.SQLException;

public class UserDataManager  extends DataManager implements UserDataDao{
    
    @Override
    public String login(String login, String pswd){
        String name = null;
        try {
            query = "SELECT Username FROM users where Login = '" + login + "' AND Password = '" + pswd + "';";
            rs = stmnt.executeQuery(query);
            if(rs.next()){
                name = rs.getString("Username");
            }
        } catch (SQLException ex) {
        }
        return name;
    }
    
    @Override
    public boolean registration(String name, String login, String pswd){
        int updatedRecordsNumber = 0;
        try{
            query = "INSERT INTO users (Username, Login, Password) VALUES ('" + name + "', '" 
                + login + "', '" + pswd + "');";
            updatedRecordsNumber = stmnt.executeUpdate(query);
        }catch(SQLException ex){
        }
        return updatedRecordsNumber == 1;
    }
    
    @Override
    public String getStatistics(String user){
        String stats = "";
        int count = 0;
        
        if(user.equals("admin")){
            query = "SELECT Login, testresults.TestName, `Date`, Score FROM testresults;";
        }else{
            query = "SELECT Login, testresults.TestName, `Date`, Score FROM testresults WHERE Login = '"
                    + user + "';";
        }

        try {
            rs = stmnt.executeQuery(query);
            while(rs.next()){
                stats += (++count) + ". Login: " + rs.getString("Login") + "; test: " + rs.getString("TestName") + "; date: " +
                        rs.getString("Date") + "; score: " + rs.getInt("Score") + "\n";
            }
        } catch (SQLException ex) {
        }
        
        return stats;
    }
}
