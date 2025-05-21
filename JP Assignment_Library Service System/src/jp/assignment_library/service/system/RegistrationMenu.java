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
public class RegistrationMenu extends JFrame{
    Button btnLibrarian;
    Button btnClient;
    Button btnBack;
    
    public RegistrationMenu(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(screenSize.width, screenSize.height);
        setResizable(false);
        
        setLocation(0,0);
        setTitle("Registration Menu");
        setLayout(new BorderLayout(0,0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initGUI();
        setVisible(true);
    }
    
    private void initGUI(){
        JPanel pnlTitle = new JPanel();
        JPanel pnlButton = new JPanel(null);
        
        pnlTitle.setBackground(Color.WHITE);
        pnlButton.setBackground(Color.WHITE);
        
        add(pnlTitle, "North");
        add(pnlButton, "Center");
        
        Label lblTitle = new Label("Registration Menu");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        pnlTitle.add(lblTitle);
        
        ImageIcon librarian = new ImageIcon("images/Librarian.png");
        JLabel lblLibrarian = new JLabel(librarian);
        lblLibrarian.setBounds(370,200,400,400);
        
        ImageIcon client = new ImageIcon("images/RegisterClient.jpg");
        JLabel lblClient = new JLabel(client);
        lblClient.setBounds(1125,200,400,400);
        
        btnLibrarian = new Button("REGISTER LIBRARIAN");
        btnClient = new Button("REGISTER CLIENT");
        btnBack = new Button("BACK");
        
        btnLibrarian.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnClient.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnBack.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        btnLibrarian.setBounds(350,650,450,70);
        btnClient.setBounds(1100,650,450,70);
        btnBack.setBounds(30,50,150,50);
        
        btnLibrarian.setBackground(new Color(255,174,201));
        btnClient.setBackground(new Color(255,174,201));
        btnBack.setBackground(new Color(104,104,104));
        btnBack.setForeground(Color.WHITE);
        
        pnlButton.add(lblLibrarian);
        pnlButton.add(lblClient);
        pnlButton.add(btnLibrarian);
        pnlButton.add(btnClient);
        pnlButton.add(btnBack);
        
        btnLibrarian.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String loginId = "";
                String dbrole = "";
        
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
                            dbrole = Librarian[8];
                        }
                    }
            
                    inputLibrarian.close();
                
                }catch(FileNotFoundException ex){
                    JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                if (dbrole.equals("Head of Librarian")){
                     setVisible(false);
                
                    Register_Librarian rl = new Register_Librarian();
                    rl.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "You are not given the privilege to register new librarian.", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        btnClient.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                RegisterClient rc = new RegisterClient();
                rc.setVisible(true);
            }
        });
        
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                MainMenu mm = new MainMenu();
                mm.setVisible(true);
            }
        });
    }
}
