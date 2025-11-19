package com;

import com.javapractice.UserValidator;
import com.javapractice.exception.UserValidatorException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;

@FunctionalInterface
interface UserValidation {
    boolean validate(String input) throws UserValidatorException;
}

public class UserValidatorTest {

    UserValidator userValidator = new UserValidator();
    UserValidation validateFirstName = userValidator::validateFirstName;
    UserValidation validateLastName = userValidator::validateLastName;
    UserValidation validateEmail = userValidator::validateEmail;
    UserValidation validateMobileNumber = userValidator::validateMobileNumber;
    UserValidation validatePassword = userValidator::validatePassword;

    @Test
    public void givenFirstNameWhenProperShouldReturnTrue() throws UserValidatorException {
        boolean result = userValidator.validateFirstName("Abhisheak");
        assertTrue(result);
    }

    @Test
    public void givenEmailWhenValidShouldReturnTrue() throws UserValidatorException {
        boolean result = userValidator.validateEmail("abhisheak@gmail.com");
        assertTrue(result);
    }

    @Test
    public void givenLastNameWhenProperShouldReturnTrue() throws UserValidatorException {
        boolean result = userValidator.validateLastName("BS");
        assertTrue(result);
    }

    @Test
    public void givenMobileNumberWhenProperShouldReturnTrue() throws UserValidatorException {
        boolean result = userValidator.validateMobileNumber("91 1234567890");
        assertTrue(result);
    }

    @Test
    public void givenPasswordWhenProperShouldReturnTrue() throws UserValidatorException {
        boolean result = userValidator.validatePassword("A@bhisheak2");
        assertTrue(result);
    }

    @Test
    public void givenWrongFirstNameShouldThrowException() throws UserValidatorException {
        UserValidatorException e = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateFirstName("abi")
        );
        assertEquals("Invalid first name format.", e.getMessage());
    }

    @Test
    public void givenWrongLastNameShouldThrowException() throws UserValidatorException {
        UserValidatorException e = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateLastName("abi")
        );
        assertEquals("Invalid last name format.", e.getMessage());
    }

    @Test
    public void givenWrongEmailShouldThrowException() throws UserValidatorException {
        UserValidatorException e = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateEmail("abi")
        );
        assertEquals("Invalid email format.", e.getMessage());
    }

    @Test
    public void givenWrongMobileNumberShouldThrowException() throws UserValidatorException {
        UserValidatorException e = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateMobileNumber("32125632")
        );
        assertEquals("Invalid mobile number format.", e.getMessage());
    }

    @Test
    public void givenWrongPasswordShouldThrowException() {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validatePassword("haha,boom")
        );
        assertEquals("Invalid password format.", ex.getMessage());
    }

    @Test
    public void givenNullFirstNameShouldThrowException() {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateFirstName(null)
        );
        assertEquals("Cannot be null or empty.", ex.getMessage());
    }

    @Test
    public void givenNullLastNameShouldThrowException() {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateLastName(null)
        );
        assertEquals("Cannot be null or empty.", ex.getMessage());
    }

    @Test
    public void givenNullEmailShouldThrowException() {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateEmail(null)
        );
        assertEquals("Cannot be null or empty.", ex.getMessage());
    }

    @Test
    public void givenNullMobileNumberShouldThrowException() {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateMobileNumber(null)
        );
        assertEquals("Cannot be null or empty.", ex.getMessage());
    }

    @Test
    public void givenNullPasswordShouldThrowException() {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validatePassword(null)
        );
        assertEquals("Cannot be null or empty.", ex.getMessage());
    }

    // ===================== FIRST NAME =========================

    @ParameterizedTest
    @CsvSource({
            "Abhishek",
            "John",
            "Aaa",
            "Mark",
            "David"
    })
    public void testValidFirstNames(String firstName) throws UserValidatorException {

        UserValidator validator = new UserValidator();

        boolean result = validator.validateFirstName(firstName);

        Assertions.assertTrue(result);
    }


    @ParameterizedTest
    @ValueSource(strings = { "abi", "ab", "a", "123", "@bi", "abI" })
    public void givenInvalidFirstNamesShouldThrowException(String name) {


        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateFirstName(name)
        );

        assertEquals("Invalid first name format.", ex.getMessage());
    }

    // ===================== LAST NAME =========================

    @ParameterizedTest
    @ValueSource(strings = {"K", "Prabu", "D", "Singh"})
    public void validLastNames(String name) throws Exception {
        assertTrue(userValidator.validateLastName(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"k", "prabu", "pRabu", "123", "@k"})
    public void invalidLastNames(String name) {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateLastName(name)
        );
        assertEquals("Invalid last name format.", ex.getMessage());
    }

    // ===================== EMAIL =========================

    @ParameterizedTest
    @ValueSource(strings = {
            "abc@gmail.com",
            "john.doe@yahoo.com",
            "test123@domain.in",
            "user_name@company.org"
    })
    public void validEmails(String email) throws Exception {
        assertTrue(userValidator.validateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abcgmail.com",
            "abc@gmail",
            "@gmail.com",
            "abc@.com",
            "abc@domain.c"
    })
    public void invalidEmails(String email) {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateEmail(email)
        );
        assertEquals("Invalid email format.", ex.getMessage());
    }

    // ===================== MOBILE NUMBER =========================

    @ParameterizedTest
    @ValueSource(strings = {
            "91 9876543210",
            "91 1234567890"
    })
    public void validMobileNumbers(String mobile) throws Exception {
        assertTrue(userValidator.validateMobileNumber(mobile));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "919876543210",
            "91 987654321",   // 9 digits
            "98 9876543210",  // wrong country code
            "91-9876543210"  // wrong separator
    })
    public void invalidMobileNumbers(String mobile) {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validateMobileNumber(mobile)
        );
        assertEquals("Invalid mobile number format.", ex.getMessage());
    }

    // ===================== PASSWORD =========================

    // ✔ VALID passwords (assertTrue)
    @ParameterizedTest
    @ValueSource(strings = {
            "Abcd@123",
            "Xyz#7890",
            "Aa1!aaaa",
            "StrongP@55"
    })
    public void validPasswords(String password) throws Exception {
        assertTrue(userValidator.validatePassword(password));
    }

    // ❌ INVALID passwords (regex fail → exception)
    @ParameterizedTest
    @ValueSource(strings = {
            "abcd1234",     // no caps, no special
            "ABCD1234",     // no special
            "Abcd@12",      // < 8 chars
            "abcd@123",     // no caps
            "ABCD@ABC"     // no numbers
    })
    public void invalidPasswords(String password) {
        UserValidatorException ex = assertThrows(
                UserValidatorException.class,
                () -> userValidator.validatePassword(password)
        );
        assertEquals("Invalid password format.", ex.getMessage());
    }

    @Test
    public void validFirstNameUsingLambdaFunctionShouldReturnTrue() throws UserValidatorException {
        Assert.assertTrue(validateFirstName.validate("Abhisheak"));
    }

    @Test
    public void validLastNameUsingLambdaFunctionShouldReturnTrue() throws UserValidatorException {
        Assert.assertTrue(validateLastName.validate("Abhisheak"));
    }

    @Test
    public void validEmailUsingLambdaFunctionShouldReturnTrue() throws UserValidatorException {
        Assert.assertTrue(validateEmail.validate("abish.eak@gmail.com"));
    }

    @Test
    public void validMobileNumberUsingLambdaFunctionShouldReturnTrue() throws UserValidatorException {
        Assert.assertTrue(validateMobileNumber.validate("91 1234567890"));
    }

    @Test
    public void validPasswordUsingLambdaFunctionShouldReturnTrue() throws UserValidatorException {
        Assert.assertTrue(validatePassword.validate("Abhisheak@2"));
    }
}
