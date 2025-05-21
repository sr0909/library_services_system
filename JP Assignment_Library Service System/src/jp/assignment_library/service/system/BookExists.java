/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
/**
 *
 * @author Dell Vostro
 */
public class BookExists {
    private String bookName;
    boolean bookExists;
    
    public BookExists(String bn){
        bookName = bn;
        bookExists = false;
        
        File inputFile = new File("Book.txt");
        
        try{
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNextLine()){
                String data = input.nextLine();
                
                String[] book = data.split(":");
                String dbBookName = book[1];
                
                if (bookName.equals(dbBookName)){
                    bookExists = true;
                }
            }
            
            input.close();

        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean getBookExists(){
        return bookExists;
    }
}
