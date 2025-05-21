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
public class MainMenu extends JFrame{
    Button btnRegister;
    Button btnBook;
    Button btnClient;
    Button btnService;
    Button btnFine;
    Button btnMyAcc;
    
    public MainMenu(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(screenSize.width, screenSize.height);
        setResizable(false);
        
        setLocation(0,0);
        setTitle("Main Menu");
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
        
        Label lblTitle = new Label("Main Menu");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        pnlTitle.add(lblTitle);
        
        ImageIcon register = new ImageIcon("images/Register.png");
        JLabel lblRegister = new JLabel(register);
        lblRegister.setBounds(100,70,200,200);
        
        ImageIcon client = new ImageIcon("images/Client.png");
        JLabel lblClient = new JLabel(client);
        lblClient.setBounds(1100,70,200,200);
        
        ImageIcon book = new ImageIcon("images/Book.png");
        JLabel lblBook = new JLabel(book);
        lblBook.setBounds(100,360,200,200);
        
        ImageIcon service = new ImageIcon("images/Service.png");
        JLabel lblService = new JLabel(service);
        lblService.setBounds(1100,360,200,200);
        
        ImageIcon fine = new ImageIcon("images/Fine.png");
        JLabel lblFine = new JLabel(fine);
        lblFine.setBounds(100,650,200,200);
        
        ImageIcon acc = new ImageIcon("images/MyAcc.png");
        JLabel lblAcc = new JLabel(acc);
        lblAcc.setBounds(1100,650,200,200);

        btnRegister = new Button("REGISTRATION");
        btnClient = new Button("CLIENT");
        btnBook = new Button("MANAGE BOOK");
        btnService = new Button("SERVICE");
        btnFine = new Button("FINE");
        btnMyAcc = new Button("MY ACCOUNT");

        btnRegister.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnClient.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnBook.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnService.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnFine.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnMyAcc.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        btnRegister.setBounds(350,140,450,70);
        btnClient.setBounds(1350,140,450,70);
        btnBook.setBounds(350,430,450,70);
        btnService.setBounds(1350,430,450,70);
        btnFine.setBounds(350,720,450,70);
        btnMyAcc.setBounds(1350,720,450,70);
        
        btnRegister.setBackground(new Color(255,174,201));
        btnClient.setBackground(new Color(251,254,199));
        btnBook.setBackground(new Color(255,232,151));
        btnService.setBackground(new Color(202,255,255));
        btnFine.setBackground(new Color(236,249,198));
        btnMyAcc.setBackground(new Color(250,233,197));
        
        pnlButton.add(lblRegister);
        pnlButton.add(lblClient);
        pnlButton.add(lblBook);
        pnlButton.add(lblService);
        pnlButton.add(lblFine);
        pnlButton.add(lblAcc);
        pnlButton.add(btnRegister);
        pnlButton.add(btnClient);
        pnlButton.add(btnBook);
        pnlButton.add(btnService);
        pnlButton.add(btnFine);
        pnlButton.add(btnMyAcc);

        btnRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                RegistrationMenu rm = new RegistrationMenu();
                rm.setVisible(true);
            }
        });
        
        btnClient.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                ClientMenu cm = new ClientMenu();
                cm.setVisible(true);
            }
        });
        
        btnBook.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                BookMenu bm = new BookMenu();
                bm.setVisible(true);
            }
        });
        
        btnService.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                ServiceMenu sm = new ServiceMenu();
                sm.setVisible(true);
            }
        });
        
        btnFine.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                Fine f = new Fine();
                f.setVisible(true);
            }
        });
        
        btnMyAcc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                MyAccMenu acc = new MyAccMenu();
                acc.setVisible(true);
            }
        });
    }
}
