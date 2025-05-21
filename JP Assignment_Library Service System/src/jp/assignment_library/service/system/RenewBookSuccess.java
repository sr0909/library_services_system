/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
/**
 *
 * @author Dell Vostro
 */
public class RenewBookSuccess extends NavBar{
    private String editBorrowId, bookName, clientName, borrowDate, returnDate, status, renewTime, extendDate;
    
    public RenewBookSuccess(){
        super();
        
        setTitle("Edit Book");
        setLayout(new BorderLayout(0,0));
    }
    
    public RenewBookSuccess(String id){
        super();
        
        setTitle("Edit Book");
        setLayout(new BorderLayout(0,0));
        
        editBorrowId = id;
        
        Panel pnlTitle = new Panel();
        Panel pnlDetails = new Panel(null);
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        
        Label lblTitle = new Label("Renew Book");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        ImageIcon renew = new ImageIcon("images/RenewBook.jpg");
        JLabel lblRenew = new JLabel(renew);
        lblRenew.setBounds(400,185,500,400);
        pnlDetails.add(lblRenew);
        
        File inputFile = new File("Borrow.txt");
        
        try{
            Scanner input = new Scanner(inputFile);
      
            while(input.hasNextLine()){
                String data = input.nextLine();
                        
                String[] borrowDetails = data.split(":");
                String dbBorrowID = borrowDetails[0];
                
                if (dbBorrowID.equals(editBorrowId)){
                    bookName = borrowDetails[1];
                    clientName = borrowDetails[2];
                    borrowDate = borrowDetails[3];
                    status = borrowDetails[5];
                    renewTime = borrowDetails[6];
                    extendDate = borrowDetails[7];
                    
                    Label lblDetails = new Label("Borrowing Details");
                    Label lblClientName = new Label("Client Name: ");
                    Label lblClient = new Label(clientName);
                    Label lblName = new Label("Book Name: ");
                    Label lblBookName = new Label(bookName);
                    Label lblBorrow = new Label("Borrow Date: ");
                    Label lblBorrowDate = new Label(borrowDate);
                    Label lblReturn = new Label("Extended Return Date: ");
                    Label lblReturnDate = new Label(extendDate);
                    Label lblStatus = new Label("Borrow Status: ");
                    Label lblBorrowStatus = new Label(status);
                    Label lblrenew = new Label("Number of times of renewing: ");
                    Label lblrenewTime = new Label(renewTime);
                    
                    Button btnRenewOther = new Button("RENEW ANOTHER BOOK");
                    btnRenewOther.setBackground(new Color(134,83,55));
                    btnRenewOther.setForeground(Color.WHITE);
                    btnRenewOther.setFont(new Font("Monospaced", Font.BOLD, 26));
                    btnRenewOther.setBounds(720,750,500,50);
                    
                    lblReturnDate.setForeground(new Color(255,28,141));
                            
                    if (status.equals("Overdue")){
                        lblBorrowStatus.setForeground(new Color(255,0,0));
                    }else if (status.equals("Borrowing")){
                        lblBorrowStatus.setForeground(new Color(100,200,0));
                    }
                    
                    lblDetails.setFont(new Font("Serif", Font.BOLD, 34));
                    lblClientName.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblClient.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblName.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblBookName.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblBorrow.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblBorrowDate.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblReturn.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblReturnDate.setFont(new Font("Serif", Font.BOLD, 24));
                    lblStatus.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblBorrowStatus.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblrenew.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblrenewTime.setFont(new Font("Serif", Font.PLAIN, 24));
                    
                    lblDetails.setBounds(350,100,500,40);
                    lblClientName.setBounds(1000,230,200,30);
                    lblClient.setBounds(1250,230,1200,40);
                    lblName.setBounds(1000,290,200,30);
                    lblBookName.setBounds(1250,290,1200,30);
                    lblBorrow.setBounds(1000,350,200,30);
                    lblBorrowDate.setBounds(1250,350,400,30);
                    lblReturn.setBounds(1000,410,250,30);
                    lblReturnDate.setBounds(1250,410,400,30);
                    lblStatus.setBounds(1000,470,200,30);
                    lblBorrowStatus.setBounds(1250,470,400,30);
                    lblrenew.setBounds(1000,530,300,30);
                    lblrenewTime.setBounds(1300,530,200,30);
                    
                    pnlDetails.add(lblDetails);
                    pnlDetails.add(lblClientName);
                    pnlDetails.add(lblClient);
                    pnlDetails.add(lblName);
                    pnlDetails.add(lblBookName);
                    pnlDetails.add(lblBorrow);
                    pnlDetails.add(lblBorrowDate);
                    pnlDetails.add(lblReturn);
                    pnlDetails.add(lblReturnDate);
                    pnlDetails.add(lblStatus);
                    pnlDetails.add(lblBorrowStatus);
                    pnlDetails.add(lblrenew);
                    pnlDetails.add(lblrenewTime);
                    pnlDetails.add(btnRenewOther);
                    
                    btnRenewOther.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            setVisible(false);
                    
                            RenewBook rbook = new RenewBook();
                            rbook.setVisible(true);
                        }
                    });
                }
            }
            
            input.close();
            
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
