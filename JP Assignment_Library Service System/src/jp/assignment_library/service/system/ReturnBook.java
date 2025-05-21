/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author Dell Vostro
 */
public class ReturnBook extends Return{
    private String bookName, clientName, borrowDate, returnDate, renewTime, extendDate, fineStatus, oldString, newString;
    
    public ReturnBook(){
        super();
        
        setTitle("Borrow Book");
        
        try{
            Overdue over = new Overdue();
        }catch(ParseException ex){
            JOptionPane.showMessageDialog(null, "There is an error occurred during the conversion from String to Date format.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        initGUI();
    }
    
    public ReturnBook(String oI, String nI){
        oldString = oI;
        newString = nI;
        
        //Link: https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/
        //Date Accessed: 5 March 2021
        File modifyFile = new File("Borrow.txt");
        
        String oldDetails = "";
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(modifyFile));
            
            //Read all the lines of the text file
            String line = reader.readLine();
            
            while (line != null){
                oldDetails = oldDetails + line + System.lineSeparator();
                
                line = reader.readLine();
            }
            
            //Replace oldString with newString in newDetails
            String newDetails = oldDetails.replaceAll(oldString, newString);
            
            FileWriter writer = new FileWriter(modifyFile);
            
            writer.write(newDetails);
            
            //Close the resources
            reader.close();
            
            writer.close();
            
            JOptionPane.showMessageDialog(null, "This book is returned successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            
            setVisible(false);
            
            ReturnBook rb2 = new ReturnBook();
            rb2.setVisible(true);
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initGUI(){
        Label lblTitle = new Label("Return Book");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        Label lblStatement = new Label("Please enter the book name that the client wants to return:");
        lblStatement.setFont(new Font("Serif", Font.PLAIN, 24));
        lblStatement.setBounds(240,80,570,30);
        
        pnlDetails.add(lblStatement);

        Button btnReturn = new Button("RETURN BOOK");
        btnReturn.setBackground(new Color(134,83,55));
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnReturn.setBounds(720,750,500,50);
        
        pnlDetails.add(btnReturn);
        
        btnReturn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (exists == true){
                    if (isOverdue == true){
                        if (isPaid == true){
                            updateReturnStatus();
                        }else{
                            JOptionPane.showMessageDialog(null, "Please pay the fine first to return the book.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        updateReturnStatus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please search a book to return.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void updateReturnStatus(){
        String status = "Returned";
        String exactReturnDate;
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        exactReturnDate = formatter.format(todayDate);
        
        File inputFile = new File("Borrow.txt");

        try{
            Scanner input = new Scanner(inputFile);

            while(input.hasNextLine()){
                String data = input.nextLine();

                String[] borrowDetails = data.split(":");
                String dbId = borrowDetails[0];

                if (borrowId.equals(dbId)){
                    bookName = borrowDetails[1];
                    clientName = borrowDetails[2];
                    borrowDate = borrowDetails[3];
                    returnDate = borrowDetails[4];
                    renewTime = borrowDetails[6];
                    extendDate = borrowDetails[7];
                    fineStatus = borrowDetails[8];
                }
            }

            input.close();

        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        String newInfo = borrowId + ":" + bookName + ":" + clientName + ":" + borrowDate + ":" + returnDate + ":" + 
                         status + ":" + renewTime + ":" + extendDate + ":" + fineStatus + ":" + exactReturnDate;
        
        setVisible(false);
        
        ReturnBook returnBook = new ReturnBook(oldInfo, newInfo);
    }
}
