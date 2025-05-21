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
public class BookMenu extends JFrame{
    Button btnAdd, btnEdit, btnDelete, btnBorrowStatus, btnBack;
    
    public BookMenu(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(screenSize.width, screenSize.height);
        setResizable(false);
        
        setLocation(0,0);
        setTitle("Manage Book Menu");
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
        
        Label lblTitle = new Label("Manage Book Menu");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        pnlTitle.add(lblTitle);
        
        ImageIcon add = new ImageIcon("images/AddBook.png");
        JLabel lblAdd = new JLabel(add);
        lblAdd.setBounds(430,80,250,250);
        
        ImageIcon edit = new ImageIcon("images/EditBook.png");
        JLabel lblEdit = new JLabel(edit);
        lblEdit.setBounds(1180,80,250,250);
        
        ImageIcon delete = new ImageIcon("images/DeleteBook.png");
        JLabel lblDelete = new JLabel(delete);
        lblDelete.setBounds(430,550,250,250);
        
        ImageIcon borrow = new ImageIcon("images/BookBorrowingStatus.png");
        JLabel lblBorrow = new JLabel(borrow);
        lblBorrow.setBounds(1180,550,250,250);
        
        btnAdd = new Button("ADD BOOK");
        btnEdit = new Button("EDIT BOOK");
        btnDelete = new Button("DELETE BOOK");
        btnBorrowStatus = new Button("BOOK BORROWING STATUS");
        btnBack = new Button("BACK");
        
        btnAdd.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnEdit.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnDelete.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnBorrowStatus.setFont(new Font("Monospaced", Font.BOLD, 30));
        btnBack.setFont(new Font("Monospaced", Font.BOLD, 30));
        
        btnAdd.setBounds(350,360,450,70);
        btnEdit.setBounds(1100,360,450,70);
        btnDelete.setBounds(350,830,450,70);
        btnBorrowStatus.setBounds(1100,830,450,70);
        btnBack.setBounds(30,30,150,50);
        
        btnAdd.setBackground(new Color(255,232,151));
        btnEdit.setBackground(new Color(255,232,151));
        btnDelete.setBackground(new Color(255,232,151));
        btnBorrowStatus.setBackground(new Color(255,232,151));
        btnBack.setBackground(new Color(104,104,104));
        btnBack.setForeground(Color.WHITE);
        
        pnlButton.add(lblAdd);
        pnlButton.add(lblEdit);
        pnlButton.add(lblDelete);
        pnlButton.add(lblBorrow);
        pnlButton.add(btnAdd);
        pnlButton.add(btnEdit);
        pnlButton.add(btnDelete);
        pnlButton.add(btnBorrowStatus);
        pnlButton.add(btnBack);
        
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                AddBook ab = new AddBook();
                ab.setVisible(true);
            }
        });
        
        btnEdit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                EditBook eb = new EditBook();
                eb.setVisible(true);
            }
        });
        
        btnDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                DeleteBook db = new DeleteBook();
                db.setVisible(true);
            }
        });
        
        btnBorrowStatus.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                
                BookBorrowingStatus bbs = new BookBorrowingStatus();
                bbs.setVisible(true);
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
