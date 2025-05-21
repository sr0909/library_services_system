/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Dell Vostro
 */
public class Login extends JFrame implements ActionListener{
    TextField txtUsername;
    
    //Link: https://docs.oracle.com/javase/tutorial/uiswing/components/passwordfield.html
    //Date Accessed: 2 March 2021
    JPasswordField txtPass;
    Button btnLogin;
    
    String username, pass;
       
    public Login() throws FileNotFoundException{
        //Link: https://stackoverflow.com/questions/6777135/java-jframe-size-according-to-screen-resolution
        //Date Accessed: 2 March 2021
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(screenSize.width, screenSize.height);
        setResizable(false);
        
        setLocation(0,0);
        setTitle("Login");
        setLayout(new BorderLayout(10,0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Link: https://stackoverflow.com/questions/26913923/how-do-you-change-the-size-and-font-of-a-joptionpane
        //Date Accessed: 11 March 2021
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 18));

        initGUI();
        setVisible(true);
    }
    
    private void initGUI() throws FileNotFoundException{
        JPanel pnlWelcome = new JPanel(null);
        JPanel pnlLogin = new JPanel(null);
        
        add(pnlWelcome, "North");
        add(pnlLogin, "Center");
        
        pnlWelcome.setPreferredSize(new Dimension(1000,300));
        pnlWelcome.setMaximumSize(new Dimension(1000,300));
        
        pnlWelcome.setBackground(Color.WHITE);
        pnlLogin.setBackground(new Color(227,201,187));
        
        //Link: https://www.dummies.com/programming/java/how-to-write-java-code-to-show-an-image-on-the-screen/
        //Date Accessed: 9 March 2021
        ImageIcon apuicon = new ImageIcon("images/Apu.jpg");
        JLabel lblApuImage = new JLabel(apuicon);
        
        lblApuImage.setBounds(50,0,300,300);
        pnlWelcome.add(lblApuImage);
        
        Label lblWelcome = new Label("Welcome to APU Library Service System!");
       
        lblWelcome.setFont(new Font("DialogInput", Font.BOLD, 60));
        lblWelcome.setBounds(400,110,1500,50);
        
        pnlWelcome.add(lblWelcome);
        
        //Link: https://www.dummies.com/programming/java/how-to-write-java-code-to-show-an-image-on-the-screen/
        //Date Accessed: 9 March 2021
        ImageIcon icon = new ImageIcon("images/Login.jpg");
        JLabel lblImage = new JLabel(icon);

        lblImage.setBounds(0,0,1400,750);
        pnlLogin.add(lblImage);

        Label lblLogin = new Label("Login");
        Label lblUsername = new Label("Username");
        txtUsername = new TextField(25);
        Label lblPass = new Label("Password");
        txtPass = new JPasswordField(25);
        btnLogin = new Button("LOGIN");
        
        btnLogin.setBackground(new Color(134,83,55));
        btnLogin.setForeground(Color.WHITE);
        
        //Link: https://www.java-examples.com/jlabel-set-font-example
        //Date Accessed: 2 March 2021
        lblLogin.setFont(new Font("Serif", Font.BOLD, 50));
        lblUsername.setFont(new Font("Serif", Font.PLAIN, 24));
        txtUsername.setFont(new Font("Serif", Font.PLAIN, 24));
        lblPass.setFont(new Font("Serif", Font.PLAIN, 24));
        txtPass.setFont(new Font("Serif", Font.PLAIN, 24));
        btnLogin.setFont(new Font("Monospaced", Font.BOLD, 26));
        
        //Link: https://stackoverflow.com/questions/19415170/what-is-setbounds-and-how-do-i-use-it
        //Date Accessed: 2 March 2021
        lblLogin.setBounds(1450,70,200,70);
        lblUsername.setBounds(1450,160,100,30);
        txtUsername.setBounds(1450,190,420,40);
        lblPass.setBounds(1450,270,100,30);
        txtPass.setBounds(1450,300,420,40);
        btnLogin.setBounds(1450,450,420,50);
        
        pnlLogin.add(lblLogin);
        pnlLogin.add(lblUsername);
        pnlLogin.add(txtUsername);
        pnlLogin.add(lblPass);
        pnlLogin.add(txtPass);
        pnlLogin.add(btnLogin);
        
        btnLogin.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        File inputFile = new File("Librarian.txt");
                
        username = txtUsername.getText();
        pass = String.valueOf(txtPass.getPassword());
        
        if (username.equals("") || pass.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter your username and/or password to login.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            boolean isLoginSuccessful = false;

            //Link: https://gist.github.com/0wlbear/8e0d24abe8fdd7133c96
            //Date accessed: 2 March 2021
            try{
                Scanner input = new Scanner(inputFile);

                while(input.hasNextLine()){
                    String data = input.nextLine();

                    String[] details = data.split(":");
                    String dbId = details[0];
                    String dbUsername = details[2];
                    String dbPass = details[3];

                    if(username.equals(dbUsername) && pass.equals(dbPass)){
                        isLoginSuccessful = true;

                        JOptionPane.showMessageDialog(null, "Login Successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                        Librarian l = new Librarian(dbId);
                        l.setVisible(false);

                        setVisible(false);

                        MainMenu mm = new MainMenu();
                        mm.setVisible(true);
                    }
                }

                input.close();

                if (isLoginSuccessful == false){
                    JOptionPane.showMessageDialog(null, "Username or password is incorrect. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);

                    txtUsername.setText("");
                    txtPass.setText("");

                    //Link: https://stackoverflow.com/questions/4640138/setting-the-focus-to-a-text-field
                    //Date Accessed: 2 March 2021
                    txtUsername.requestFocusInWindow();
                }

            }catch (FileNotFoundException ex){
                JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
