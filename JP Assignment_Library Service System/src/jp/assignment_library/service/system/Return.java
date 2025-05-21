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
public class Return extends Register{
    Panel pnlTitle, pnlDetails;
    TextField txtSearchName;
    Button btnOk;
    public String borrowId, oldInfo;
    private String searchName, status, bookName, clientName, borrowDate, returnDate, renewTime, extendDate, fineStatus, exactReturnDate;
    public boolean exists, isRenewValid, isOverdue, isPaid;
    
    public Return(){
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
                        
                        ReturnBook returnb2 = new ReturnBook();
                        returnb2.setVisible(true);
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
                                    
                                    if (status.equals("Overdue")){
                                        isOverdue = true;
                                    }else{
                                        isOverdue = false;
                                    }
                                    
                                    if (fineStatus.equals("Paid")){
                                        isPaid = true;
                                    }else{
                                        isPaid = false;
                                    }
                                    
                                    oldInfo = borrowId + ":" + bookName + ":" + clientName + ":" + borrowDate + ":" + returnDate + ":" + 
                                              status + ":" + renewTime + ":" + extendDate + ":" + fineStatus + ":" + exactReturnDate;

                                    Label lblDetails = new Label("Borrowing Details");
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
                                    Label lblextend = new Label("Extend Date: ");
                                    Label lblextendDate = new Label(extendDate);
                                    Label lblf = new Label("Fine Payment Status: ");
                                    Label lblFine = new Label(fineStatus);

                                    if (status.equals("Overdue")){
                                        lblBorrowStatus.setForeground(new Color(255,0,0));
                                    }else if (status.equals("Borrowing")){
                                        lblBorrowStatus.setForeground(new Color(100,200,0));
                                    }
                                    
                                    if (fineStatus.equals("Unpaid")){
                                        lblFine.setForeground(new Color(255,0,0));
                                    }else if (fineStatus.equals("Paid")){
                                        lblFine.setForeground(new Color(100,200,0));
                                    }

                                    lblDetails.setFont(new Font("Serif", Font.BOLD, 34));
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
                                    lblextend.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblextendDate.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblf.setFont(new Font("Serif", Font.PLAIN, 24));
                                    lblFine.setFont(new Font("Serif", Font.PLAIN, 24));
                                    
                                    lblDetails.setBounds(250,200,500,40);
                                    lblClientName.setBounds(250,280,200,30);
                                    lblClient.setBounds(450,280,1200,40);
                                    lblName.setBounds(250,340,200,30);
                                    lblBookName.setBounds(450,340,1200,30);
                                    lblBorrow.setBounds(250,400,200,30);
                                    lblBorrowDate.setBounds(450,400,400,30);
                                    lblReturn.setBounds(1050,400,200,30);
                                    lblReturnDate.setBounds(1350,400,400,30);
                                    lblextend.setBounds(250,460,200,30);
                                    lblextendDate.setBounds(450,460,400,30);
                                    lblrenew.setBounds(1050,460,300,30);
                                    lblrenewTime.setBounds(1350,460,200,30);
                                    lblStatus.setBounds(250,520,200,30);
                                    lblBorrowStatus.setBounds(450,520,400,30);
                                    lblf.setBounds(1050,520,300,30);
                                    lblFine.setBounds(1350,520,200,30);
                                        
                                    pnlDetails.add(lblDetails);
                                    pnlDetails.add(lblClientName);
                                    pnlDetails.add(lblClient);
                                    pnlDetails.add(lblName);
                                    pnlDetails.add(lblBookName);
                                    pnlDetails.add(lblBorrow);
                                    pnlDetails.add(lblBorrowDate);
                                    pnlDetails.add(lblReturn);
                                    pnlDetails.add(lblReturnDate);
                                    pnlDetails.add(lblextend);
                                    pnlDetails.add(lblextendDate);
                                    pnlDetails.add(lblStatus);
                                    pnlDetails.add(lblBorrowStatus);
                                    pnlDetails.add(lblrenew);
                                    pnlDetails.add(lblrenewTime);
                                    pnlDetails.add(lblf);
                                    pnlDetails.add(lblFine);
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

                        ReturnBook rb2 = new ReturnBook();
                        rb2.setVisible(true);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "There is no such a book in the database.", "ERROR", JOptionPane.ERROR_MESSAGE);

                    setVisible(false);

                    ReturnBook rb2 = new ReturnBook();
                    rb2.setVisible(true);
                }
            }
        });
    }
}
