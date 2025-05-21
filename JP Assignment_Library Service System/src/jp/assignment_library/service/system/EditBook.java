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
public class EditBook extends SearchBook{
    private String editBookID, bookName, isbn, date, author, publisher, description, category;
    private String oldString, newString;
    
    public EditBook(){
        super();
        
        setTitle("Edit Book");
        
        initGUI();
    }
     
    public EditBook(String oI, String nI, String id){
        oldString = oI;
        newString = nI;
        editBookID = id;
        
        //Link: https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/
        //Date Accessed: 5 March 2021
        File modifyFile = new File("Book.txt");
        
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
            
            JOptionPane.showMessageDialog(null, "The details of this book has been edited successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            
            setVisible(false);

            EditBookSuccess success = new EditBookSuccess(editBookID);
            success.setVisible(true);
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initGUI(){
        Label lblTitle = new Label("Edit Book");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        Label lblStatement = new Label("Please enter the book name you want to edit:");
        lblStatement.setFont(new Font("Serif", Font.PLAIN, 24));
        lblStatement.setBounds(300,60,450,30);
        
        pnlDetails.add(lblStatement);
        
        Button btnEdit = new Button("EDIT BOOK");
        btnEdit.setBackground(new Color(134,83,55));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnEdit.setBounds(720,800,500,50);
        
        pnlDetails.add(btnEdit);
         
        btnEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (exists == true){
                    isbn = txtIsbn.getText();
                    bookName = lblBook.getText();
                    date = txtDate.getText();
                    author = txtAuthor.getText();
                    publisher = txtPublisher.getText();
                    description = taDescription.getText(); 
                    category = cCategory.getSelectedItem();

                    try{
                        if (isbn.equals("") || date.equals("") || author.equals("") || publisher.equals("") || description.equals("") || 
                            category.equals("")){
                            throw new noAllDetailsException();

                        }else{
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
                                        try{
                                            Register r = new Register("Book.txt", id);
                                            editBookID = String.valueOf(r.getEditId());

                                        }catch(IOException ex){
                                            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        }

                                        String newInfo = editBookID + ":" + bookName + ":" + isbn + ":" + date + ":" + author + ":" + publisher + ":" + 
                                                         description + ":" + category;

                                        setVisible(false);

                                        EditBook ebook = new EditBook(oldInfo, newInfo, editBookID);
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Invalid publisher name.", "WARNING", JOptionPane.WARNING_MESSAGE);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Invalid author name.", "WARNING", JOptionPane.WARNING_MESSAGE);
                                }
                            }else{
                                throw new dateNotValidException();
                            }
                        }      
                    }catch(noAllDetailsException ex){
                        JOptionPane.showMessageDialog(null, "Please fill in all the book details to edit this book.", "WARNING", JOptionPane.WARNING_MESSAGE);

                    }catch(dateNotValidException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);

                        txtDate.requestFocusInWindow();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please search a book to edit", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
