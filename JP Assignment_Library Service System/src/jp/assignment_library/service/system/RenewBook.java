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
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
/**
 *
 * @author Dell Vostro
 */
public class RenewBook extends Renew{
    private String bookName, clientName, borrowDate, returnDate, status, renewTime, fineStatus, extendDate, oldString, newString, 
                   editBorrowID, exactReturnDate;
    
    public RenewBook(){
        super();
        
        setTitle("Borrow Book");
        
        try{
            Overdue over = new Overdue();
        }catch(ParseException ex){
            JOptionPane.showMessageDialog(null, "There is an error occurred during the conversion from String to Date format.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    
        initGUI();
    }
    
    public RenewBook(String oI, String nI, String id){
        oldString = oI;
        newString = nI;
        editBorrowID = id;
        
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
            
            JOptionPane.showMessageDialog(null, "The return date of this book is extended successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            
            setVisible(false);

            RenewBookSuccess success = new RenewBookSuccess(editBorrowID);
            success.setVisible(true);
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initGUI(){
        Label lblTitle = new Label("Renew Book");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        Label lblStatement = new Label("Please enter the book name that the client wants to renew:");
        lblStatement.setFont(new Font("Serif", Font.PLAIN, 24));
        lblStatement.setBounds(240,80,570,30);
        
        pnlDetails.add(lblStatement);
        
        Label lblDetails = new Label("Borrowing Details");
        lblDetails.setFont(new Font("Serif", Font.BOLD, 34));
        lblDetails.setBounds(350,180,500,40);
        
        ImageIcon renew = new ImageIcon("images/RenewBook.jpg");
        JLabel lblRenew = new JLabel(renew);
        lblRenew.setBounds(400,250,500,400);
        
        pnlDetails.add(lblDetails);
        pnlDetails.add(lblRenew);

        Button btnRenew = new Button("RENEW BOOK");
        btnRenew.setBackground(new Color(134,83,55));
        btnRenew.setForeground(Color.WHITE);
        btnRenew.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnRenew.setBounds(720,750,500,50);
        
        pnlDetails.add(btnRenew);
        
        btnRenew.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (exists == true){
                    if (isRenewValid == true){
                        if (isOverdue == true){
                            JOptionPane.showMessageDialog(null, "Please pay the fine first to renew.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            
                        }else{
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
                                        status = borrowDetails[5];
                                        renewTime = borrowDetails[6];
                                        fineStatus = borrowDetails[8];
                                        exactReturnDate = borrowDetails[9];
                                    }
                                }

                                input.close();

                            }catch(FileNotFoundException ex){
                                JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }

                            //Link: https://beginnersbook.com/2017/10/java-add-days-to-date/
                            //Date Accessed: 6 March 2021
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            Calendar c = Calendar.getInstance();

                            try{
                                c.setTime(dateFormat.parse(returnDate));
                            }catch(ParseException ex){
                                JOptionPane.showMessageDialog(null, "There is an error occurred during the conversion from String to Date format.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }

                            c.add(Calendar.DAY_OF_MONTH, 7);

                            extendDate = dateFormat.format(c.getTime());

                            String newInfo = borrowId + ":" + bookName + ":" + clientName + ":" + borrowDate + ":" + returnDate + ":" + 
                                             status + ":1:" + extendDate + ":" + fineStatus + ":" + exactReturnDate;

                            setVisible(false);

                            RenewBook rbook = new RenewBook(oldInfo, newInfo, borrowId);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "This book had been renewed already.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please search a book to renew.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
     }
}
