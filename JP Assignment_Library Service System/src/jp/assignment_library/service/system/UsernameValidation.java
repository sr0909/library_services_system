/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Dell Vostro
 */
public class UsernameValidation {
    private String username;
    private boolean valid = true;
    
    public UsernameValidation(String u){
        username = u;
        
        File inputFile = new File("Librarian.txt");
        
        try{
            Scanner input = new Scanner(inputFile);

            while(input.hasNextLine()){
                String data = input.nextLine();

                String[] details = data.split(":");
                String dbUsername = details[2];

                if(username.equals(dbUsername)){
                    valid = false;
                }
            }

            input.close();
            
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean getUsernameValidation(){
        return valid;
    }
}
