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
public class PhoneValidation {
    private String phone;
    private boolean match;
    
    public PhoneValidation(String p){
        phone = p;
        
        //Link: https://www.javaprogramto.com/2020/04/java-phone-number-validation.html
        //Date Accessed: 4 March 2021
        final String regex = "\\d{10}";
        
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(phone);
        
        match = matcher.matches();
    }
    
    public boolean getPhoneValidation(){
        return match;
    }
}
