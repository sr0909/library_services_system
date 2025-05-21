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
public class EditClientSuccess extends NavBar{
    private String editClientId, name, gender, intake, email, phone, role;;
    
    public EditClientSuccess(){
        super();
        
        setTitle("Edit Client");
        setLayout(new BorderLayout(0,0));
    }
    
    public EditClientSuccess(String id){
        super();
        
        setTitle("Edit Client");
        setLayout(new BorderLayout(0,0));
        
        editClientId = id;
        
        Panel pnlTitle = new Panel();
        Panel pnlDetails = new Panel(null);
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        
        Label lblTitle = new Label("Edit Client");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
  
        File inputFile = new File("Client.txt");
        
        try{
            Scanner input = new Scanner(inputFile);
      
            while(input.hasNextLine()){
                String data = input.nextLine();
                        
                String[] clientDetails = data.split(":");
                String dbClientID = clientDetails[0];
                        
                if (dbClientID.equals(editClientId)){
                    name = clientDetails[1];
                    gender = clientDetails[2];
                    intake = clientDetails[3];
                    email = clientDetails[4];
                    phone = clientDetails[5];
                    role = clientDetails[6];
                    
                    ImageIcon c = new ImageIcon("images/EditClient.jpg");
                    JLabel lblClient = new JLabel(c);
                    lblClient.setBounds(350,150,500,500);
                    
                    Label lblDetails = new Label("Client Details");
                    Label lblId = new Label("Client ID: ");
                    Label lblClientId = new Label(editClientId);
                    Label lblCName = new Label("Client Name: ");
                    Label lblName = new Label(name);
                    Label lblG = new Label("Gender: ");
                    Label lblGender = new Label(gender);
                    Label lblI = new Label("Intake Code: ");
                    Label lblIntake = new Label(intake);
                    Label lblE = new Label("Email: ");
                    Label lblEmail = new Label(email);
                    Label lblP = new Label("Phone: ");
                    Label lblPhone = new Label(phone);
                    Label lblR = new Label("Role: ");
                    Label lblRole = new Label(role);
                    
                    lblDetails.setFont(new Font("Serif", Font.BOLD, 34));
                    lblId.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblClientId.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblCName.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblName.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblG.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblGender.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblI.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblIntake.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblE.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblEmail.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblP.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblPhone.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblR.setFont(new Font("Serif", Font.PLAIN, 24));
                    lblRole.setFont(new Font("Serif", Font.PLAIN, 24));

                    lblDetails.setBounds(350,90,500,40);
                    lblId.setBounds(1050,170,150,30);
                    lblClientId.setBounds(1200,170,400,30);
                    lblCName.setBounds(1050,240,150,30);
                    lblName.setBounds(1200,240,600,30);
                    lblG.setBounds(1050,310,150,30);
                    lblGender.setBounds(1200,310,400,30);
                    lblR.setBounds(1050,380,150,30);
                    lblRole.setBounds(1200,380,600,30);
                    lblI.setBounds(1050,450,150,30);
                    lblIntake.setBounds(1200,450,600,30);
                    lblE.setBounds(1050,520,150,30);
                    lblEmail.setBounds(1200,520,600,30);
                    lblP.setBounds(1050,590,150,30);
                    lblPhone.setBounds(1200,590,600,30);

                    pnlDetails.add(lblDetails);
                    pnlDetails.add(lblClient);
                    pnlDetails.add(lblId);
                    pnlDetails.add(lblClientId);
                    pnlDetails.add(lblCName);
                    pnlDetails.add(lblName);
                    pnlDetails.add(lblG);
                    pnlDetails.add(lblGender);
                    pnlDetails.add(lblI);
                    pnlDetails.add(lblIntake);
                    pnlDetails.add(lblR);
                    pnlDetails.add(lblRole);
                    pnlDetails.add(lblE);
                    pnlDetails.add(lblEmail);
                    pnlDetails.add(lblP);
                    pnlDetails.add(lblPhone);
                    
                    Button btnEditOther = new Button("EDIT ANOTHER CLIENT");
                    btnEditOther.setBackground(new Color(134,83,55));
                    btnEditOther.setForeground(Color.WHITE);
                    btnEditOther.setFont(new Font("Monospaced", Font.BOLD, 26));
                    btnEditOther.setBounds(720,800,500,50);
                    
                    pnlDetails.add(btnEditOther);
                    
                    btnEditOther.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            setVisible(false);
                    
                            EditClient eClient = new EditClient();
                            eClient.setVisible(true);
                        }
                    });
                }
            }
                    
            input.close();
                    
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
