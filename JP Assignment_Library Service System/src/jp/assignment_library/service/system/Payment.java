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
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author Dell Vostro
 */
public class Payment extends Register{
    Panel pnlDetails;
    Button btnPay;
    private String borrowId, fine, bookName, clientName, borrowDate, returnDate, renewTime, returnD, status, extendDate, fineStatus, 
                   exactReturnDate, oldInfo, today;
    private Double amount, change, charge;
    
    public Payment(){
        super();
    }
    
    public Payment(String id, String f){
        super();
        
        borrowId = id;
        fine = f;
        
        setTitle("Payment");
        setLayout(new BorderLayout(0,0));
        
        initGUI();
    }
    
    private void initGUI(){
        Panel pnlTitle = new Panel();
        pnlDetails = new Panel(null);
        
        pnlTitle.setBackground(new Color(227,201,187));
        pnlDetails.setBackground(new Color(227,201,187));

        add(pnlTitle, "North");
        add(pnlDetails, "Center");
        
        Label lblTitle = new Label("Fine");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 50));
        pnlTitle.add(lblTitle);
        
        getFineDetails();
                
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        today = formatter.format(todayDate);
        Label lblD = new Label("Today Date: ");
        Label lblDate = new Label(today);
        
        Label lblDetails = new Label("Payment Details");
        Label lblClientName = new Label("Client Name: ");
        Label lblClient = new Label(clientName);
        Label lblName = new Label("Book Name: ");
        Label lblBookName = new Label(bookName);
        Label lblBorrow = new Label("Borrow Date: ");
        Label lblBorrowDate = new Label(borrowDate);
        Label lblReturn = new Label("Return Date: ");
        Label lblReturnDate = new Label(returnDate);
        Label lblf = new Label("Fine (RM): ");
        Label lblFine = new Label(fine);
        Label lbla = new Label("Amount Paid (RM): ");
        TextField txtAmount = new TextField();
        
        ImageIcon payment = new ImageIcon("images/Payment1.png");
        JLabel lblPayment = new JLabel(payment);
        lblPayment.setBounds(250,370,700,300);

        lblDetails.setFont(new Font("Serif", Font.BOLD, 34));
        lblClientName.setFont(new Font("Serif", Font.PLAIN, 24));
        lblClient.setFont(new Font("Serif", Font.PLAIN, 24));
        lblName.setFont(new Font("Serif", Font.PLAIN, 24));
        lblBookName.setFont(new Font("Serif", Font.PLAIN, 24));
        lblBorrow.setFont(new Font("Serif", Font.PLAIN, 24));
        lblBorrowDate.setFont(new Font("Serif", Font.PLAIN, 24));
        lblReturn.setFont(new Font("Serif", Font.PLAIN, 24));
        lblReturnDate.setFont(new Font("Serif", Font.PLAIN, 24));
        lblD.setFont(new Font("DialogInput", Font.PLAIN, 24));
        lblDate.setFont(new Font("DialogInput", Font.PLAIN, 24));
        lblf.setFont(new Font("DialogInput", Font.BOLD, 24));
        lblFine.setFont(new Font("DialogInput", Font.BOLD, 24));
        lbla.setFont(new Font("DialogInput", Font.PLAIN, 24));
        txtAmount.setFont(new Font("DialogInput", Font.PLAIN, 24));
        
        lblDetails.setBounds(250,60,500,40);
        lblClientName.setBounds(250,140,200,30);
        lblClient.setBounds(450,140,1200,40);
        lblName.setBounds(250,200,200,30);
        lblBookName.setBounds(450,200,1200,30);
        lblBorrow.setBounds(250,260,200,30);
        lblBorrowDate.setBounds(450,260,400,30);
        lblReturn.setBounds(1050,260,200,30);
        lblReturnDate.setBounds(1350,260,400,30);
        lblD.setBounds(1050,430,280,30);
        lblDate.setBounds(1330,430,400,30);
        lblf.setBounds(1050,490,280,30);
        lblFine.setBounds(1330,490,400,30);
        lbla.setBounds(1050,550,280,30);
        txtAmount.setBounds(1330,550,400,40);
                                    
        pnlDetails.add(lblDetails);
        pnlDetails.add(lblClientName);
        pnlDetails.add(lblClient);
        pnlDetails.add(lblName);
        pnlDetails.add(lblBookName);
        pnlDetails.add(lblBorrow);
        pnlDetails.add(lblBorrowDate);
        pnlDetails.add(lblReturn);
        pnlDetails.add(lblReturnDate);
        pnlDetails.add(lblPayment);
        pnlDetails.add(lblD);
        pnlDetails.add(lblDate);
        pnlDetails.add(lblf);
        pnlDetails.add(lblFine);
        pnlDetails.add(lbla);
        pnlDetails.add(txtAmount);
        
        btnPay = new Button("PAY");
        btnPay.setBackground(new Color(134,83,55));
        btnPay.setForeground(Color.WHITE);
        btnPay.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnPay.setBounds(720,800,500,50);
        
        pnlDetails.add(btnPay);
        
        btnPay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean isAmountValid = false;
                amount = 0.0;
                charge = Double.parseDouble(fine);
                
                if (txtAmount.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the amount paid.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                    txtAmount.requestFocusInWindow();
                    
                }else{
                    try{
                        amount = Double.parseDouble(txtAmount.getText());
                        isAmountValid = true;

                    }catch(NumberFormatException ex){
                        isAmountValid = false;
                    }

                    if (isAmountValid == true){
                        if (amount < charge){
                            JOptionPane.showMessageDialog(null, "Amount paid is lesser than fine.", "ERROR", JOptionPane.ERROR_MESSAGE);

                            txtAmount.setText("");
                            txtAmount.requestFocusInWindow();
                        }else{
                            change = amount - charge;
                            
                            updateFineStatus();
                            payFine();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Please enter a valid amount.", "ERROR", JOptionPane.ERROR_MESSAGE);

                        txtAmount.setText("");
                        txtAmount.requestFocusInWindow();
                    }
                }
            }
        });
    }
    
    private void getFineDetails(){
        File inputFile = new File("Borrow.txt");
        
        try{
            Scanner input = new Scanner(inputFile);
            
            while(input.hasNextLine()){
                String data = input.nextLine();
                
                String[] details = data.split(":");
                String dbId = details[0];

                if (dbId.equals(borrowId)){
                    bookName = details[1];
                    clientName = details[2];
                    borrowDate = details[3];
                    returnD = details[4];
                    status = details[5];
                    renewTime = details[6];
                    extendDate = details[7];
                    fineStatus = details[8];
                    exactReturnDate = details[9];
                    
                    oldInfo = borrowId + ":" + bookName + ":" + clientName + ":" + borrowDate + ":" + returnD + ":" + 
                              status + ":" + renewTime + ":" + extendDate + ":" + fineStatus + ":" + exactReturnDate;
                    
                    int rTime = Integer.parseInt(renewTime);
                    
                    if (rTime == 1){
                        returnDate = details[7];
                    }else{
                        returnDate = details[4];
                    }
                }
            }
            
            input.close();

        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateFineStatus(){
        fineStatus = "Paid";
        
        String newInfo = borrowId + ":" + bookName + ":" + clientName + ":" + borrowDate + ":" + returnD + ":" + 
                         status + ":" + renewTime + ":" + extendDate + ":" + fineStatus + ":" + exactReturnDate;
        
        //Link: https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/
        //Date Accessed: 5 March 2021
        File modifyFile = new File("Borrow.txt");
        
        String oldDetails = "";
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(modifyFile));
            
            //Read all the lines of the text file
            String line = reader.readLine();
            
            while (line != null){
                oldDetails = oldDetails + line + System.lineSeparator();
                
                line = reader.readLine();
            }
            
            //Replace oldString with newString in newDetails
            String newDetails = oldDetails.replaceAll(oldInfo, newInfo);
            
            FileWriter writer = new FileWriter(modifyFile);
            
            writer.write(newDetails);
            
            //Close the resources
            reader.close();
            
            writer.close();
            
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void payFine(){
        try{
            FileWriter fw = new FileWriter("Fine.txt", true);
            PrintWriter outputFile = new PrintWriter(fw);
                                            
            Register r = new Register("Fine.txt");
            String FineId = r.getId();
            
            //Link: https://mkyong.com/java/java-display-double-in-2-decimal-points/
            //Date Accessed: 8 March 2021
            DecimalFormat df = new DecimalFormat("#.##");
            String fFine = df.format(charge);
            String fAmount = df.format(amount);
            String fChange = df.format(change);

            String fineInfo = FineId + ":" + borrowId + ":" + returnDate + ":RM" + fFine + ":RM" + fAmount + ":RM" + 
                              fChange + ":" + today;
            outputFile.println(fineInfo);

            outputFile.close();

            JOptionPane.showMessageDialog(null, "The fine is paid successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            
            Label lblc = new Label("Change (RM): ");
            Label lblChange = new Label(String.format("%.2f", change));
            lblc.setFont(new Font("DialogInput", Font.BOLD, 24));
            lblChange.setFont(new Font("DialogInput", Font.BOLD, 24));
            lblc.setBounds(1050,610,280,30);
            lblChange.setBounds(1330,610,400,40);

            pnlDetails.add(lblc);
            pnlDetails.add(lblChange);
            
            btnPay.setVisible(false);
            Button btnPayOther = new Button("PAY ANOTHER FINE");
            btnPayOther.setBackground(new Color(134,83,55));
            btnPayOther.setForeground(Color.WHITE);
            btnPayOther.setFont(new Font("Monospaced", Font.BOLD, 26));
            btnPayOther.setBounds(720,800,500,50);

            pnlDetails.add(btnPayOther);
            
            btnPayOther.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    setVisible(false);

                    Fine f = new Fine();
                    f.setVisible(true);
                }
            });
  
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "There is an error during input and output operation. Please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
