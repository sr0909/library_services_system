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
public class RegisterClient extends Register{
    String fullname, intakeCode, email, phoneNum, gender, role;
    TextField txtName, txtIntake, txtEmail, txtPhone;
    JRadioButton radM, radF;
    Choice cRole;
    
    public RegisterClient(){
        super();

        setTitle("Client Registration");
        setLayout(new BorderLayout(0,0));
 
        initGUI();
    }
    
    private void initGUI(){
        Panel pnlTitle = new Panel();
        Panel pnlDetails = new Panel(null);
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        
        Label lblTitle = new Label("Client Registration");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        pnlTitle.add(lblTitle);
        
        ImageIcon acc = new ImageIcon("images/Client2.jpg");
        JLabel lblAcc = new JLabel(acc);
        lblAcc.setBounds(300,180,500,500);
        
        Label lblPersonalDetails = new Label("Personal Details");
        Label lblName = new Label("Full Name");
        Label lblRole = new Label("Role");
        Label lblIntake = new Label("Intake Code");
        Label lblGender = new Label("Gender");
        Label lblEmail = new Label("Email");
        Label lblPhone = new Label("Phone");
        
        txtName = new TextField(80);
        txtIntake = new TextField(30);
        txtEmail = new TextField(30);
        txtPhone = new TextField(30);

        radM = new JRadioButton("Male");
        radF = new JRadioButton("Female");
        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(radM);
        bgGender.add(radF);
        
        radM.setBackground(new Color(227,201,187));
        radF.setBackground(new Color(227,201,187));
        
        cRole = new Choice();
        cRole.add("Student");
        cRole.add("Staff");
        
        Label lblStatement = new Label("For staff, please enter '-'.");
        lblStatement.setForeground(new Color(235,18,29));
        lblStatement.setFont(new Font("Serif", Font.BOLD, 20));
        lblStatement.setBounds(1000,490,600,30);
        
        Button btnRegister = new Button("REGISTER");
        btnRegister.setBackground(new Color(134,83,55));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnRegister.setBounds(720,800,500,50);
        
        lblPersonalDetails.setFont(new Font("Serif", Font.BOLD, 34));
        lblName.setFont(new Font("Serif", Font.PLAIN, 24));
        lblRole.setFont(new Font("Serif", Font.PLAIN, 24));
        lblIntake.setFont(new Font("Serif", Font.PLAIN, 24));
        lblGender.setFont(new Font("Serif", Font.PLAIN, 24));
        lblEmail.setFont(new Font("Serif", Font.PLAIN, 24));
        lblPhone.setFont(new Font("Serif", Font.PLAIN, 24));
        txtName.setFont(new Font("Serif", Font.PLAIN, 24));
        txtIntake.setFont(new Font("Serif", Font.PLAIN, 24));
        txtEmail.setFont(new Font("Serif", Font.PLAIN, 24));
        txtPhone.setFont(new Font("Serif", Font.PLAIN, 24));
        radM.setFont(new Font("Serif", Font.PLAIN, 24));
        radF.setFont(new Font("Serif", Font.PLAIN, 24));
        cRole.setFont(new Font("Serif", Font.PLAIN, 24));
        
        lblPersonalDetails.setBounds(300,65,500,40);
        lblName.setBounds(1000,120,100,30);
        txtName.setBounds(1000,150,600,40);
        lblGender.setBounds(1000,220,100,30);
        radM.setBounds(1000,250,200,40);
        radF.setBounds(1200,250,100,40);
        lblRole.setBounds(1000,320,100,30);
        cRole.setBounds(1000,350,600,40);
        lblIntake.setBounds(1000,420,200,30);
        txtIntake.setBounds(1000,450,600,40);
        lblEmail.setBounds(1000,550,100,30);
        txtEmail.setBounds(1000,580,600,40);
        lblPhone.setBounds(1000,650,200,30);
        txtPhone.setBounds(1000,680,600,40);
        
        pnlDetails.add(lblPersonalDetails);
        pnlDetails.add(lblAcc);
        pnlDetails.add(lblName);
        pnlDetails.add(txtName);
        pnlDetails.add(lblRole);
        pnlDetails.add(cRole);
        pnlDetails.add(lblIntake);
        pnlDetails.add(txtIntake);
        pnlDetails.add(lblStatement);
        pnlDetails.add(lblGender);
        pnlDetails.add(radM);
        pnlDetails.add(radF);
        pnlDetails.add(lblEmail);
        pnlDetails.add(txtEmail);
        pnlDetails.add(lblPhone);
        pnlDetails.add(txtPhone);
        pnlDetails.add(btnRegister);
        
        btnRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fullname = txtName.getText();
                intakeCode = txtIntake.getText();
                email = txtEmail.getText();
                phoneNum = txtPhone.getText();
                
                role = cRole.getSelectedItem();
                
                try{
                    if (bgGender.getSelection() == null){
                        throw new noGenderException();

                    }else{
                        if (fullname.equals("") || intakeCode.equals("") || email.equals("") || phoneNum.equals("") || role.equals("")){
                            throw new noAllDetailsException();

                        }else{
                            NameValidation nameValid = new NameValidation(fullname);
                            boolean isNameValid = nameValid.getNameValidation();
                                
                            if (isNameValid == true){
                                NameValidation nameValid2 = new NameValidation(fullname, "Client.txt");
                                boolean isNameExists = nameValid2.getNameExists();
                                
                                if (isNameExists == true){
                                    EmailValidation emailValid = new EmailValidation(email);
                                    boolean isEmailValid = emailValid.getEmailValidation();

                                    if (isEmailValid == true){
                                        PhoneValidation phoneValid = new PhoneValidation(phoneNum);
                                        boolean isPhoneValid = phoneValid.getPhoneValidation();

                                        if (isPhoneValid == true){
                                            IntakeCodeValidation intakeValid = new IntakeCodeValidation(intakeCode);
                                            boolean isIntakeValid = intakeValid.getIntakeValidation();

                                            if (isIntakeValid == false){
                                                if (role.equals("Staff")){
                                                    intakeCode = "-";
                                                }
                                                
                                                if (role.equals("Student") && intakeCode.equals("-")){
                                                    JOptionPane.showMessageDialog(null, "Please enter your intake code as '-' is not considered as the intake code.",
                                                                                  "WARNING", JOptionPane.WARNING_MESSAGE);
                                                    
                                                    txtIntake.setText("");
                                                    txtIntake.requestFocusInWindow();
                                                    
                                                }else{
                                                    try {
                                                        Register registerClient = new Register("Client.txt");

                                                        String id = registerClient.getId();

                                                        FileWriter fw = new FileWriter("Client.txt", true);
                                                        PrintWriter outputFile = new PrintWriter(fw);

                                                        String info = id + ":" + fullname + ":" + getGender() + ":" + intakeCode + ":" + email + ":" + 
                                                                      phoneNum + ":" + role;
                                                        outputFile.println(info);

                                                        outputFile.close();

                                                        JOptionPane.showMessageDialog(null, "This client is registered successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                                                        setVisible(false);

                                                        RegisterClient rc2 = new RegisterClient();
                                                        rc2.setVisible(true);

                                                    }catch(IOException ex){
                                                        JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                    }
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
                                    
                                }else{
                                    throw new nameExistsException();
                                }
                                
                            }else{
                                throw new nameNotValidException();
                            }
                        }
                    }
                    
                }catch(noGenderException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                    
                }catch(noAllDetailsException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                    
                }catch(emailNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                    
                    txtEmail.requestFocusInWindow();
                    
                }catch(nameNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    txtName.requestFocusInWindow();
                    
                }catch(nameExistsException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    setVisible(false);
                                                
                    RegisterClient rc2 = new RegisterClient();
                    rc2.setVisible(true);
                    
                }catch(phoneNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    txtPhone.requestFocusInWindow();
                    
                }catch(intakeNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    txtIntake.requestFocusInWindow();
                }
            }
        });
    }
    
    public String getGender(){
        //Link: https://www.homeandlearn.co.uk/java/java_radio_buttons.html
        //Date Accessed: 4 March 2021
        if (radM.isSelected()){
            gender = radM.getText();
        }else if (radF.isSelected()){
            gender = radF.getText();
        }
        
        return gender;
    }
}
