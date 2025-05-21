/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.assignment_library.service.system;
/**
 *
 * @author Dell Vostro
 */
public abstract class ExceptionHandling extends Exception{
    ExceptionHandling(String msg){
        super(msg);
    }
}

class noGenderException extends ExceptionHandling{
    public noGenderException(){
        super("Please select your gender.");
    }
}

class noAllDetailsException extends ExceptionHandling{
    public noAllDetailsException(){
        super("Please fill in all the details to register.");
    }
}

class passNotMatchException extends ExceptionHandling{
    public passNotMatchException(){
        super("Password and confirm password is not match to each other.");
    }
}

class emailNotValidException extends ExceptionHandling{
    public emailNotValidException(){
        super("Invalid email format.");
    }
}

class nameNotValidException extends ExceptionHandling{
    public nameNotValidException(){
        super("Invalid name.");
    }
}

class nameExistsException extends ExceptionHandling{
    public nameExistsException(){
        super("This person had been registered.");
    }
}

class usernameNotValidException extends ExceptionHandling{
    public usernameNotValidException(){
        super("This username had been used by another librarian. Please try again.");
    }
}

class phoneNotValidException extends ExceptionHandling{
    public phoneNotValidException(){
        super("Invalid phone format. Valid format: 0123456789");
    }
}

class dateNotValidException extends ExceptionHandling{
    public dateNotValidException(){
        super("Invalid date format. Valid format: dd-MM-yyyy");
    }
}

class intakeNotValidException extends ExceptionHandling{
    public intakeNotValidException(){
        super("Invalid intake code format as it contains symbols. Valid format: UCDF1905ICTSE.");
    }
}
