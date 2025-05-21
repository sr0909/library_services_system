/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.util.regex.*;
/**
 *
 * @author Dell Vostro
 */
public class EmailValidation {
    private String email;
    private boolean match;
    
    public EmailValidation(String e){
        email = e;
        
        //Link: https://blog.mailtrap.io/java-email-validation/
        //Link: https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
        //Date Accessed: 4 March 2021
        final String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(email);
        
        //Link: http://tutorials.jenkov.com/java-regex/matcher.html
        //Date Accessed: 4 March 2021
        match = matcher.matches();
    }
    
    public boolean getEmailValidation(){
        return match;
    }
}
