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
import java.text.ParseException;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author Dell Vostro
 */
public class BookBorrowingStatus extends NavBar{
    private String bookName, borrowStatus, client;
    DefaultTableModel tbl;
    
    public BookBorrowingStatus(){
        super();
        
        setTitle("Book Borrowing Status");
        setLayout(new BorderLayout(0,0));
        
        try{
            Overdue over = new Overdue();
        }catch(ParseException ex){
            JOptionPane.showMessageDialog(null, "There is an error occurred during the conversion from String to Date format.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        initGUI();
    }
    
    private void initGUI(){
        Panel pnlTitle = new Panel();
        Panel pnlDetails = new Panel(null);
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));
        
        Label lblTitle = new Label("Book Borrowing Status");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        //Link: https://stackoverflow.com/questions/2320812/jtable-wont-show-column-headers
        //Date Accessed: 7 March 2021
        JScrollPane scrollPane = new JScrollPane();
        
        //Link: https://www.tutorialspoint.com/how-to-create-defaulttablemodel-which-is-an-implementation-of-tablemodel
        //Date Accessed: 7 March 2021
        tbl = new DefaultTableModel();
        JTable table = new JTable(tbl);
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(1700,800));
        scrollPane.setBounds(100,60,1700,800);
        
        //Link: https://stackoverflow.com/questions/7212114/how-to-control-font-style-color-and-size-inside-a-jtable
        //Date Accessed: 7 March 2021
        table.setRowHeight(40);
        table.setFont(new Font("Serif", Font.PLAIN, 24));
        
        //Link: https://stackoverflow.com/questions/4408644/how-can-i-change-the-font-of-a-jtables-header
        //Date Accessed: 7 March 2021
        table.getTableHeader().setFont(new Font("DialogInput", Font.BOLD, 28));
        table.getTableHeader().setBackground(new Color(128,0,64));
        table.getTableHeader().setForeground(Color.WHITE);

        tbl.addColumn("Book Name");
        tbl.addColumn("Borrowing Status");
        tbl.addColumn("Borrowed By");
        
        //Link: https://www.tutorialspoint.com/how-to-change-each-column-width-of-a-jtable-in-java
        //Date Accessed: 11 March 2021
        table.getColumnModel().getColumn(0).setPreferredWidth(450);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

        pnlDetails.add(scrollPane);
        
        getBorrowingStatus();
    }
    
    private void getBorrowingStatus(){
        File inputFile = new File("Book.txt");

        try{
            Scanner input = new Scanner(inputFile);

            while(input.hasNextLine()){
                String data = input.nextLine();

                String[] book = data.split(":");
                String dbBook = book[1];
                
                boolean exists = false;
                bookName = dbBook;
                
                File inputBorrowFile = new File("Borrow.txt");
                
                Scanner inputBorrow = new Scanner(inputBorrowFile);
                
                while(inputBorrow.hasNextLine()){
                    String info = inputBorrow.nextLine();
                    
                    String[] borrow = info.split(":");
                    String dbBorrowBook = borrow[1];
                    
                    if (dbBook.equals(dbBorrowBook)){
                        exists = true;
                        
                        String dbStatus = borrow[5];
                        
                        if (dbStatus.equals("Borrowing") || dbStatus.equals("Overdue")){
                            borrowStatus = "Not Available";
                            client = borrow[2];
                        }else{
                            borrowStatus = "Available";
                            client = "-";
                        }
                    }
                }
                
                if (exists == false){
                    borrowStatus = "Available";
                    client = "-";
                }
                
                tbl.insertRow(0, new Object[]{bookName, borrowStatus, client});
            }
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
