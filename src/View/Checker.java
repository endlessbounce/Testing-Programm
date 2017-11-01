package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Checker {
    
    public String readLine(){
        String line = null;
        Boolean toggle = true;
        
        try{
            while(toggle){
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                line = in.readLine();
                if(line.length() == 0){
                    System.out.println(">>>Please enter correct value.");
                    continue;
                }else if(line.equals("quit")){
                    System.exit(0);
                }
                toggle = false;
            }
        }catch(IOException ex){
            System.out.println(">>>Input error.");
        }
        
        return line;
    }
    
    public int readNumber(int maxOptionsNumber){
        int num = 0;
        Boolean toggle = true;

        while(toggle){
            try{
                num = Integer.parseInt(readLine());
                if(num > maxOptionsNumber || num <= 0){
                    System.out.println(">>>Enter correct value:");
                    continue;
                }
                toggle = false;
            }catch(NumberFormatException nfe){
                System.out.println(">>>Enter correct value:");
            }
        }
        return num;
    }
    
    //INSERTS A "'" SYMBOL TO AVOID ERRORS IN QUERIES
    public String correctInputCheck(String text){
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == 39){
                text = text.substring(0, i) + "'" + text.substring(i);
                i++;
            }
        }
        return text;
    }
}
