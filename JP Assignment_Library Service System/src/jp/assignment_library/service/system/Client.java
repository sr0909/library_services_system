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
public class Client extends Register{
    public String clientId, oldInfo;
    public boolean exists;
    private String searchName, name, gender, intake, email, phone, role;
    Panel pnlTitle, pnlDetails;
    Label lblGender, lblName;
    TextField txtSearchName, txtName, txtIntake, txtEmail, txtPhone;
    Choice cRole;
    
    public Client(){
        super();

        setLayout(new BorderLayout(0,0));
        
        initGUI();
    }
    
    private void initGUI(){
        pnlTitle = new Panel();
        pnlDetails = new Panel(null);
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");

        txtSearchName = new TextField(30);
        txtSearchName.setFont(new Font("Serif", Font.PLAIN, 24));
        txtSearchName.setBounds(750,75,600,40);
        
        Button btnOk = new Button("OK");
        btnOk.setBackground(new Color(134,83,55));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnOk.setBounds(1360,75,200,40);

        pnlDetails.add(txtSearchName);
        pnlDetails.add(btnOk);
        
        btnOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                searchName = txtSearchName.getText();

                File inputFile = new File("Client.txt");
        
                try{
                    Scanner input = new Scanner(inputFile);

                    exists = false;
                    
                    while(input.hasNextLine()){
                        String data = input.nextLine();
                        
                        String[] clientDetails = data.split(":");
                        String dbClientName = clientDetails[1];
                        
                        if (searchName.equals(dbClientName)){
                            exists = true;

                            clientId = clientDetails[0];
                            name = clientDetails[1];
                            gender = clientDetails[2];
                            intake = clientDetails[3];
                            email = clientDetails[4];
                            phone = clientDetails[5];
                            role = clientDetails[6];
                            
                            oldInfo = clientId + ":" + name + ":" + gender + ":" + intake + ":" + email + ":" + phone + ":" + role;
                            
                            Label lblDetails = new Label("Client Details");
                            Label lblClientId = new Label("Client ID: ");
                            Label lblId = new Label(clientId);
                            Label lblClientName = new Label("Full Name: ");
                            lblName = new Label(name);
                            Label lblG = new Label("Gender: ");
                            lblGender = new Label(gender);
                            Label lblIntake = new Label("Intake Code");
                            Label lblRole = new Label("Role");
                            Label lblEmail = new Label("Email");
                            Label lblPhone = new Label("Phone Number");

                            txtIntake = new TextField(intake);
                            txtEmail = new TextField(email);
                            txtPhone = new TextField(phone);
                            
                            cRole = new Choice();
                            cRole.add("Student");
                            cRole.add("Staff");
                            cRole.select(role);
                            
                            Label lblStatement = new Label("For staff, please enter '-'.");
                            lblStatement.setForeground(new Color(235,18,29));
                            lblStatement.setFont(new Font("Serif", Font.BOLD, 20));
                            lblStatement.setBounds(250,500,600,30);
                            
                            lblDetails.setFont(new Font("Serif", Font.BOLD, 34));
                            lblClientId.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblId.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblClientName.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblName.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblG.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblGender.setFont(new Font("Serif", Font.PLAIN, 24));;
                            lblIntake.setFont(new Font("Serif", Font.PLAIN, 24));
                            txtIntake.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblRole.setFont(new Font("Serif", Font.PLAIN, 24));
                            cRole.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblEmail.setFont(new Font("Serif", Font.PLAIN, 24));
                            txtEmail.setFont(new Font("Serif", Font.PLAIN, 24));
                            lblPhone.setFont(new Font("Serif", Font.PLAIN, 24));
                            txtPhone.setFont(new Font("Serif", Font.PLAIN, 24));
                            
                            lblDetails.setBounds(250,180,500,40);
                            lblClientId.setBounds(250,260,170,30);
                            lblId.setBounds(420,260,400,30);
                            lblClientName.setBounds(250,330,170,30);
                            lblName.setBounds(420,330,600,30);
                            lblG.setBounds(1050,320,150,30);
                            lblGender.setBounds(1200,320,400,30);
                            lblIntake.setBounds(250,420,200,30);
                            txtIntake.setBounds(250,450,600,40);
                            lblRole.setBounds(1050,420,100,30);
                            cRole.setBounds(1050,450,600,40);
                            lblEmail.setBounds(250,560,100,30);
                            txtEmail.setBounds(250,590,600,40);
                            lblPhone.setBounds(1050,560,200,30);
                            txtPhone.setBounds(1050,590,600,40);
                            
                            pnlDetails.add(lblDetails);
                            pnlDetails.add(lblClientId);
                            pnlDetails.add(lblId);
                            pnlDetails.add(lblClientName);
                            pnlDetails.add(lblName);
                            pnlDetails.add(lblG);
                            pnlDetails.add(lblGender);
                            pnlDetails.add(lblIntake);
                            pnlDetails.add(txtIntake);
                            pnlDetails.add(lblRole);
                            pnlDetails.add(cRole);
                            pnlDetails.add(lblStatement);
                            pnlDetails.add(lblEmail);
                            pnlDetails.add(txtEmail);
                            pnlDetails.add(lblPhone);
                            pnlDetails.add(txtPhone);
                        }
                    }
                    
                    input.close();
                    
                }catch(FileNotFoundException ex){
                    JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                if (exists == false){
                    JOptionPane.showMessageDialog(null, "There is no such a client in database.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                    setVisible(false);
                    
                    EditClient ec2 = new EditClient();
                    ec2.setVisible(true);
                }
            }
        });
    }
}
