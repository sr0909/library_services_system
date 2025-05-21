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
public class ClientMenu extends JFrame{
    Button btnEditClient;
    Button btnRecord;
    Button btnBack;
    
    public ClientMenu(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(screenSize.width, screenSize.height);
        setResizable(false);
        
        setLocation(0,0);
        setTitle("Client Menu");
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
        
        Label lblTitle = new Label("Client Menu");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        pnlTitle.add(lblTitle);
        
        ImageIcon edit = new ImageIcon("images/Edit.png");
        JLabel lblEdit = new JLabel(edit);
        lblEdit.setBounds(370,180,400,400);
        
        ImageIcon record = new ImageIcon("images/Report.png");
        JLabel lblRecord = new JLabel(record);
        lblRecord.setBounds(1120,180,400,400);
        
        btnEditClient = new Button("EDIT CLIENT");
        btnRecord = new Button("CLIENT BORROWING RECORD");
        btnBack = new Button("BACK");
        
        btnEditClient.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnRecord .setFont(new Font("Monospaced", Font.BOLD, 30));
        btnBack.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        btnEditClient.setBounds(350,630,450,70);
        btnRecord .setBounds(1100,630,450,70);
        btnBack.setBounds(30,50,150,50);
        
        btnEditClient.setBackground(new Color(251,254,199));
        btnRecord .setBackground(new Color(251,254,199));
        btnBack.setBackground(new Color(104,104,104));
        btnBack.setForeground(Color.WHITE);
        
        pnlButton.add(lblEdit);
        pnlButton.add(lblRecord);
        pnlButton.add(btnEditClient);
        pnlButton.add(btnRecord );
        pnlButton.add(btnBack);
        
        btnEditClient.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                EditClient editC = new EditClient();
                editC.setVisible(true);
            }
        });
        
        btnRecord.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                BorrowRecord br = new BorrowRecord();
                br.setVisible(true);
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
