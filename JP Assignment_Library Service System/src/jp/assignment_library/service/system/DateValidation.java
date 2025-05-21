/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
/**
 *
 * @author Dell Vostro
 */
public class DateValidation {
    private String date;
    private boolean valid = true;
    
    public DateValidation(String d){
        date = d;
        
        //Link: https://beginnersbook.com/2013/05/java-date-format-validation/
        //Date Accessed: 5 March 2021
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        
        try{
            dateFormat.parse(date);
            
        }catch(ParseException ex){
            valid = false;
        }
    }
    
    public boolean getDateValidation(){
        return valid;
    }
}
