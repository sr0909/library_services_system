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
public class Book extends Register{
    Panel pnlTitle, pnlDetails;
    TextField txtSearchName;
    Button btnOk;
    public String id, status, bookName;
    private String searchName, isbn, date, author, publisher, category;
    public boolean exists;
    
    public Book(){
        super();
        
        setLayout(new BorderLayout(0,0));
        
        initGUI();
    }

    private void initGUI(){
        pnlTitle = new Panel();
        pnlDetails = new Panel(null);
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));

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
                        
                        BorrowBook bb2 = new BorrowBook();
                        bb2.setVisible(true);
                    }
                });

                File inputFile = new File("Book.txt");
        
                try{
                    Scanner input = new Scanner(inputFile);

                    exists = false;
                    
                    while(input.hasNextLine()){
                        String data = input.nextLine();
                        
                        String[] bookDetails = data.split(":");
                        String dbBookName = bookDetails[1];
                        
                        if (searchName.equals(dbBookName)){
                            exists = true;

                            id = bookDetails[0];
                            bookName = bookDetails[1];
                            isbn = bookDetails[2];
                            date = bookDetails[3];
                            author = bookDetails[4];
                            publisher = bookDetails[5];
                            category = bookDetails[7];
                            status = "Available";
                            
                            File inputFileBorrow = new File("Borrow.txt");
                            
                            Scanner inputBorrow = new Scanner(inputFileBorrow);
                            
                            while (inputBorrow.hasNextLine()){
                                String borrow = inputBorrow.nextLine();
                                
                                String[] borrowDetails = borrow.split(":");
                                String dbName = borrowDetails[1];
                                String dbStatus = borrowDetails[5];
                                
                                if (searchName.equals(dbName)){
                                    if (dbStatus.equals("Borrowing")){
                                        status = "Not Available";
                                    }else if (dbStatus.equals("Returned")){
                                        status = "Available";
                                    }else if (dbStatus.equals("Overdue")){
                                        status = "Not Available";
                                    }
                                }
                            }
                            
                            inputBorrow.close();
                         
                            Label lblDetails = new Label("Book Details");
                            Label lblId = new Label("Book ID: ");
                            Label lblBookId = new Label(id);
                            Label lblisbn = new Label("ISBN: ");
                            Label lblIsbn = new Label(isbn);
                            Label lblBookName = new Label("Book Name: ");
                            Label lblName = new Label(bookName);
                            Label lblCategory = new Label("Book Category: ");
                            Label lblcat = new Label(category);
                            Label lblDate = new Label("Published Date: ");
                            Label lbldate = new Label(date);
                            Label lblAuthor = new Label("Author Name: ");
                            Label lblauthor = new Label(author);
                            Label lblPublisher = new Label("Publisher: ");
                            Label lblpub = new Label(publisher);
                            Label lblStatus = new Label("Borrow Status: ");
                            Label lblBorrowStatus = new Label(status);
                            
                            if (status.equals("Available")){
                                lblBorrowStatus.setForeground(new Color(100,200,0));
                            }else{
                                lblBorrowStatus.setForeground(new Color(255,0,0));
                            }

                            lblDetails.setFont(new Font("Serif", Font.BOLD, 34));
                            lblId.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblBookId.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblisbn.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblBookName.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblCategory.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblDate.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblAuthor.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblPublisher.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblIsbn.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblName.setFont(new Font("Serif", Font.PLAIN, 24));
                            lbldate.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblauthor.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblpub.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblcat.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblStatus.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblBorrowStatus.setFont(new Font("Serif", Font.PLAIN, 24));
                            
                            lblDetails.setBounds(250,180,500,40);
                            lblId.setBounds(250,250,100,30);
                            lblBookId.setBounds(450,250,100,30);
                            lblisbn.setBounds(1050,250,70,30);
                            lblIsbn.setBounds(1250,250,530,30);
                            lblBookName.setBounds(250,300,200,30);
                            lblName.setBounds(450,300,1200,30);
                            lblCategory.setBounds(250,360,200,30);
                            lblcat.setBounds(450,360,600,30);
                            lblDate.setBounds(1050,360,200,30);
                            lbldate.setBounds(1250,360,600,30);
                            lblAuthor.setBounds(250,420,200,30);
                            lblauthor.setBounds(450,420,600,30);
                            lblPublisher.setBounds(1050,420,200,30);
                            lblpub.setBounds(1250,420,600,30);
                            lblStatus.setBounds(250,480,200,30);
                            lblBorrowStatus.setBounds(450,480,600,30);

                            pnlDetails.add(lblDetails);
                            pnlDetails.add(lblId);
                            pnlDetails.add(lblBookId);
                            pnlDetails.add(lblisbn);
                            pnlDetails.add(lblIsbn);
                            pnlDetails.add(lblBookName);
                            pnlDetails.add(lblName);
                            pnlDetails.add(lblCategory);
                            pnlDetails.add(lblcat);
                            pnlDetails.add(lblDate);
                            pnlDetails.add(lbldate);
                            pnlDetails.add(lblAuthor);
                            pnlDetails.add(lblauthor);
                            pnlDetails.add(lblPublisher);
                            pnlDetails.add(lblpub);
                            pnlDetails.add(lblStatus);
                            pnlDetails.add(lblBorrowStatus);
                        }
                    }
                    
                    input.close();
                    
                }catch(FileNotFoundException ex){
                    JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                if (exists == false){
                    JOptionPane.showMessageDialog(null, "There is no such a book in the database.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                    setVisible(false);
                    
                    BorrowBook bb2 = new BorrowBook();
                    bb2.setVisible(true);
                }
            }
        });
    }
}
