package service;

import service.processor.CentralProcessor;

public class Tester {
    
    public static void main(String[] args) {
        
        View view = new View();
        CentralProcessor proc = new CentralProcessor(view);
        
        while(view.getStatus()){
            proc.processView();
        }
        
    }
    
}
