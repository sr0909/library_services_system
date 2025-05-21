/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.util.regex.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Dell Vostro
 */
public class IntakeCodeValidation {
    private String intake;
    private boolean valid = true;
    
    public IntakeCodeValidation(String i){
        intake = i;
        
        //Link: https://www.tutorialspoint.com/java-regex-program-to-match-parenthesis-or
        //Date Accessed: 9 March 2021
        Pattern pattern = Pattern.compile("^.*[\\(\\)].*$");
        
        Matcher matcher = pattern.matcher(intake);
        
        valid = matcher.matches();
    }
    
    public boolean getIntakeValidation(){
        return valid;
    }
}
