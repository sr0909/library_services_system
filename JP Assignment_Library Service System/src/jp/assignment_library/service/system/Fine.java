/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.ParseException;
/**
 *
 * @author Dell Vostro
 */
public class Fine extends NavBar{
    private int maxId;
    
    public Fine(){
        super();
        
        setTitle("Fine");
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
        Panel pnlDetails = new Panel();
        Panel pnlButton = new Panel();
        
        pnlButton.setPreferredSize(new Dimension(1000,100));
        pnlButton.setMaximumSize(new Dimension(1000,100));
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));
        pnlButton.setBackground(new Color(227,201,187));
        
        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        add(pnlButton, "South");
        
        Label lblTitle = new Label("Fine");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        //Link: https://stackoverflow.com/questions/2320812/jtable-wont-show-column-headers
        //Date Accessed: 7 March 2021
        JScrollPane scrollPane = new JScrollPane();
        
        //Link: https://www.tutorialspoint.com/how-to-create-defaulttablemodel-which-is-an-implementation-of-tablemodel
        //Date Accessed: 7 March 2021
        DefaultTableModel tbl = new DefaultTableModel();
        JTable table = new JTable(tbl);
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(1600,800));
        
        //Link: https://stackoverflow.com/questions/7212114/how-to-control-font-style-color-and-size-inside-a-jtable
        //Date Accessed: 7 March 2021
        table.setRowHeight(40);
        table.setFont(new Font("Serif", Font.PLAIN, 24));
        
        //Link: https://stackoverflow.com/questions/4408644/how-can-i-change-the-font-of-a-jtables-header
        //Date Accessed: 7 March 2021
        table.getTableHeader().setFont(new Font("DialogInput", Font.BOLD, 28));
        table.getTableHeader().setBackground(new Color(128,0,64));
        table.getTableHeader().setForeground(Color.WHITE);
        
        tbl.addColumn("ID");
        tbl.addColumn("Client Name");
        tbl.addColumn("Book Name");
        tbl.addColumn("Borrow Date");
        tbl.addColumn("Return Date");
        tbl.addColumn("Overdue(Days)");
        tbl.addColumn("Fine");
        
        //Link: https://www.tutorialspoint.com/how-to-change-each-column-width-of-a-jtable-in-java
        //Date Accessed: 11 March 2021
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(380);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(130);
        table.getColumnModel().getColumn(6).setPreferredWidth(30);
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
        
        File inputFile = new File("Borrow.txt");
        
        try{
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNextLine()){
                String data = input.nextLine();
                
                String[] details = data.split(":");
                String dbId = details[0];

                maxId = Integer.parseInt(dbId);
            }
            
            input.close();
            
            for (int i=0; i < maxId; i++){
                int no = i + 1;
                String num = String.valueOf(no);
                
                Scanner inputBorrow = new Scanner(inputFile);
                
                while(inputBorrow.hasNextLine()){
                    String dataBorrow = inputBorrow.nextLine();

                    String[] borrowDetails = dataBorrow.split(":");
                    String dbId = borrowDetails[0];
                    String dbStatus = borrowDetails[5];
                    String dbPayStatus = borrowDetails[8];
                    
                    if (dbId.equals(num)){
                        if (dbStatus.equals("Overdue") && dbPayStatus.equals("Unpaid")){
                            String id, client, book, borrowDate, returnDate, renewTime;
                            
                            id = borrowDetails[0];
                            book = borrowDetails[1];
                            client = borrowDetails[2];
                            borrowDate = borrowDetails[3];
                            returnDate = borrowDetails[4];
                            renewTime = borrowDetails[6];
                            
                            int rTime = Integer.parseInt(renewTime);
                            
                            if (rTime == 1){
                                returnDate = borrowDetails[7];
                            }
                            
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                            Date todayDate = new Date();
                            String today = formatter.format(todayDate);
                            
                            try{
                                //Link: https://beginnersbook.com/2013/04/number-of-days-calculation-between-two-dates/
                                //Date Accessed: 7 March 2021
                                Date dReturn = formatter.parse(returnDate);
                                Date dToday = formatter.parse(today);

                                long diff = dToday.getTime() - dReturn.getTime();

                                float diffD = (diff / (1000*60*60*24));
                                
                                //Link: https://stackoverflow.com/questions/703396/how-to-nicely-format-floating-numbers-to-string-without-unnecessary-decimal-0s
                                //Date Accessed: 7 March 2021
                                String diffDays = String.format("%.0f", diffD);
                                String fine = "RM " + diffDays;
 
                                tbl.insertRow(0, new Object[]{id, client, book, borrowDate, returnDate, diffDays, fine});
                                
                            }catch(ParseException ex){
                                JOptionPane.showMessageDialog(null, "There is an error occurred during the conversion from String to Date format.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            
                        }
                    }
                }
                
                inputBorrow.close();
            } 
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        pnlDetails.add(scrollPane);
        
        //Link: https://kodejava.org/how-do-i-programmatically-select-jtables-rows/
        //Date Accessed: 7 March 2021
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        
        Button btnPay = new Button("PAY");
        btnPay.setBackground(new Color(134,83,55));
        btnPay.setForeground(Color.WHITE);
        btnPay.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnPay.setPreferredSize(new Dimension(500,50));
    
        pnlButton.add(btnPay);
        
        btnPay.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Link: https://stackoverflow.com/questions/29345792/java-jtable-getting-the-data-of-the-selected-row
                //Date Accessed: 7 March 2021
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(null, "Please select a client to pay the fine.", "WARNING", JOptionPane.WARNING_MESSAGE);
                }else{
                    String selectedId = table.getModel().getValueAt(selectedRow, 0).toString();
                    String selectedFine = table.getModel().getValueAt(selectedRow, 5).toString();
 
                    setVisible(false);
                    
                    Payment pay = new Payment(selectedId, selectedFine);
                    pay.setVisible(true);
                }
            }
        });
    }
}