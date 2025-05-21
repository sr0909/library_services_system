/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;
/**
 *
 * @author Dell Vostro
 */
public class Register extends NavBar{
    private String filename, bookId;
    private String ID = "";
    private int id = 0;
    private int editId = 0;
    
    public Register(){
        super();
    }

    public Register(String fn) throws IOException {
        super();
        
        setVisible(false);
        
        filename = fn;

        File inputFile = new File(filename);
        
        try{
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNextLine()){
                String data = input.nextLine();
                
                String[] librarianDetails = data.split(":");
                id = Integer.parseInt(librarianDetails[0]);
            }
            
            input.close();
            
            id = id + 1;
            ID = String.valueOf(id);
            
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Register(String fn, String bID) throws IOException {
        super();
        
        setVisible(false);
        
        filename = fn;
        bookId = bID;
        
        File inputFile = new File(filename);
        
        try{
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNextLine()){
                String data = input.nextLine();
                
                String[] details = data.split(":");
                String dbId = details[0];

                if (dbId.equals(bookId)){
                    editId = Integer.parseInt(dbId);
                }
            }
            
            input.close();

        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getId(){
        return ID;
    }
    
    public int getEditId(){
        return editId;
    }
}
