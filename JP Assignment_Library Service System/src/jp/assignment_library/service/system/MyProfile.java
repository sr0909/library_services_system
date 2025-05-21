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
public class MyProfile extends Register{
    private String Id, fullname, username, pass, gender, intakeCode, email, phoneNum, role, oldInfo, newInfo, oldString, newString;
    private String name, intake, emailAdd, phone, position, uName, password, lgender;
    Choice cRole;
    
    public MyProfile(){
        super();
        
        setTitle("My Profile");
        setLayout(new BorderLayout(0,0));

        initGUI();
    }
    
    public MyProfile(String oI, String nI){
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
            
            JOptionPane.showMessageDialog(null, "Your personal details is edited successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            
            setVisible(false);

            MyProfile p2 = new MyProfile();
            p2.setVisible(true);

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
        
        Label lblTitle = new Label("My Profile");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        getDetails();
        
        Label lblDetails = new Label("Personal Details");
        Label lblLibrarianId = new Label("Librarian ID: ");
        Label lblId = new Label(Id);
        Label lblU = new Label("Username: ");
        Label lblUsername = new Label(username);
        Label lblLibrarianName = new Label("Full Name");
        Label lblG = new Label("Gender: ");
        Label lblGender = new Label(gender);
        Label lblIntake = new Label("Intake Code");
        Label lblRole = new Label("Role");
        Label lblR = new Label(role);
        Label lblEmail = new Label("Email");
        Label lblPhone = new Label("Phone Number");
        
        TextField txtName = new TextField(fullname);
        TextField txtIntake = new TextField(intakeCode);
        TextField txtEmail = new TextField(email);
        TextField txtPhone = new TextField(phoneNum);

        lblDetails.setFont(new Font("Serif", Font.BOLD, 34));
        lblLibrarianId.setFont(new Font("Serif", Font.PLAIN, 24));
        lblId.setFont(new Font("Serif", Font.PLAIN, 24));
        lblU.setFont(new Font("Serif", Font.PLAIN, 24));
        lblUsername.setFont(new Font("Serif", Font.PLAIN, 24));
        lblLibrarianName.setFont(new Font("Serif", Font.PLAIN, 24));
        txtName.setFont(new Font("Serif", Font.PLAIN, 24));
        lblG.setFont(new Font("Serif", Font.PLAIN, 24));
        lblGender.setFont(new Font("Serif", Font.PLAIN, 24));;
        lblIntake.setFont(new Font("Serif", Font.PLAIN, 24));
        txtIntake.setFont(new Font("Serif", Font.PLAIN, 24));
        lblRole.setFont(new Font("Serif", Font.PLAIN, 24));
        lblR.setFont(new Font("Serif", Font.PLAIN, 24));
        lblEmail.setFont(new Font("Serif", Font.PLAIN, 24));
        txtEmail.setFont(new Font("Serif", Font.PLAIN, 24));
        lblPhone.setFont(new Font("Serif", Font.PLAIN, 24));
        txtPhone.setFont(new Font("Serif", Font.PLAIN, 24));

        lblDetails.setBounds(250,55,500,40);
        lblLibrarianId.setBounds(250,130,170,30);
        lblId.setBounds(420,130,400,30);
        lblRole.setBounds(1050,130,150,30);
        lblR.setBounds(1200,130,400,30);
        lblU.setBounds(250,210,170,30);
        lblUsername.setBounds(420,210,400,30);
        lblG.setBounds(1050,210,150,30);
        lblGender.setBounds(1200,210,400,30);
        lblLibrarianName.setBounds(250,310,200,30);
        txtName.setBounds(250,340,600,40);
        lblIntake.setBounds(1050,310,200,30);
        txtIntake.setBounds(1050,340,600,40);
        lblEmail.setBounds(250,440,100,30);
        txtEmail.setBounds(250,470,600,40);
        lblPhone.setBounds(1050,440,200,30);
        txtPhone.setBounds(1050,470,600,40);

        pnlDetails.add(lblDetails);
        pnlDetails.add(lblLibrarianId);
        pnlDetails.add(lblId);
        pnlDetails.add(lblU);
        pnlDetails.add(lblUsername);
        pnlDetails.add(lblLibrarianName);
        pnlDetails.add(txtName);
        pnlDetails.add(lblG);
        pnlDetails.add(lblGender);
        pnlDetails.add(lblIntake);
        pnlDetails.add(txtIntake);
        pnlDetails.add(lblRole);
        pnlDetails.add(lblR);
        pnlDetails.add(lblEmail);
        pnlDetails.add(txtEmail);
        pnlDetails.add(lblPhone);
        pnlDetails.add(txtPhone);
        
        Button btnEdit = new Button("EDIT PROFILE");
        btnEdit.setBackground(new Color(134,83,55));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnEdit.setBounds(720,750,500,50);
        
        pnlDetails.add(btnEdit);
        
        btnEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                name = txtName.getText();
                intake = txtIntake.getText();
                emailAdd = txtEmail.getText();
                phone = txtPhone.getText();
                position = lblR.getText();
                Id = lblId.getText();
                uName = lblUsername.getText();
                password = pass;
                lgender = lblGender.getText();

                try{
                    if (name.equals("") || intake.equals("") || emailAdd.equals("") || phone.equals("")){
                        throw new noAllDetailsException();

                    }else{
                        NameValidation nameValid = new NameValidation(name);
                        boolean isNameValid = nameValid.getNameValidation();
                                
                        if (isNameValid == true){
                            EmailValidation emailValid = new EmailValidation(emailAdd);
                            boolean isEmailValid = emailValid.getEmailValidation();

                            if (isEmailValid == true){
                                PhoneValidation phoneValid = new PhoneValidation(phone);
                                boolean isPhoneValid = phoneValid.getPhoneValidation();
                                        
                                if (isPhoneValid == true){
                                    IntakeCodeValidation intakeValid = new IntakeCodeValidation(intake);
                                    boolean isIntakeValid = intakeValid.getIntakeValidation();
                                    
                                    if (isIntakeValid == false){
                                        newInfo = Id + ":" + name + ":" + uName + ":" + password + ":" + lgender + ":" + intake + ":" + 
                                                  emailAdd + ":" + phone + ":" + position;

                                        setVisible(false);

                                        MyProfile myP2 = new MyProfile(oldInfo, newInfo);
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
                            throw new nameNotValidException();
                        }
                    }
                }catch(noAllDetailsException ex){
                    JOptionPane.showMessageDialog(null, "Please fill in all your personal details to edit profile.", "WARNING", JOptionPane.WARNING_MESSAGE);
                    
                }catch(emailNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                    
                    txtEmail.requestFocusInWindow();
                    
                }catch(nameNotValidException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                   
                    txtName.requestFocusInWindow();
                    
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
    
    private void getDetails(){
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
            
            File inputLFile = new File("Librarian.txt");
            
            try{
                Scanner inputL = new Scanner(inputLFile);

                while(inputL.hasNextLine()){
                    String data = inputL.nextLine();

                    String[] lDetails = data.split(":");
                    String dbId = lDetails[0];
                    
                    if (dbId.equals(loginId)){
                        Id = lDetails[0];
                        fullname = lDetails[1];
                        username = lDetails[2];
                        pass = lDetails[3];
                        gender = lDetails[4];
                        intakeCode = lDetails[5];
                        email = lDetails[6];
                        phoneNum = lDetails[7];
                        role = lDetails[8];
                    }
                }
                
                oldInfo = Id + ":" + fullname + ":" + username + ":" + pass + ":" + gender + ":" + intakeCode + ":" + email + ":" + 
                      phoneNum + ":" + role;
                
                inputL.close();
                
            }catch(FileNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
