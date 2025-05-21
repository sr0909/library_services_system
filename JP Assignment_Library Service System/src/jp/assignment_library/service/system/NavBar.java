/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
//Link: http://herongyang.com/Swing/JMenuBar-Menu-Listener-Interface.html
//Date Accessed: 3 March 2021
import javax.swing.event.*;
/**
 *
 * @author Dell Vostro
 */
//Link: http://www2.hawaii.edu/~takebaya/ics111/inheritance/inheritance.html
//Date Accessed: 3 March 2021
public class NavBar extends BasicWindow{
    JMenu mainMenu;
    JMenuItem librarianItem, clientItem, editClient, record, add, edit, delete, borrow, renew, returnBook, payFine, logout, exit, editProfile, 
              chgPass, borrowingRecord;

    public NavBar(){
        super();
        
        showNavBar();
    }
  
    public void showNavBar(){
        //Link: https://www.javatpoint.com/java-jmenuitem-and-jmenu#:~:text=The%20JMenuBar%20class%20is%20used,It%20inherits%20the%20JMenuItem%20class.
        //Link: https://www.tutorialspoint.com/swingexamples/creating_menu_bar.htm
        //Date Accessed: 3 March 2021
        final JMenuBar menuBar = new JMenuBar();
        
        menuBar.setBackground(Color.WHITE);

        mainMenu = new JMenu("Main Menu");
        JMenu registerMenu = new JMenu("Registration");
        JMenu clientMenu = new JMenu("Client");
        JMenu bookMenu = new JMenu("Manage Book");
        JMenu serviceMenu = new JMenu("Service");
        JMenu fineMenu = new JMenu("Fine");
        JMenu myAccMenu = new JMenu("My Account");
        JMenu logOutMenu = new JMenu("Log Out");
        
        mainMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        registerMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        clientMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        bookMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        serviceMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        fineMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        myAccMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        logOutMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        
        mainMenu.setPreferredSize(new Dimension(120,40));
        registerMenu.setPreferredSize(new Dimension(130,40));
        clientMenu.setPreferredSize(new Dimension(80,40));
        bookMenu.setPreferredSize(new Dimension(150,40));
        serviceMenu.setPreferredSize(new Dimension(100,40));
        fineMenu.setPreferredSize(new Dimension(70,40));
        myAccMenu.setPreferredSize(new Dimension(130,40));
        logOutMenu.setPreferredSize(new Dimension(100,40));
        
        librarianItem = new JMenuItem("Librarian Registration");
        clientItem = new JMenuItem("Client Registration");
        editClient = new JMenuItem("Edit Client");
        record = new JMenuItem("Client Borrowing Record");
        add = new JMenuItem("Add Book");
        edit = new JMenuItem("Edit Book");
        delete = new JMenuItem("Delete Book");
        borrowingRecord = new JMenuItem("Book Borrowing Status");
        borrow = new JMenuItem("Borrow Book");
        renew = new JMenuItem("Renew Book");
        returnBook = new JMenuItem("Return Book");
        payFine = new JMenuItem("Pay Fine");
        logout = new JMenuItem("Log Out");
        exit = new JMenuItem("Exit System");
        editProfile = new JMenuItem("My Profile");
        chgPass = new JMenuItem("Change Password");
        
        librarianItem.setFont(new Font("Arial", Font.PLAIN, 20));
        clientItem.setFont(new Font("Arial", Font.PLAIN, 20));
        editClient.setFont(new Font("Arial", Font.PLAIN, 20));
        record.setFont(new Font("Arial", Font.PLAIN, 20));
        add.setFont(new Font("Arial", Font.PLAIN, 20));
        edit.setFont(new Font("Arial", Font.PLAIN, 20));
        delete.setFont(new Font("Arial", Font.PLAIN, 20));
        borrowingRecord.setFont(new Font("Arial", Font.PLAIN, 20));
        borrow.setFont(new Font("Arial", Font.PLAIN, 20));
        renew.setFont(new Font("Arial", Font.PLAIN, 20));
        returnBook.setFont(new Font("Arial", Font.PLAIN, 20));
        payFine.setFont(new Font("Arial", Font.PLAIN, 20));
        logout.setFont(new Font("Arial", Font.PLAIN, 20));
        exit.setFont(new Font("Arial", Font.PLAIN, 20));
        editProfile.setFont(new Font("Arial", Font.PLAIN, 20));
        chgPass.setFont(new Font("Arial", Font.PLAIN, 20));
        
        registerMenu.add(librarianItem);
        registerMenu.add(clientItem);
        
        clientMenu.add(editClient);
        clientMenu.addSeparator();
        clientMenu.add(record);
        
        bookMenu.add(add);
        bookMenu.add(edit);
        bookMenu.add(delete);
        bookMenu.addSeparator();
        bookMenu.add(borrowingRecord);
        
        serviceMenu.add(borrow);
        serviceMenu.add(renew);
        serviceMenu.add(returnBook);
        
        fineMenu.add(payFine);
        
        myAccMenu.add(editProfile);
        //Link: https://zetcode.com/javaswing/menusandtoolbars/
        //Date Accessed: 3 March 2021
        myAccMenu.addSeparator();
        myAccMenu.add(chgPass);
        
        logOutMenu.add(logout);
        logOutMenu.addSeparator();
        logOutMenu.add(exit);
        
        menuBar.add(mainMenu);
        menuBar.add(registerMenu);
        menuBar.add(clientMenu);
        menuBar.add(bookMenu);
        menuBar.add(serviceMenu);
        menuBar.add(fineMenu);
        menuBar.add(myAccMenu);
        menuBar.add(logOutMenu);
        
        setJMenuBar(menuBar);
        
        //Link: http://www.java2s.com/Tutorial/Java/0240__Swing/UsingMenuListenertolistentomenucanceledselectedanddeselectedevents.htm
        //Date Accessed: 3 March 2021
        mainMenu.addMenuListener(new MenuListener(){
            public void menuSelected(MenuEvent evt){
                setVisible(false);

                MainMenu mm = new MainMenu();
                mm.setVisible(true);
            }
            
            public void menuDeselected(MenuEvent evt){}
            public void menuCanceled(MenuEvent evt){}
        });

        librarianItem.addActionListener(new ActionListener(){
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
        
        clientItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                RegisterClient rc = new RegisterClient();
                rc.setVisible(true);
            }
        });
        
        editClient.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                EditClient editC = new EditClient();
                editC.setVisible(true);
            }
        });
        
        record.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                BorrowRecord br = new BorrowRecord();
                br.setVisible(true);
            }
        });
        
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                AddBook ab = new AddBook();
                ab.setVisible(true);
            }
        });
        
        edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                EditBook eb = new EditBook();
                eb.setVisible(true);
            }
        });
        
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                DeleteBook db = new DeleteBook();
                db.setVisible(true);
            }
        });
        
        borrowingRecord.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                BookBorrowingStatus bbs = new BookBorrowingStatus();
                bbs.setVisible(true);
            }
        });
        
        borrow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                BorrowBook bb = new BorrowBook();
                bb.setVisible(true);
            }
        });
        
        renew.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                RenewBook rb = new RenewBook();
                rb.setVisible(true);
            }
        });
        
        returnBook.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                ReturnBook returnb = new ReturnBook();
                returnb.setVisible(true);
            }
        });
        
        payFine.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                Fine f = new Fine();
                f.setVisible(true);
            }
        });
        
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Link: https://www.tutorialspoint.com/swingexamples/show_confirm_dialog_with_yesno.htm
                //Date Accessed: 10 March 2021
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "LOG OUT", JOptionPane.YES_NO_OPTION, 
                             JOptionPane.QUESTION_MESSAGE);
                
                if (result == JOptionPane.YES_OPTION){
                    setVisible(false);

                    try {
                        Login loginPage = new Login();
                        loginPage.setVisible(true);

                    }catch (FileNotFoundException ex){
                        JOptionPane.showMessageDialog(null, "Database not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit this system?", "EXIT", 
                             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                if (result == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        
        editProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                MyProfile p = new MyProfile();
                p.setVisible(true);
            }
        });
        
        chgPass.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);

                ChangePassword pass = new ChangePassword();
                pass.setVisible(true);
            }
        });
    }
}
