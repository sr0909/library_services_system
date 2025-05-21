/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.io.*;
import javax.swing.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;
/**
 *
 * @author Dell Vostro
 */
public class Overdue{
    private String borrowId, bookName, clientName, borrowDate, returnDate, status, renewTime, extendDate,fine, fineStatus, todayDate, 
                   borrowStatus, oldInfo, exactReturnDate;
    private int maxId;

    public Overdue() throws ParseException{
        File inputFile = new File("Borrow.txt");
        
        try{
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNextLine()){
                String data = input.nextLine();
                
                String[] details = data.split(":");
                String dbId = details[0];

                maxId = Integer.parseInt(dbId);
            }
            
            input.close();
            
            for (int i=0; i < maxId; i++){
                int no = i + 1;
                String num = String.valueOf(no);
                
                Scanner inputBorrow = new Scanner(inputFile);
                
                while(inputBorrow.hasNextLine()){
                    String dataBorrow = inputBorrow.nextLine();

                    String[] borrowDetails = dataBorrow.split(":");
                    String dbId = borrowDetails[0];

                    if (dbId.equals(num)){

                        status = borrowDetails[5];

                        if (status.equals("Borrowing")){
                            borrowId = borrowDetails[0];
                            bookName = borrowDetails[1];
                            clientName = borrowDetails[2];
                            borrowDate = borrowDetails[3];
                            renewTime = borrowDetails[6];
                            returnDate = borrowDetails[4];
                            extendDate = borrowDetails[7];
                            fine = borrowDetails[8];
                            exactReturnDate = borrowDetails[9];
                            
                            oldInfo = borrowId + ":" + bookName + ":" + clientName + ":" + borrowDate + ":" + returnDate + ":" + 
                                      status + ":" + renewTime + ":" + extendDate + ":" + fine + ":" + exactReturnDate;

                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                            Date tDate = new Date();
                            todayDate = dateFormat.format(tDate);

                            Date dToday = dateFormat.parse(todayDate);
                            Calendar c = Calendar.getInstance();
                            c.setTime(dToday);

                            if (extendDate.equals("-")){
                                Date dReturn = dateFormat.parse(returnDate);
                                Calendar c1 = Calendar.getInstance();
                                c1.setTime(dReturn);

                                //Link: https://www.geeksforgeeks.org/compare-dates-in-java/
                                //Date Accessed: 6 March 2021
                                if (c.after(c1)){
                                    borrowStatus = "Overdue";

                                    updateBorrowStatus();
                                }

                            }else{
                                Date dExtend = dateFormat.parse(extendDate);
                                Calendar c2 = Calendar.getInstance();
                                c2.setTime(dExtend);

                                if (c.after(c2)){
                                    borrowStatus = "Overdue";

                                    updateBorrowStatus();
                                }
                            }
                        } 
                    }
                }
                inputBorrow.close();
            }
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Overdue(String oI, String nI){
        String oldString = oI;
        String newString = nI;
        
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
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateBorrowStatus(){
        fineStatus = "Unpaid";
        
        String newInfo = borrowId + ":" + bookName + ":" + clientName + ":" + borrowDate + ":" + returnDate + ":" + 
                         borrowStatus + ":" + renewTime + ":" + extendDate + ":" + fineStatus + ":" + exactReturnDate;

        Overdue over = new Overdue(oldInfo, newInfo);
    }
}
