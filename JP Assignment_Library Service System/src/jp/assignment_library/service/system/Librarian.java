/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Dell Vostro
 */
public class Librarian extends Register{
    public String oldInfo;
    private String Id, librarianId, fullname, username, pass, gender, intakeCode, email, phoneNum, role, oldString, newString;
    
    public Librarian(){
        super();
        
        setVisible(false);
    }
    
    public Librarian(String id){
        super();
        
        setVisible(false);
        
        librarianId = id;
        
        getLibrarianDetails();
        
        try{
            FileWriter fw = new FileWriter("Login.txt", true);
            PrintWriter outputFile = new PrintWriter(fw);
                                
            String loginInfo = librarianId + ":" + fullname + ":" + username + ":" + pass + ":" + gender + ":" + intakeCode + ":" +
                               email + ":" + phoneNum + ":" + role;
            outputFile.println(loginInfo);

            outputFile.close();

        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getLibrarianDetails(){
        File inputFile = new File("Librarian.txt");
        
        try{
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNextLine()){
                String data = input.nextLine();
                
                String[] details = data.split(":");
                String dbId = details[0];

                if (dbId.equals(librarianId)){
                    fullname = details[1];
                    username = details[2];
                    pass = details[3];
                    gender = details[4];
                    intakeCode = details[5];
                    email = details[6];
                    phoneNum = details[7];
                    role = details[8];
                }
            }
            
            input.close();

        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
