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
public class EditBookSuccess extends NavBar{
    private String id, bookName, isbn, date, author, publisher, description, category;
    private String bookId;
    
    public EditBookSuccess(){
        super();
        
        setTitle("Edit Book");
        setLayout(new BorderLayout(0,0));
    }
    
    public EditBookSuccess(String bid){
        super();
        
        setTitle("Edit Book");
        setLayout(new BorderLayout(0,0));
        
        bookId = bid;
        
        Panel pnlTitle = new Panel();
        Panel pnlDetails = new Panel(null);
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));
        
        Label lblTitle = new Label("Edit Book");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
  
        File inputFile = new File("Book.txt");
        
        try{
            Scanner input = new Scanner(inputFile);
      
            while(input.hasNextLine()){
                String data = input.nextLine();
                        
                String[] bookDetails = data.split(":");
                String dbBookID = bookDetails[0];
                        
                if (bookId.equals(dbBookID)){
                    id = bookDetails[0];
                    bookName = bookDetails[1];
                    isbn = bookDetails[2];
                    date = bookDetails[3];
                    author = bookDetails[4];
                    publisher = bookDetails[5];
                    description = bookDetails[6];
                    category = bookDetails[7];
                            
                    Label lblId = new Label("Book ID: ");
                    Label lblBookId = new Label(id);
                    Label lblisbn = new Label("ISBN: ");
                    Label lblBookName = new Label("Book Name");
                    Label lblBook = new Label(bookName);
                    Label lblCategory = new Label("Book Category");
                    Label lblDate = new Label("Published Date");
                    Label lblAuthor = new Label("Author Name");
                    Label lblPublisher = new Label("Publisher");
                    Label lblDescription = new Label("Description");
                            
                    TextField txtIsbn = new TextField(isbn);
                    TextField txtCategory = new TextField(category);
                    TextField txtDate = new TextField(date);
                    TextField txtAuthor = new TextField(author);
                    TextField txtPublisher = new TextField(publisher);
                    TextArea taDescription = new TextArea(description, 3, 100, TextArea.SCROLLBARS_NONE);

                    Button btnEditOther = new Button("EDIT ANOTHER BOOK");
                    btnEditOther.setBackground(new Color(134,83,55));
                    btnEditOther.setForeground(Color.WHITE);
                    btnEditOther.setFont(new Font("Monospaced", Font.BOLD, 26));
                    btnEditOther.setBounds(720,800,500,50);

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
                    txtCategory.setFont(new Font("Serif", Font.PLAIN, 24));
        
                    lblId.setBounds(250,100,100,30);
                    lblBookId.setBounds(350,100,100,30);
                    lblisbn.setBounds(1050,100,70,30);
                    txtIsbn.setBounds(1120,100,530,40);
                    lblBookName.setBounds(250,180,200,30);
                    lblBook.setBounds(450,180,1200,30);
                    lblCategory.setBounds(250,260,200,30);
                    txtCategory.setBounds(250,290,600,40);
                    lblDate.setBounds(1050,260,200,30);
                    txtDate.setBounds(1050,290,600,40);
                    lblAuthor.setBounds(250,370,200,30);
                    txtAuthor.setBounds(250,400,600,40);
                    lblPublisher.setBounds(1050,370,200,30);
                    txtPublisher.setBounds(1050,400,600,40);
                    lblDescription.setBounds(250,480,200,30);
                    taDescription.setBounds(250,510,1400,150);

                    pnlDetails.add(lblId);
                    pnlDetails.add(lblBookId);
                    pnlDetails.add(lblisbn);
                    pnlDetails.add(txtIsbn);
                    pnlDetails.add(lblBookName);
                    pnlDetails.add(lblBook);
                    pnlDetails.add(lblCategory);
                    pnlDetails.add(txtCategory);
                    pnlDetails.add(lblDate);
                    pnlDetails.add(txtDate);
                    pnlDetails.add(lblAuthor);
                    pnlDetails.add(txtAuthor);
                    pnlDetails.add(lblPublisher);
                    pnlDetails.add(txtPublisher);
                    pnlDetails.add(lblDescription);
                    pnlDetails.add(taDescription);
                    pnlDetails.add(btnEditOther);
                    
                    btnEditOther.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            setVisible(false);
                    
                            EditBook ebook = new EditBook();
                            ebook.setVisible(true);
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
