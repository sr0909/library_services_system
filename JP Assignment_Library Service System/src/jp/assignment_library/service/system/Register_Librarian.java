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
//Link: http://www2.hawaii.edu/~takebaya/ics111/inheritance/inheritance.html
//Date Accessed: 3 March 2021
public class Register_Librarian extends Register{
    String fullname, username, pass, cPass, intakeCode, email, phoneNum, gender, role;
    TextField txtName, txtUsername, txtIntake, txtEmail, txtPhone;
    JRadioButton radM, radF;
    Choice cRole;
    JPasswordField txtPass, txtCPass;
    
    public Register_Librarian(){
        super();

        setTitle("Librarian Registration");
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
        
        Label lblTitle = new Label("Librarian Registration");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        pnlTitle.add(lblTitle);
        
        Label lblPersonalDetails = new Label("Personal Details");
        Label lblName = new Label("Full Name");
        Label lblGender = new Label("Gender");
        Label lblIntake = new Label("Intake Code");
        Label lblRole = new Label("Role");
        Label lblEmail = new Label("Email");
        Label lblPhone = new Label("Phone");
        Label lblAccDetails = new Label("Account Details");
        Label lblUsername = new Label("Username");
        Label lblPass = new Label("Password");
        Label lblCPass = new Label("Confirm Password");

        txtName = new TextField(80);
        txtIntake = new TextField(30);
        txtEmail = new TextField(30);
        txtPhone = new TextField(30);
        txtUsername = new TextField(50);

        //Link: https://www.javatpoint.com/java-jradiobutton
        //Date Accessed: 3 March 2021
        radM = new JRadioButton("Male");
        radF = new JRadioButton("Female");
        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(radM);
        bgGender.add(radF);
        
        radM.setBackground(new Color(227,201,187));
        radF.setBackground(new Color(227,201,187));
        
        cRole = new Choice();
        cRole.add("Librarian");
        cRole.add("Head of Librarian");
        
        txtPass = new JPasswordField(30);
        txtCPass = new JPasswordField(30);
        
        Button btnRegister = new Button("REGISTER");
        btnRegister.setBackground(new Color(134,83,55));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnRegister.setBounds(720,800,500,50);

        lblPersonalDetails.setFont(new Font("Serif", Font.BOLD, 34));
        lblName.setFont(new Font("Serif", Font.PLAIN, 24));
        lblGender.setFont(new Font("Serif", Font.PLAIN, 24));
        lblIntake.setFont(new Font("Serif", Font.PLAIN, 24));
        lblRole.setFont(new Font("Serif", Font.PLAIN, 24));
        lblEmail.setFont(new Font("Serif", Font.PLAIN, 24));
        lblPhone.setFont(new Font("Serif", Font.PLAIN, 24));
        lblAccDetails.setFont(new Font("Serif", Font.BOLD, 34));
        lblUsername.setFont(new Font("Serif", Font.PLAIN, 24));
        lblPass.setFont(new Font("Serif", Font.PLAIN, 24));
        lblCPass.setFont(new Font("Serif", Font.PLAIN, 24));
        txtName.setFont(new Font("Serif", Font.PLAIN, 24));
        txtIntake.setFont(new Font("Serif", Font.PLAIN, 24));
        txtEmail.setFont(new Font("Serif", Font.PLAIN, 24));
        txtPhone.setFont(new Font("Serif", Font.PLAIN, 24));
        txtUsername.setFont(new Font("Serif", Font.PLAIN, 24));
        txtPass.setFont(new Font("Serif", Font.PLAIN, 24));
        txtCPass.setFont(new Font("Serif", Font.PLAIN, 24));
        radM.setFont(new Font("Serif", Font.PLAIN, 24));
        radF.setFont(new Font("Serif", Font.PLAIN, 24));
        cRole.setFont(new Font("Serif", Font.PLAIN, 24));

        lblPersonalDetails.setBounds(250,55,500,40);
        lblName.setBounds(250,110,100,30);
        txtName.setBounds(250,140,600,40);
        lblGender.setBounds(250,210,200,30);
        radM.setBounds(250,240,200,40);
        radF.setBounds(450,240,100,40);
        lblIntake.setBounds(250,310,200,30);
        txtIntake.setBounds(250,340,600,40);
        lblRole.setBounds(250,410,100,30);
        cRole.setBounds(250,440,600,40);
        lblEmail.setBounds(250,510,100,30);
        txtEmail.setBounds(250,540,600,40);
        lblPhone.setBounds(250,610,200,30);
        txtPhone.setBounds(250,640,600,40);
      
        ImageIcon acc = new ImageIcon("images/Account.png");
        JLabel lblAcc = new JLabel(acc);
        lblAcc.setBounds(1050,100,600,280);
        
        lblAccDetails.setBounds(1050,55,500,40);
        lblUsername.setBounds(1050,410,100,30);
        txtUsername.setBounds(1050,440,600,40);
        lblPass.setBounds(1050,510,100,30);
        txtPass.setBounds(1050,540,600,40);
        lblCPass.setBounds(1050,610,200,30);
        txtCPass.setBounds(1050,640,600,40);
        
        pnlDetails.add(lblPersonalDetails);
        pnlDetails.add(lblName);
        pnlDetails.add(txtName);
        pnlDetails.add(lblGender);
        pnlDetails.add(radM);
        pnlDetails.add(radF);
        pnlDetails.add(lblIntake);
        pnlDetails.add(txtIntake);
        pnlDetails.add(lblRole);
        pnlDetails.add(cRole);
        pnlDetails.add(lblEmail);
        pnlDetails.add(txtEmail);
        pnlDetails.add(lblPhone);
        pnlDetails.add(txtPhone);
        pnlDetails.add(lblAccDetails);
        pnlDetails.add(lblAcc);
        pnlDetails.add(lblUsername);
        pnlDetails.add(txtUsername);
        pnlDetails.add(lblPass);
        pnlDetails.add(txtPass);
        pnlDetails.add(lblCPass);
        pnlDetails.add(txtCPass);
        pnlDetails.add(btnRegister);

        btnRegister.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                fullname = txtName.getText();
                intakeCode = txtIntake.getText();
                email = txtEmail.getText();
                phoneNum = txtPhone.getText();
                username = txtUsername.getText();
                pass = String.valueOf(txtPass.getPassword());
                cPass = String.valueOf(txtCPass.getPassword());
                
                //Link: https://www.codota.com/code/java/methods/java.awt.Choice/getSelectedItem
                //Date Accessed: 4 March 2021
                role = cRole.getSelectedItem();
                
                try{
                    //Link: https://stackoverflow.com/questions/13986466/how-to-check-whether-a-radiobutton-is-selected-in-a-buttongroup
                    //Date Accessed: 4 March 2021
                    if (bgGender.getSelection() == null){
                        throw new noGenderException();

                    }else{
                        //Link: https://stackoverflow.com/questions/22276242/multiple-string-conditions-in-an-if-statement
                        //Date Accessed: 4 March 2021
                        if (fullname.equals("") || intakeCode.equals("") || email.equals("") || phoneNum.equals("") || username.equals("") || 
                            pass.equals("")){
                            throw new noAllDetailsException();

                        }else{
                            if (pass.equals(cPass)){
                                NameValidation nameValid = new NameValidation(fullname);
                                boolean isNameValid = nameValid.getNameValidation();
                                
                                if (isNameValid == true){
                                    NameValidation nameValid2 = new NameValidation(fullname, "Librarian.txt");
                                    boolean isNameExists = nameValid2.getNameExists();
                                
                                    if (isNameExists == true){
                                        EmailValidation emailValid = new EmailValidation(email);
                                        boolean isEmailValid = emailValid.getEmailValidation();

                                        if (isEmailValid == true){
                                            PhoneValidation phoneValid = new PhoneValidation(phoneNum);
                                            boolean isPhoneValid = phoneValid.getPhoneValidation();
                                        
                                            if (isPhoneValid == true){
                                                UsernameValidation usernameValid = new UsernameValidation(username);
                                                boolean isUsernameValid = usernameValid.getUsernameValidation();
                                        
                                                if (isUsernameValid == true){
                                                    IntakeCodeValidation intakeValid = new IntakeCodeValidation(intakeCode);
                                                    boolean isIntakeValid = intakeValid.getIntakeValidation();

                                                    if (isIntakeValid == false){
                                                        try {
                                                            Register registerLibrarian = new Register("Librarian.txt");

                                                            String id = registerLibrarian.getId();

                                                            FileWriter fw = new FileWriter("Librarian.txt", true);
                                                            PrintWriter outputFile = new PrintWriter(fw);

                                                            String info = id + ":" + fullname + ":" + username + ":" + pass + ":" + getGender() + ":" + intakeCode + ":" +
                                                                          email + ":" + phoneNum + ":" + role;
                                                            outputFile.println(info);

                                                            outputFile.close();

                                                            JOptionPane.showMessageDialog(null, "This librarian is registered successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                                                            setVisible(false);

                                                            Register_Librarian rl2 = new Register_Librarian();
                                                            rl2.setVisible(true);

                                                        }catch(IOException ex){
                                                            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                        }
                                                    }else{
                                                        throw new intakeNotValidException();
                                                    }
                                                }else{
                                                    throw new usernameNotValidException();
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
                            }else{
                                throw new passNotMatchException();
                            }
                        }
                    }
                    
                }catch(noGenderException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                    
                }catch(noAllDetailsException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                    
                }catch(passNotMatchException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                    
                    txtPass.setText("");
                    txtCPass.setText("");
                    
                    txtPass.requestFocusInWindow();
                    
                }catch(emailNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                    
                    txtEmail.requestFocusInWindow();
                    
                }catch(nameNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    txtName.requestFocusInWindow();
                    
                }catch(nameExistsException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    setVisible(false);
                                                
                    Register_Librarian rl2 = new Register_Librarian();
                    rl2.setVisible(true);
                    
                }catch(phoneNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    txtPhone.requestFocusInWindow();
                    
                }catch(usernameNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    txtUsername.requestFocusInWindow();
                    
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