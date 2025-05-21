/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.util.Scanner;
import java.util.regex.*;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author Dell Vostro
 */
public class NameValidation {
    private String name, filename;
    private boolean match;
    private boolean valid = true;
    
    public NameValidation(String n){
        name = n;
        
        //Link: https://www.tutorialspoint.com/name-validation-using-java-regular-expressions
        //Date Accessed: 4 March 2021
        final String regex = "(\\p{Upper}(\\p{Lower}+\\s?)){1,10}";
        
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(name);
        
        match = matcher.matches();
    }
    
    public NameValidation(String n, String f){
        name = n;
        filename = f;
        
        File inputFile = new File(filename);
        
        try{
            Scanner input = new Scanner(inputFile);

            while(input.hasNextLine()){
                String data = input.nextLine();

                String[] details = data.split(":");
                String dbName = details[1];

                if(name.equals(dbName)){
                    valid = false;
                }
            }

            input.close();
            
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean getNameValidation(){
        return match;
    }
    
    public boolean getNameExists(){
        return valid;
    }
}
