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
public class EditClient extends Client{
    private String editClientId, clientName, intakeCode, email, phone, role, oldString, newString;
    
    public EditClient(){
        super();
        
        setTitle("Edit Book");
        
        initGUI();
    }
    
    public EditClient(String oI, String nI, String id){
        oldString = oI;
        newString = nI;
        editClientId = id;
        
        //Link: https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/
        //Date Accessed: 5 March 2021
        File modifyFile = new File("Client.txt");
        
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
            
            JOptionPane.showMessageDialog(null, "The client details had been edited successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            
            setVisible(false);

            EditClientSuccess success = new EditClientSuccess(editClientId);
            success.setVisible(true);
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initGUI(){
        Label lblTitle = new Label("Edit Client");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        Label lblStatement = new Label("Please enter the client name you want to edit:");
        lblStatement.setFont(new Font("Serif", Font.PLAIN, 24));
        lblStatement.setBounds(300,80,450,30);
        
        pnlDetails.add(lblStatement);
        
        Button btnEdit = new Button("EDIT CLIENT");
        btnEdit.setBackground(new Color(134,83,55));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnEdit.setBounds(720,800,500,50);
        
        pnlDetails.add(btnEdit);
        
        btnEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (exists == true){
                    clientName = lblName.getText();
                    intakeCode = txtIntake.getText();
                    email = txtEmail.getText();
                    phone = txtPhone.getText();
                    role = cRole.getSelectedItem();

                    try{
                        if (intakeCode.equals("") || email.equals("") || phone.equals("") || role.equals("")){
                            throw new noAllDetailsException();

                        }else{
                            EmailValidation emailValid = new EmailValidation(email);
                            boolean isEmailValid = emailValid.getEmailValidation();

                            if (isEmailValid == true){
                                PhoneValidation phoneValid = new PhoneValidation(phone);
                                boolean isPhoneValid = phoneValid.getPhoneValidation();

                                if (isPhoneValid == true){
                                    IntakeCodeValidation intakeValid = new IntakeCodeValidation(intakeCode);
                                    boolean isIntakeValid = intakeValid.getIntakeValidation();
                                        
                                    if (isIntakeValid == false){
                                        boolean isIntakeCodeValid = true;
                                            
                                        if (role.equals("Staff")){
                                            intakeCode = "-";
                                        }else if (role.equals("Student") && intakeCode.equals("-")){
                                            isIntakeCodeValid = false;
                                        }

                                        if (isIntakeCodeValid == true){
                                            try{
                                                Register r = new Register("Book.txt", clientId);
                                                editClientId = String.valueOf(r.getEditId());

                                            }catch(IOException ex){
                                                JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            }

                                            String newInfo = editClientId + ":" + clientName + ":" + lblGender.getText() + ":" + intakeCode + ":" + email + ":" + 
                                                             phone + ":" + role;

                                            setVisible(false);

                                            EditClient eClient = new EditClient(oldInfo, newInfo, editClientId);
                                        }else{
                                            JOptionPane.showMessageDialog(null, "Please fill in the student intake code.", "WARNING", JOptionPane.WARNING_MESSAGE);
                                                
                                            txtIntake.setText("");
                                            txtIntake.requestFocusInWindow();
                                        }
                                    }else{
                                        throw new intakeNotValidException();
                                    }
                                }else{
                                    throw new phoneNotValidException();
                                }
                            }else{
                                throw new emailNotValidException();
                            }
                        }      
                    }catch(noAllDetailsException ex){
                        JOptionPane.showMessageDialog(null, "Please fill in all the client details to edit this client.", "WARNING", JOptionPane.WARNING_MESSAGE);

                    }catch(emailNotValidException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);

                        txtEmail.requestFocusInWindow();
                    
                    }catch(phoneNotValidException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);

                        txtPhone.requestFocusInWindow();

                    }catch(intakeNotValidException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);

                        txtIntake.requestFocusInWindow();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please search a client to edit", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
