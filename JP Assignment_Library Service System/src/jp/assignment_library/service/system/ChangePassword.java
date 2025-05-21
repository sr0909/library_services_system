/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
/**
 *
 * @author Dell Vostro
 */
public class ChangePassword extends Register{
    private String dbOldPass, oldPass, newPass, cNewPass;
    private String Id, fullname, username, gender, intakeCode, email, phoneNum, role, oldInfo, newInfo, oldString, newString;
    
    public ChangePassword(){
        super();
        
        setTitle("Edit Book");
        setLayout(new BorderLayout(0,0));
        
        initGUI();
    }
    
    public ChangePassword(String oI, String nI){
        oldString = oI;
        newString = nI;
        
        //Link: https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/
        //Date Accessed: 5 March 2021
        File modifyFile = new File("Librarian.txt");
        
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
            
            JOptionPane.showMessageDialog(null, "The password is changed successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            
            setVisible(false);

            Login l = new Login();
            l.setVisible(true);
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initGUI(){
        Panel pnlTitle = new Panel();
        Panel pnlDetails = new Panel(null);
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));

        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        
        Label lblTitle = new Label("Change Password");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        ImageIcon chgpass = new ImageIcon("images/ChangePass.png");
        JLabel lblChgPass = new JLabel(chgpass);
        lblChgPass.setBounds(250,100,720,440);
        
        Label lblO = new Label("Old Password");
        Label lbln = new Label("New Password");
        Label lblcn = new Label("Confirm New Password");
        
        JPasswordField txtOldPass = new JPasswordField();
        JPasswordField txtNewPass = new JPasswordField();
        JPasswordField txtCNewPass = new JPasswordField();
        
        lblO.setFont(new Font("Serif", Font.PLAIN, 24));
        lbln.setFont(new Font("Serif", Font.PLAIN, 24));
        lblcn.setFont(new Font("Serif", Font.PLAIN, 24));
        txtOldPass.setFont(new Font("Serif", Font.PLAIN, 24));
        txtNewPass.setFont(new Font("Serif", Font.PLAIN, 24));
        txtCNewPass.setFont(new Font("Serif", Font.PLAIN, 24));
        
        lblO.setBounds(1050,160,300,30);
        txtOldPass.setBounds(1050,190,600,40);
        lbln.setBounds(1050,270,300,30);
        txtNewPass.setBounds(1050,300,600,40);
        lblcn.setBounds(1050,380,300,30);
        txtCNewPass.setBounds(1050,410,600,40);
        
        pnlDetails.add(lblChgPass);
        pnlDetails.add(lblO);
        pnlDetails.add(txtOldPass);
        pnlDetails.add(lbln);
        pnlDetails.add(txtNewPass);
        pnlDetails.add(lblcn);
        pnlDetails.add(txtCNewPass);
        
        Button btnChg = new Button("CHANGE PASSWORD");
        btnChg.setBackground(new Color(134,83,55));
        btnChg.setForeground(Color.WHITE);
        btnChg.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnChg.setBounds(720,750,500,50);
        
        pnlDetails.add(btnChg);
        
        btnChg.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                oldPass = txtOldPass.getText();
                newPass = txtNewPass.getText();
                cNewPass = txtCNewPass.getText();
                
                getOldPass();
                
                if (dbOldPass.equals(oldPass)){
                    if (newPass.equals(cNewPass)){
                        newInfo = Id + ":" + fullname + ":" + username + ":" + newPass + ":" + gender + ":" + intakeCode + ":" + email + ":" +
                              phoneNum + ":" + role;
                        
                        setVisible(false);

                        ChangePassword chg = new ChangePassword(oldInfo, newInfo);
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "New password is not match with confirm new password. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                        txtNewPass.setText("");
                        txtCNewPass.setText("");
                        txtNewPass.requestFocusInWindow();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect old password. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                    txtOldPass.setText("");
                    txtOldPass.requestFocusInWindow();
                }
            }
        });
    }
    
    private void getOldPass(){
        String loginId = "";
        
        File inputFile = new File("Login.txt");
        
        try{
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNextLine()){
                String data = input.nextLine();
                
                String[] details = data.split(":");
                loginId = details[0];
            }
            
            input.close();
            
            File inputLibrarianFile = new File("Librarian.txt");
            
            Scanner inputLibrarian = new Scanner(inputLibrarianFile);
            
            while(inputLibrarian.hasNextLine()){
                String data = inputLibrarian.nextLine();
                
                String[] Librarian = data.split(":");
                String dbId = Librarian[0];
                
                if (dbId.equals(loginId)){
                    Id = Librarian[0];
                    fullname = Librarian[1];
                    username = Librarian[2];
                    dbOldPass = Librarian[3];
                    gender = Librarian[4];
                    intakeCode = Librarian[5];
                    email = Librarian[6];
                    phoneNum = Librarian[7];
                    role = Librarian[8];
                    
                    oldInfo = Id + ":" + fullname + ":" + username + ":" + dbOldPass + ":" + gender + ":" + intakeCode + ":" + email + ":" +
                              phoneNum + ":" + role;
                }
            }
            
            inputLibrarian.close();
            
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
