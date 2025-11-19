package com.javapractice;

import com.javapractice.exception.UserValidatorException;

import java.util.regex.Pattern;

public class UserValidator {

    private static final String FIRST_NAME_PATTERN = "^[A-Z][a-z]{2,}$";
    private static final String LAST_NAME_PATTERN = "^[A-Z][a-zA-Z]{0,}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]{3,}@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
    private static final String MOBILE_PATTERN = "^91 [0-9]{10}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$";

    public boolean validateFirstName(String firstName) throws UserValidatorException {
        if (firstName == null || firstName.trim().isEmpty())
            throw new UserValidatorException("Cannot be null or empty.");

        Pattern pattern = Pattern.compile(FIRST_NAME_PATTERN);
        if (!pattern.matcher(firstName).matches()) {
            throw new UserValidatorException("Invalid first name format.");
        }
        return true;
    }

    public boolean validateLastName(String lastName) throws UserValidatorException{
        if (lastName == null || lastName.trim().isEmpty())
            throw new UserValidatorException("Cannot be null or empty.");

        Pattern pattern = Pattern.compile(LAST_NAME_PATTERN);
        if (!pattern.matcher(lastName).matches()) {
            throw new UserValidatorException("Invalid last name format.");
        }
        return pattern.matcher(lastName).matches();
    }

    public boolean validateEmail(String email) throws UserValidatorException{
        if (email == null || email.trim().isEmpty())
            throw new UserValidatorException("Cannot be null or empty.");

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(email).matches()) {
            throw new UserValidatorException("Invalid email format.");
        }
        return pattern.matcher(email).matches();
    }

    public boolean validateMobileNumber(String mobileNumber) throws UserValidatorException{
        if (mobileNumber == null || mobileNumber.trim().isEmpty())
            throw new UserValidatorException("Cannot be null or empty.");

        Pattern pattern = Pattern.compile(MOBILE_PATTERN);
        if (!pattern.matcher(mobileNumber).matches()) {
            throw new UserValidatorException("Invalid mobile number format.");
        }
        return pattern.matcher(mobileNumber).matches();
    }

    public boolean validatePassword(String password) throws UserValidatorException{
        if (password == null || password.trim().isEmpty())
            throw new UserValidatorException("Cannot be null or empty.");

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (!pattern.matcher(password).matches()) {
            throw new UserValidatorException("Invalid password format.");
        }
        return pattern.matcher(password).matches();
    }
}
