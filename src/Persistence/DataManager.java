package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DataManager {
    protected Connection con;
    protected Statement stmnt;
    protected ResultSet rs;
    protected final String url = "jdbc:mysql://localhost:3306/testerdatabase";
    protected final String user = "root";
    protected final String password = "12345";
    protected String query;
    
    public DataManager(){
        try{
            con = DriverManager.getConnection(url, user, password);
            stmnt = con.createStatement();
        }catch(SQLException ex){
        }
    }
    
    public int recordsCounter(String tableName){
        int recordsNumber = 0;
        
        try{
            query = "SELECT count(*) AS num FROM " + tableName + ";";
            rs = stmnt.executeQuery(query);
            
            if(rs.next()){
                recordsNumber = rs.getInt("num");
            }
            
        }catch(SQLException ex){
        }
        return recordsNumber;
    }
    
}
