/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Dell Vostro
 */
//Link: http://www2.hawaii.edu/~takebaya/ics111/inheritance/inheritance.html
//Date Accessed: 3 March 2021
public class BasicWindow extends JFrame{
    public BasicWindow(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(screenSize.width, screenSize.height);
        setResizable(false);
        
        setLocation(0,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        //Link: https://stackoverflow.com/questions/26913923/how-do-you-change-the-size-and-font-of-a-joptionpane
        //Date Accessed: 11 March 2021
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 18));
    }
}
