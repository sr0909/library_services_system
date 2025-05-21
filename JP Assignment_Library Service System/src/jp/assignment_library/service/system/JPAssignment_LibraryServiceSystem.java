/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import javax.swing.*;
import java.io.*;

/**
 *
 * @author Dell Vostro
 */
public class JPAssignment_LibraryServiceSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        try {
            Login loginPage = new Login();
            loginPage.setVisible(true);
                    
        }catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Database not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
