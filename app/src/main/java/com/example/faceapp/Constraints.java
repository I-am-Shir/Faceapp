package com.example.faceapp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Constraints {
    private boolean checkRegex(String regex, String toCheck){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toCheck);
        return matcher.find();
    }
    boolean usernameCheck(String username) throws IllegalArgumentException {
        if (!checkRegex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", username)){
            throw new IllegalArgumentException("Invalid email address");
        }
        //TODO: add a check for the email to be unique
        return true;
    }
    boolean passwordCheck(String password) throws Exception {
        if (password == null)
            throw new Exception("must input a password");
        if (password.length()<8||password.length()>20)
            throw new Exception("password must contain 8-20 characters.");
        if (!checkRegex("^([a-zA-Z0-9]*(([a-zA-Z][0-9])|([0-9][a-zA-Z]))[a-zA-Z0-9]*)$",password))
            throw new Exception("password must contain both letters and numbers.");
        return true;
    }

    boolean firstNameCheck(String firstName) throws Exception {
        if (firstName == null)
            throw new Exception("must input a first name");
        if (!checkRegex("^[a-zA-Z]+$", firstName))
            throw new Exception("first name must contain letters only");
        return true;
    }

    boolean lastNameCheck(String lastName) throws Exception {
        if (lastName == null)
            throw new Exception("must input a last name");
        if (!checkRegex("^[a-zA-Z]+$", lastName))
            throw new Exception("last name must contain letters only");
        return true;
    }
}
