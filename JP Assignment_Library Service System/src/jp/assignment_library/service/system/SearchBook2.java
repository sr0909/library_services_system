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
/**
 *
 * @author Dell Vostro
 */
public class SearchBook2  extends Register{
    public String id;
    private String searchName, bookName, isbn, date, author, publisher, description, category;
    public boolean exists;
    String oldInfo;
    Panel pnlTitle, pnlDetails;
    Label lblBook;
    TextField txtSearchName, txtIsbn, txtBookName, txtDate, txtAuthor, txtPublisher;
    TextArea taDescription;
    Choice cCategory;
    
    public SearchBook2(){
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
        txtSearchName.setBounds(750,55,600,40);
        
        Button btnOk = new Button("OK");
        btnOk.setBackground(new Color(134,83,55));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnOk.setBounds(1360,55,200,40);

        pnlDetails.add(txtSearchName);
        pnlDetails.add(btnOk);
        
        btnOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                searchName = txtSearchName.getText();

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
                            description = bookDetails[6];
                            category = bookDetails[7];
                            
                            oldInfo = id + ":" + bookName + ":" + isbn + ":" + date + ":" + author + ":" + publisher + ":" + 
                                             description + ":" + category;
                            
                            Label lblId = new Label("Book ID: ");
                            Label lblBookId = new Label(id);
                            Label lblisbn = new Label("ISBN: ");
                            Label lblBookName = new Label("Book Name");
                            lblBook = new Label(bookName);
                            Label lblCategory = new Label("Book Category");
                            Label lblDate = new Label("Published Date");
                            Label lblAuthor = new Label("Author Name");
                            Label lblPublisher = new Label("Publisher");
                            Label lblDescription = new Label("Description");
                            
                            txtIsbn = new TextField(isbn);
                            txtDate = new TextField(date);
                            txtAuthor = new TextField(author);
                            txtPublisher = new TextField(publisher);
                            taDescription = new TextArea(description, 3, 100, TextArea.SCROLLBARS_NONE);
                            
                            cCategory = new Choice();
                            cCategory.add("Business & Economics");
                            cCategory.add("Computers");
                            cCategory.add("Fiction");
                            cCategory.add("Social Science");
                            cCategory.add("Technology & Engineering");
                            //Link: https://stackoverflow.com/questions/40837844/set-choice-box-to-default-value-in-java
                            //Date Accessed: 5 March 2021
                            cCategory.select(category);
                                          
                            lblId.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblBookId.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblisbn.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblBookName.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblCategory.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblDate.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblAuthor.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblPublisher.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblDescription.setFont(new Font("Serif", Font.PLAIN, 24));
                            txtIsbn.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblBook.setFont(new Font("Serif", Font.PLAIN, 24));
                            txtDate.setFont(new Font("Serif", Font.PLAIN, 24));
                            txtAuthor.setFont(new Font("Serif", Font.PLAIN, 24));
                            txtPublisher.setFont(new Font("Serif", Font.PLAIN, 24));
                            taDescription.setFont(new Font("Serif", Font.PLAIN, 24));
                            cCategory.setFont(new Font("Serif", Font.PLAIN, 24));
        
                            lblId.setBounds(250,180,100,30);
                            lblBookId.setBounds(350,180,100,30);
                            lblisbn.setBounds(1050,180,70,30);
                            txtIsbn.setBounds(1120,180,530,40);
                            lblBookName.setBounds(250,260,200,30);
                            lblBook.setBounds(450,260,1200,30);
                            lblCategory.setBounds(250,340,200,30);
                            cCategory.setBounds(250,370,600,40);
                            lblDate.setBounds(1050,340,200,30);
                            txtDate.setBounds(1050,370,600,40);
                            lblAuthor.setBounds(250,450,200,30);
                            txtAuthor.setBounds(250,480,600,40);
                            lblPublisher.setBounds(1050,450,200,30);
                            txtPublisher.setBounds(1050,480,600,40);
                            lblDescription.setBounds(250,560,200,30);
                            taDescription.setBounds(250,590,1400,150);

                            pnlDetails.add(lblId);
                            pnlDetails.add(lblBookId);
                            pnlDetails.add(lblisbn);
                            pnlDetails.add(txtIsbn);
                            pnlDetails.add(lblBookName);
                            pnlDetails.add(lblBook);
                            pnlDetails.add(lblCategory);
                            pnlDetails.add(cCategory);
                            pnlDetails.add(lblDate);
                            pnlDetails.add(txtDate);
                            pnlDetails.add(lblAuthor);
                            pnlDetails.add(txtAuthor);
                            pnlDetails.add(lblPublisher);
                            pnlDetails.add(txtPublisher);
                            pnlDetails.add(lblDescription);
                            pnlDetails.add(taDescription);
                        }
                    }
                    
                    input.close();
                    
                }catch(FileNotFoundException ex){
                    JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                if (exists == false){
                    JOptionPane.showMessageDialog(null, "There is no such a book in database.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                    setVisible(false);
                    
                    DeleteBook db2 = new DeleteBook();
                    db2.setVisible(true);
                }
            }
        });
    }
}
