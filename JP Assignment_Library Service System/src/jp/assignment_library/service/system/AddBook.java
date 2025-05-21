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
/**
 *
 * @author Dell Vostro
 */
public class AddBook extends Register{
    private String id, isbn, bookName, date, author, publisher, category, description;
    TextField txtIsbn, txtBookName, txtDate, txtAuthor, txtPublisher;
    TextArea taDescription;
    Choice cCategory;
    
    public AddBook(){
        super();
        
        setTitle("Add Book");
        setLayout(new BorderLayout(0,0));

        initGUI();
    }
    
    private void initGUI(){
        Panel pnlTitle = new Panel();
        Panel pnlDetails = new Panel(null);
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));
        
        Label lblTitle = new Label("Add Book");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        pnlTitle.add(lblTitle);
        
        try{
            Register r = new Register("Book.txt");
            id = r.getId();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        Label lblId = new Label("Book ID: ");
        Label lblBookId = new Label(id);
        Label lblisbn = new Label("ISBN: ");
        Label lblBookName = new Label("Book Name");
        Label lblCategory = new Label("Book Category");
        Label lblDate = new Label("Published Date");
        Label lblAuthor = new Label("Author Name");
        Label lblPublisher = new Label("Publisher");
        Label lblDescription = new Label("Description");
        
        txtIsbn = new TextField(30);
        txtBookName = new TextField(80);
        txtDate = new TextField("Example: 30-01-2010");
        txtAuthor = new TextField(30);
        txtPublisher = new TextField(30);
        
        //Link: https://stackoverflow.com/questions/16441554/textarea-without-scrollbars-awt
        //Date Accessed: 5 March 2021
        taDescription = new TextArea("", 3, 100, TextArea.SCROLLBARS_NONE);
        
        cCategory = new Choice();
        cCategory.add("Business & Economics");
        cCategory.add("Computers");
        cCategory.add("Fiction");
        cCategory.add("Social Science");
        cCategory.add("Technology & Engineering");
        
        Button btnAdd = new Button("ADD BOOK");
        btnAdd.setBackground(new Color(134,83,55));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnAdd.setBounds(720,800,500,50);
        
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
        txtBookName.setFont(new Font("Serif", Font.PLAIN, 24));
        txtDate.setFont(new Font("Serif", Font.PLAIN, 24));
        txtAuthor.setFont(new Font("Serif", Font.PLAIN, 24));
        txtPublisher.setFont(new Font("Serif", Font.PLAIN, 24));
        taDescription.setFont(new Font("Serif", Font.PLAIN, 24));
        cCategory.setFont(new Font("Serif", Font.PLAIN, 24));
        
        lblId.setBounds(250,65,100,30);
        lblBookId.setBounds(350,65,100,30);
        lblisbn.setBounds(1050,65,70,30);
        txtIsbn.setBounds(1120,60,530,40);
        lblBookName.setBounds(250,140,200,30);
        txtBookName.setBounds(250,170,1400,40);
        lblCategory.setBounds(250,250,200,30);
        cCategory.setBounds(250,280,600,40);
        lblDate.setBounds(1050,250,200,30);
        txtDate.setBounds(1050,280,600,40);
        lblAuthor.setBounds(250,360,200,30);
        txtAuthor.setBounds(250,390,600,40);
        lblPublisher.setBounds(1050,360,200,30);
        txtPublisher.setBounds(1050,390,600,40);
        lblDescription.setBounds(250,470,200,30);
        taDescription.setBounds(250,500,1400,150);
        
        pnlDetails.add(lblId);
        pnlDetails.add(lblBookId);
        pnlDetails.add(lblisbn);
        pnlDetails.add(txtIsbn);
        pnlDetails.add(lblBookName);
        pnlDetails.add(txtBookName);
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
        pnlDetails.add(btnAdd);
        
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                isbn = txtIsbn.getText();
                bookName = txtBookName.getText();
                date = txtDate.getText();
                author = txtAuthor.getText();
                publisher = txtPublisher.getText();
                description = taDescription.getText(); 
                category = cCategory.getSelectedItem();
                
                try{
                    if (isbn.equals("") || bookName.equals("") || date.equals("") || author.equals("") || publisher.equals("") || 
                        description.equals("") || category.equals("")){
                        throw new noAllDetailsException();

                    }else{
                        //Link: https://stackabuse.com/java-check-if-string-is-a-number/
                        //Date Accessed: 5 March 2021
                        boolean isNameValid = false;
                                        
                        try{
                            Double str = Double.parseDouble(bookName);
                                            
                        }catch(NumberFormatException ex){
                            isNameValid = true;
                        }
                                
                        if (isNameValid == true){
                            NameValidation nameExists = new NameValidation(bookName, "Book.txt");
                            boolean isNameExists = nameExists.getNameExists();
                                
                            if (isNameExists == true){
                                DateValidation dateValid = new DateValidation(date);
                                boolean isDateValid = dateValid.getDateValidation();
                            
                                if (isDateValid == true){
                                    NameValidation nameValid2 = new NameValidation(author);
                                    boolean isAuthorNameValid = nameValid2.getNameValidation();

                                    if (isAuthorNameValid == true){
                                        boolean isPublisherValid = false;
                                        
                                        try{
                                            Double num = Double.parseDouble(publisher);
                                            
                                        }catch(NumberFormatException ex){
                                            isPublisherValid = true;
                                        }
                                        
                                        if (isPublisherValid == true){
                                            try {
                                                FileWriter fw = new FileWriter("Book.txt", true);
                                                PrintWriter outputFile = new PrintWriter(fw);

                                                String info = id + ":" + bookName + ":" + isbn + ":" + date + ":" + author + ":" + 
                                                              publisher + ":" + description + ":" + category;
                                                outputFile.println(info);

                                                outputFile.close();

                                                JOptionPane.showMessageDialog(null, "This book is added into database successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                                                setVisible(false);

                                                AddBook ab2 = new AddBook();
                                                ab2.setVisible(true);

                                            }catch(IOException ex){
                                                JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            }
                                        
                                        }else{
                                            JOptionPane.showMessageDialog(null, "Invalid publisher name.", "WARNING", JOptionPane.WARNING_MESSAGE);
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Invalid author name.", "WARNING", JOptionPane.WARNING_MESSAGE);
                                    }
                                }else{
                                    throw new dateNotValidException();
                                }
                            }else{
                                throw new nameExistsException();
                            }
                        }else{
                            throw new nameNotValidException();
                        }
                    }
                    
                }catch(noAllDetailsException ex){
                    JOptionPane.showMessageDialog(null, "Please fill in all the book details to add this book.", "WARNING", JOptionPane.WARNING_MESSAGE);
                
                }catch(nameNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    txtBookName.requestFocusInWindow();
                    
                }catch(nameExistsException ex){
                    JOptionPane.showMessageDialog(null, "This book exists in the database already.", "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    setVisible(false);
                                                
                    AddBook ab2 = new AddBook();
                    ab2.setVisible(true);
                    
                }catch(dateNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    txtDate.requestFocusInWindow();
                }
            }
        });
    }
}
