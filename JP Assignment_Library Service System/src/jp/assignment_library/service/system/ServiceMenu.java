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
public class ServiceMenu extends JFrame{
     Button btnBack, btnBorrow, btnRenew, btnReturn;
    
    public ServiceMenu(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(screenSize.width, screenSize.height);
        setResizable(false);
        
        setLocation(0,0);
        setTitle("Service Menu");
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
        
        Label lblTitle = new Label("Service Menu");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        pnlTitle.add(lblTitle);
        
        ImageIcon borrow = new ImageIcon("images/Borrow.jpg");
        JLabel lblBorrow = new JLabel(borrow);
        lblBorrow.setBounds(90,180,400,400);
        
        ImageIcon renew = new ImageIcon("images/Renew.png");
        JLabel lblRenew = new JLabel(renew);
        lblRenew.setBounds(750,180,400,400);
        
        ImageIcon r = new ImageIcon("images/Return.png");
        JLabel lblReturn = new JLabel(r);
        lblReturn.setBounds(1400,180,400,400);
        
        btnBorrow = new Button("BORROW BOOK");
        btnRenew = new Button("RENEW BOOK");
        btnReturn = new Button("RETURN BOOK");
        btnBack = new Button("BACK");
        
        btnBorrow.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnRenew.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnReturn.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnBack.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        btnBorrow.setBounds(80,630,450,70);
        btnRenew.setBounds(730,630,450,70);
        btnReturn.setBounds(1380,630,450,70);
        btnBack.setBounds(30,50,150,50);
        
        btnBorrow.setBackground(new Color(202,255,255));
        btnRenew.setBackground(new Color(202,255,255));
        btnReturn.setBackground(new Color(202,255,255));
        btnBack.setBackground(new Color(104,104,104));
        btnBack.setForeground(Color.WHITE);
        
        pnlButton.add(lblBorrow);
        pnlButton.add(lblRenew);
        pnlButton.add(lblReturn);
        pnlButton.add(btnBorrow);
        pnlButton.add(btnRenew);
        pnlButton.add(btnReturn);
        pnlButton.add(btnBack);
        
        btnBorrow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                BorrowBook bb = new BorrowBook();
                bb.setVisible(true);
            }
        });
        
        btnRenew.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                RenewBook rb = new RenewBook();
                rb.setVisible(true);
            }
        });
        
        btnReturn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                ReturnBook returnb = new ReturnBook();
                returnb.setVisible(true);
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
