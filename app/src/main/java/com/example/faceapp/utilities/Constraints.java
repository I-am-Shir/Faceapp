package com.example.faceapp.utilities;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Constraints {
    public boolean checkRegex(String regex, String toCheck){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toCheck);
        return matcher.find();
    }
    public boolean usernameCheck(String username) throws IllegalArgumentException {
        if (!checkRegex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", username)){
            throw new IllegalArgumentException("Invalid email address");
        }
        //TODO: add a check for the email to be unique
        return true;
    }
    public boolean passwordCheck(String password) throws Exception {
        if (password == null)
            throw new Exception("Must input a password");
        if (password.length()<8||password.length()>20)
            throw new Exception("Password must contain 8-20 characters.");
        if (!checkRegex("^([a-zA-Z0-9]*(([a-zA-Z][0-9])|([0-9][a-zA-Z]))[a-zA-Z0-9]*)$",password))
            throw new Exception("Password must contain both letters and numbers.");
        return true;
    }

    public Boolean passwordMatchCheck(String password, String passwordMatch) throws Exception {
        if (!password.equals(passwordMatch))
            throw new Exception("Passwords do not match");
        return true;
    }

    public boolean firstNameCheck(String firstName) throws Exception {
        if (firstName == null)
            throw new Exception("Must input a first name");
        if (!checkRegex("^[a-zA-Z]+$", firstName))
            throw new Exception("First name must contain letters only");
        return true;
    }

    public boolean lastNameCheck(String lastName) throws Exception {
        if (lastName == null)
            throw new Exception("Must input a last name");
        if (!checkRegex("^[a-zA-Z]+$", lastName))
            throw new Exception("Last name must contain letters only");
        return true;
    }

    public boolean imageCheck(ImageView image) throws Exception {
        if (image == null)
            throw new Exception("Must input an image");
        return true;
    }

    boolean postContentCheck(String postContent) throws Exception {
        if (postContent == null)
            throw new Exception("Must input a post");
        if (postContent.length() > 1000)
            throw new Exception("Post must contain less than 1000 characters");
        return true;
    }
}
