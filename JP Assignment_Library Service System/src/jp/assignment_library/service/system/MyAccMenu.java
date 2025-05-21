/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Dell Vostro
 */
public class MyAccMenu extends JFrame{
    Button btnMyProfile, btnChgPass, btnBack;
    
    public MyAccMenu(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(screenSize.width, screenSize.height);
        setResizable(false);
        
        setLocation(0,0);
        setTitle("My Account Menu");
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
        
        Label lblTitle = new Label("My Account Menu");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        pnlTitle.add(lblTitle);
        
        ImageIcon edit = new ImageIcon("images/Edit.png");
        JLabel lblEdit = new JLabel(edit);
        lblEdit.setBounds(370,180,400,400);
        
        ImageIcon chg = new ImageIcon("images/ChgPass.png");
        JLabel lblChgPass = new JLabel(chg);
        lblChgPass.setBounds(1120,180,400,400);
        
        btnMyProfile = new Button("MY PROFILE");
        btnChgPass = new Button("CHANGE PASSWORD");
        btnBack = new Button("BACK");
        
        btnMyProfile.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnChgPass.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnBack.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        btnMyProfile.setBounds(350,630,450,70);
        btnChgPass.setBounds(1100,630,450,70);
        btnBack.setBounds(30,50,150,50);
        
        btnMyProfile.setBackground(new Color(250,233,197));
        btnChgPass.setBackground(new Color(250,233,197));
        btnBack.setBackground(new Color(104,104,104));
        btnBack.setForeground(Color.WHITE);
        
        pnlButton.add(lblEdit);
        pnlButton.add(lblChgPass);
        pnlButton.add(btnMyProfile);
        pnlButton.add(btnChgPass);
        pnlButton.add(btnBack);
        
        btnMyProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                MyProfile p = new MyProfile();
                p.setVisible(true);
            }
        });
        
        btnChgPass.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                ChangePassword pass = new ChangePassword();
                pass.setVisible(true);
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
