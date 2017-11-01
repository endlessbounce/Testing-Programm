package service;

import Interfaces.TestsDataDao;
import Interfaces.UserDataDao;
import Persistence.OtherTestsDataManager;
import Persistence.OtherUserDataManager;
import Persistence.TestsDataManager;
import Persistence.UserDataManager;

public class Factory {
    
    public UserDataDao createUserDataManager(boolean mode){
        
        if(mode){
            return new UserDataManager();
        }else{
            return new OtherUserDataManager();
        }
        
    }
    
    public TestsDataDao createTestsDataManager(boolean mode){
        
        if(mode){
            return new TestsDataManager();
        }else{
            return new OtherTestsDataManager();
        }
        
    }
}
