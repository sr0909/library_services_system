/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Dell Vostro
 */
public class BorrowingDetails extends Register{
    private String borrowBookName;
    
    public BorrowingDetails(){
        super();
        
        setTitle("Borrow Book");
        setLayout(new BorderLayout(0,0));
    }
    
    public BorrowingDetails(String n){
        super();
        
        borrowBookName = n;
        
        setTitle("Borrow Book");
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
        
        Label lblTitle = new Label("Borrow Book");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        String borrowDate, returnDate;
        
        //Link: https://www.javatpoint.com/java-get-current-date
        //Date Accessed: 6 March 2021
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        borrowDate = formatter.format(todayDate);
        
        //Link: https://beginnersbook.com/2017/10/java-add-days-to-date/
        //Date Accessed: 6 March 2021
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        returnDate = formatter.format(calendar.getTime());
        
        Label lblDetails = new Label("Borrowing Details");
        Label lblStudentName = new Label("Client Name: ");
        Label lblName = new Label("Book Name: ");
        Label lblBookName = new Label(borrowBookName);
        Label lblBorrow = new Label("Borrow Date: ");
        Label lblBorrowDate = new Label(borrowDate);
        Label lblReturn = new Label("Return Date: ");
        Label lblReturnDate = new Label(returnDate);
        TextField txtClientName = new TextField(80);
        
        lblDetails.setFont(new Font("Serif", Font.BOLD, 34));
        lblStudentName.setFont(new Font("Serif", Font.PLAIN, 24));
        txtClientName.setFont(new Font("Serif", Font.PLAIN, 24));
        lblName.setFont(new Font("Serif", Font.PLAIN, 24));
        lblBookName.setFont(new Font("Serif", Font.PLAIN, 24));
        lblBorrow.setFont(new Font("Serif", Font.PLAIN, 24));
        lblBorrowDate.setFont(new Font("Serif", Font.PLAIN, 24));
        lblReturn.setFont(new Font("Serif", Font.PLAIN, 24));
        lblReturnDate.setFont(new Font("Serif", Font.PLAIN, 24));
        
        lblDetails.setBounds(250,100,500,40);
        lblStudentName.setBounds(250,180,200,30);
        txtClientName.setBounds(450,180,1200,40);
        lblName.setBounds(250,250,200,30);
        lblBookName.setBounds(450,250,1200,30);
        lblBorrow.setBounds(250,310,200,30);
        lblBorrowDate.setBounds(450,310,400,30);
        lblReturn.setBounds(1050,310,200,30);
        lblReturnDate.setBounds(1250,310,400,30);
                            
        pnlDetails.add(lblDetails);
        pnlDetails.add(lblStudentName);
        pnlDetails.add(txtClientName);
        pnlDetails.add(lblName);
        pnlDetails.add(lblBookName);
        pnlDetails.add(lblBorrow);
        pnlDetails.add(lblBorrowDate);
        pnlDetails.add(lblReturn);
        pnlDetails.add(lblReturnDate);
        
        Button btnCancel = new Button("CANCEL");
        btnCancel.setBackground(new Color(134,83,55));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnCancel.setBounds(1050,750,500,50);
        
        pnlDetails.add(btnCancel);
        
        btnCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                BorrowBook bb2 = new BorrowBook();
                bb2.setVisible(true);
            }
        });
        
        Button btnBorrow = new Button("BORROW");
        btnBorrow.setBackground(new Color(134,83,55));
        btnBorrow.setForeground(Color.WHITE);
        btnBorrow.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnBorrow.setBounds(350,750,500,50);
        
        pnlDetails.add(btnBorrow);
        
        btnBorrow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String clientName = txtClientName.getText();
                boolean clientExists = false;

                try{
                    if (clientName.equals("")){
                        JOptionPane.showMessageDialog(null, "Please fill in the student name to borrow book.", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }else{
                        NameValidation nameValid = new NameValidation(clientName);
                        boolean isNameValid = nameValid.getNameValidation();

                        if (isNameValid == true){
                            File inputClientFile = new File("Client.txt");

                            try{
                                Scanner inputClient = new Scanner(inputClientFile);

                                while(inputClient.hasNextLine()){
                                    String data = inputClient.nextLine();

                                    String[] clientDetails = data.split(":");
                                    String dbClient = clientDetails[1];

                                    if (clientName.equals(dbClient)){
                                        clientExists = true;
                                        
                                        try{
                                            FileWriter fw = new FileWriter("Borrow.txt", true);
                                            PrintWriter outputFile = new PrintWriter(fw);
                                            
                                            Register r = new Register("Borrow.txt");
                                            String borrowId = r.getId();
                                            
                                            String borrowInfo = borrowId + ":" + borrowBookName + ":" + clientName + ":" + borrowDate + ":" +
                                                                returnDate + ":Borrowing:0:-:-:-";
                                            outputFile.println(borrowInfo);

                                            outputFile.close();

                                            JOptionPane.showMessageDialog(null, "This book is borrowed to this client successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                                            setVisible(false);

                                            BorrowBook bb2 = new BorrowBook();
                                            bb2.setVisible(true);
                                            
                                        }catch(IOException ex){
                                            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                                
                                if (clientExists == false){
                                    JOptionPane.showMessageDialog(null, "This client is not registered yet. Please register first to borrow book.", "WARNING", JOptionPane.WARNING_MESSAGE);

                                    setVisible(false);

                                    RegisterClient rc = new RegisterClient();
                                    rc.setVisible(true);
                                }

                                inputClient.close();

                            }catch(FileNotFoundException ex){
                                JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            throw new nameNotValidException();
                        }
                    }
                }catch(nameNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);

                    txtClientName.requestFocusInWindow();
                }
            }
        });
    }
}
