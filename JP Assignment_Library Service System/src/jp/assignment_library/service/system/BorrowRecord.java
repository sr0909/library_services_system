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
public class BorrowRecord extends Register{
    private boolean exists;
    private String searchName, bookName, borrowDate, returnDate, renewTime, status, fineStatus, exactReturnDate;
    TextField txtSearchName;
    
    public BorrowRecord(){
        super();
        
        setTitle("View Client Borrowing Record");
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
        
        Label lblTitle = new Label("View Client Borrowing Record");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        Label lblStatement = new Label("Please enter the client name that you want to view his borrowing records:");
        lblStatement.setFont(new Font("Serif", Font.PLAIN, 24));
        lblStatement.setBounds(180,50,710,30);
        pnlDetails.add(lblStatement);

        txtSearchName = new TextField(30);
        txtSearchName.setFont(new Font("Serif", Font.PLAIN, 24));
        txtSearchName.setBounds(900,45,600,40);
        pnlDetails.add(txtSearchName);
        
        //Link: https://stackoverflow.com/questions/2320812/jtable-wont-show-column-headers
        //Date Accessed: 7 March 2021
        JScrollPane scrollPane = new JScrollPane();
        
        //Link: https://www.tutorialspoint.com/how-to-create-defaulttablemodel-which-is-an-implementation-of-tablemodel
        //Date Accessed: 7 March 2021
        DefaultTableModel tbl = new DefaultTableModel();
        JTable table = new JTable(tbl);
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(1800,700));
        scrollPane.setBounds(60,150,1800,700);
        
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
        tbl.addColumn("Borrow Date");
        tbl.addColumn("Expect Return Date");
        tbl.addColumn("Status");
        tbl.addColumn("Fine");
        tbl.addColumn("Real Return Date");
        
        //Link: https://www.tutorialspoint.com/how-to-change-each-column-width-of-a-jtable-in-java
        //Date Accessed: 11 March 2021
        table.getColumnModel().getColumn(0).setPreferredWidth(380);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);

        pnlDetails.add(scrollPane);
        
        Button btnOk = new Button("OK");
        btnOk.setBackground(new Color(134,83,55));
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnOk.setBounds(1510,45,200,40);

        pnlDetails.add(btnOk);
        
        btnOk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                searchName = txtSearchName.getText();
                
                if (searchName.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter a client name to search his borrowing record.", "WARNING", JOptionPane.WARNING_MESSAGE);
                }else{
                    btnOk.setVisible(false);
                    Button btnFindOther = new Button("FIND ANOTHER");
                    btnFindOther.setBackground(new Color(134,83,55));
                    btnFindOther.setForeground(Color.WHITE);
                    btnFindOther.setFont(new Font("Monospaced", Font.BOLD, 26));
                    btnFindOther.setBounds(1510,45,250,40);

                    pnlDetails.add(btnFindOther);

                    btnFindOther.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            setVisible(false);

                            BorrowRecord br2 = new BorrowRecord();
                            br2.setVisible(true);
                        }
                    });

                    File inputFile = new File("Borrow.txt");

                    try{
                        Scanner input = new Scanner(inputFile);

                        exists = false;

                        while(input.hasNextLine()){
                            String data = input.nextLine();

                            String[] borrowDetails = data.split(":");
                            String dbClientName = borrowDetails[2];

                            if (dbClientName.equals(searchName)){
                                exists = true;

                                bookName = borrowDetails[1];
                                borrowDate = borrowDetails[3];
                                status = borrowDetails[5];
                                renewTime = borrowDetails[6];
                                fineStatus = borrowDetails[8];
                                exactReturnDate = borrowDetails[9];

                                int rTime = Integer.parseInt(renewTime);

                                if (rTime == 0){
                                    returnDate = borrowDetails[4];
                                }else{
                                    returnDate = borrowDetails[7];
                                }

                                tbl.insertRow(0, new Object[]{bookName, borrowDate, returnDate, status, fineStatus, exactReturnDate});
                            }
                        }
                    }catch(FileNotFoundException ex){
                        JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                    if (exists == false){
                        JOptionPane.showMessageDialog(null, "Invalid client or this client has no any borrowing record.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

                        setVisible(false);

                        BorrowRecord br2 = new BorrowRecord();
                        br2.setVisible(true);
                    }
                }
            }
        });
    }
}
