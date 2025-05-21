/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.ParseException;
/**
 *
 * @author Dell Vostro
 */
public class BorrowBook extends Book{
    public BorrowBook(){
        super();
        
        setTitle("Borrow Book");
        
        try{
            Overdue over = new Overdue();
        }catch(ParseException ex){
            JOptionPane.showMessageDialog(null, "There is an error occurred during the conversion from String to Date format.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        initGUI();
    }
    
    private void initGUI(){
        Label lblTitle = new Label("Borrow Book");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        Label lblStatement = new Label("Please enter the book name that the client wants to borrow:");
        lblStatement.setFont(new Font("Serif", Font.PLAIN, 24));
        lblStatement.setBounds(240,80,570,30);
        
        pnlDetails.add(lblStatement);
        
        Button btnBorrow = new Button("BORROW BOOK");
        btnBorrow.setBackground(new Color(134,83,55));
        btnBorrow.setForeground(Color.WHITE);
        btnBorrow.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnBorrow.setBounds(720,750,500,50);
        
        pnlDetails.add(btnBorrow);
        
        btnBorrow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (exists == true){
                    if (status == "Available"){
                        setVisible(false);

                        BorrowingDetails bd = new BorrowingDetails(bookName);
                        bd.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "This book is not available for borrowing.", "WARNING", JOptionPane.WARNING_MESSAGE);
                        
                        setVisible(false);
                        
                        BorrowBook bb2 = new BorrowBook();
                        bb2.setVisible(true);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please search a book to borrow.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
