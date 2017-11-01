package service.processor;

import Interfaces.TestsDataDao;
import Interfaces.UserDataDao;
import View.Indicator;
import service.Factory;
import service.View;

public abstract class Processor {
    protected View view;
    protected Indicator indicator;
    
    private Factory factory = new Factory();
    protected UserDataDao userDataManager = factory.createUserDataManager(true);
    protected TestsDataDao testsDataManager = factory.createTestsDataManager(true);
    
    public Processor(View view){
        this.view = view;
        this.indicator = new Indicator(view);
    }
}
