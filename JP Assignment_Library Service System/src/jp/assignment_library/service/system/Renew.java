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
public class Renew extends Register{
    Panel pnlTitle, pnlDetails;
    TextField txtSearchName;
    Button btnOk;
    public String borrowId, oldInfo;
    private String searchName, status, bookName, clientName, borrowDate, returnDate, renewTime, extendDate, fineStatus, exactReturnDate;
    public boolean exists, isRenewValid, isOverdue;
    
    public Renew(){
        super();
        
        setLayout(new BorderLayout(0,0));
        
        initGUI();
    }
    
    private void initGUI(){
        pnlTitle = new Panel();
        pnlDetails = new Panel(null);
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");

        txtSearchName = new TextField(30);
        txtSearchName.setFont(new Font("Serif", Font.PLAIN, 24));
        txtSearchName.setBounds(820,75,600,40);
        
        btnOk = new Button("OK");
        btnOk.setBackground(new Color(134,83,55));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnOk.setBounds(1430,75,200,40);

        pnlDetails.add(txtSearchName);
        pnlDetails.add(btnOk);
        
        btnOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                searchName = txtSearchName.getText();

                btnOk.setVisible(false);
                Button btnFindOther = new Button("FIND ANOTHER");
                btnFindOther.setBackground(new Color(134,83,55));
                btnFindOther.setForeground(Color.WHITE);
                btnFindOther.setFont(new Font("Monospaced", Font.BOLD, 26));
                btnFindOther.setBounds(1430,75,250,40);
                
                pnlDetails.add(btnFindOther);
                 
                btnFindOther.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        setVisible(false);
                        
                        RenewBook rb2 = new RenewBook();
                        rb2.setVisible(true);
                    }
                });
                
                BookExists be = new BookExists(searchName);
                boolean isBookExists = be.getBookExists();
                
                if (isBookExists == true){
                    File inputFile = new File("Borrow.txt");

                    try{
                        Scanner input = new Scanner(inputFile);

                        exists = false;

                        while(input.hasNextLine()){
                            String data = input.nextLine();

                            String[] borrowDetails = data.split(":");
                            String dbBookName = borrowDetails[1];
                            String dbStatus = borrowDetails[5];

                            if (searchName.equals(dbBookName)){
                                if (dbStatus.equals("Borrowing") || dbStatus.equals("Overdue")){
                                    exists = true;
                                    
                                    borrowId = borrowDetails[0];
                                    bookName = dbBookName;
                                    clientName = borrowDetails[2];
                                    borrowDate = borrowDetails[3];
                                    returnDate = borrowDetails[4];
                                    status = dbStatus;
                                    renewTime = borrowDetails[6];
                                    extendDate = borrowDetails[7];
                                    fineStatus = borrowDetails[8];
                                    exactReturnDate = borrowDetails[9];
                                    
                                    int rtime = Integer.parseInt(renewTime);
                                    
                                    if (rtime == 0){
                                        isRenewValid = true;
                                    }else{
                                        isRenewValid = false;
                                    }
                                    
                                    if (status.equals("Overdue")){
                                        isOverdue = true;
                                    }else{
                                        isOverdue = false;
                                    }
                                    
                                    oldInfo = borrowId + ":" + bookName + ":" + clientName + ":" + borrowDate + ":" + returnDate + ":" + 
                                              status + ":" + renewTime + ":" + extendDate + ":" + fineStatus + ":" + exactReturnDate;

                                    Label lblClientName = new Label("Client Name: ");
                                    Label lblClient = new Label(clientName);
                                    Label lblName = new Label("Book Name: ");
                                    Label lblBookName = new Label(bookName);
                                    Label lblBorrow = new Label("Borrow Date: ");
                                    Label lblBorrowDate = new Label(borrowDate);
                                    Label lblReturn = new Label("Return Date: ");
                                    Label lblReturnDate = new Label(returnDate);
                                    Label lblStatus = new Label("Borrow Status: ");
                                    Label lblBorrowStatus = new Label(status);
                                    Label lblrenew = new Label("Number of times of renewing: ");
                                    Label lblrenewTime = new Label(renewTime);

                                    if (status.equals("Overdue")){
                                        lblBorrowStatus.setForeground(new Color(255,0,0));
                                    }else if (status.equals("Borrowing")){
                                        lblBorrowStatus.setForeground(new Color(100,200,0));
                                    }

                                    lblClientName.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblClient.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblName.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblBookName.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblBorrow.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblBorrowDate.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblReturn.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblReturnDate.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblStatus.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblBorrowStatus.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblrenew.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblrenewTime.setFont(new Font("Serif", Font.PLAIN, 24));

                                    lblClientName.setBounds(1000,280,200,30);
                                    lblClient.setBounds(1200,280,1200,40);
                                    lblName.setBounds(1000,340,200,30);
                                    lblBookName.setBounds(1200,340,1200,30);
                                    lblBorrow.setBounds(1000,400,200,30);
                                    lblBorrowDate.setBounds(1200,400,400,30);
                                    lblReturn.setBounds(1000,460,200,30);
                                    lblReturnDate.setBounds(1200,460,400,30);
                                    lblStatus.setBounds(1000,520,200,30);
                                    lblBorrowStatus.setBounds(1200,520,400,30);
                                    lblrenew.setBounds(1000,580,300,30);
                                    lblrenewTime.setBounds(1300,580,200,30);

                                    
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
                                }
                            }
                        }

                        input.close();

                    }catch(FileNotFoundException ex){
                        JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                    if (exists == false){
                        JOptionPane.showMessageDialog(null, "This book is not borrowed to the client.", "ERROR", JOptionPane.ERROR_MESSAGE);

                        setVisible(false);

                        RenewBook rb2 = new RenewBook();
                        rb2.setVisible(true);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "There is no such a book in the database.", "ERROR", JOptionPane.ERROR_MESSAGE);

                    setVisible(false);

                    RenewBook rb2 = new RenewBook();
                    rb2.setVisible(true);
                }
            }
        });
    }
}
