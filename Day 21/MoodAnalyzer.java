package com.javapractice;

import com.javapractice.exception.MoodAnalyzerException;

import java.util.regex.Pattern;

public class MoodAnalyzer {

    private String message;
    private static final String SAD_PATTERN = "(?i)sad";

    public MoodAnalyzer() {}

    public MoodAnalyzer(String message) {
        this.message = message;
    }

    public String analyzeMood(String message) throws MoodAnalyzerException {

        if (message == null || message.trim().isEmpty()) {
            throw new MoodAnalyzerException("Invalid mood.");
        }

        if (message.toLowerCase().contains("sad")) {
            return "sad";
        } else {
            return "happy";
        }
    }

    public String analyzeMood() throws MoodAnalyzerException {
        try {
            Pattern pattern = Pattern.compile(SAD_PATTERN);
            if (pattern.matcher(message).find())
                return "sad";
            else return "happy";
        } catch (NullPointerException e) {
            throw new MoodAnalyzerException("happy");
        }
    }
}
