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
public class DeleteBook extends SearchBook2{
    public DeleteBook(){
        super();
        
        setTitle("Delete Book");
        
        initGUI();
    }
    
    private void initGUI(){
        Label lblTitle = new Label("Delete Book");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        Label lblStatement = new Label("Please enter the book name you want to delete:");
        lblStatement.setFont(new Font("Serif", Font.PLAIN, 24));
        lblStatement.setBounds(280,60,460,30);
        
        pnlDetails.add(lblStatement);
        
        Button btnDelete = new Button("DELETE BOOK");
        btnDelete.setBackground(new Color(134,83,55));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnDelete.setBounds(720,800,500,50);
        
        pnlDetails.add(btnDelete);
        
        btnDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (exists == true){
                    //Link: https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
                    //Date Accessed: 5 March 2021
                    File inputFile = new File("Book.txt");
                    File tempFile = new File("TempBook.txt");

                    try{
                        tempFile.createNewFile();

                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                        String lineToRemove = oldInfo;
                        String currentLine;

                        while ((currentLine = reader.readLine()) != null){
                            String trimmedLine = currentLine.trim();

                            if (trimmedLine.equals(lineToRemove)) continue;

                            writer.write(currentLine + System.getProperty("line.separator"));
                        }

                        writer.close();
                        reader.close();

                        inputFile.delete();

                        boolean successful = tempFile.renameTo(inputFile);

                        if (successful == true){
                            JOptionPane.showMessageDialog(null, "This book is deleted successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                            setVisible(false);

                            DeleteBook deleteb = new DeleteBook();
                            deleteb.setVisible(true);
                        }

                    }catch(IOException ex){
                        JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please search a book to delete", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
