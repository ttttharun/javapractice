package com;

import com.javapractice.MoodAnalyzer;
import com.javapractice.exception.MoodAnalyzerException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class MoodAnalyzerTest {
    @Test
    public void givenMessageWhenSadShouldReturnSad() {
        MoodAnalyzer analyzer = new MoodAnalyzer("This is sad");
        String mood = null;
        try {
            mood = analyzer.analyzeMood();
        } catch (MoodAnalyzerException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("sad", mood);
    }

    @Test
    public void givenMessageWhenNotSadShouldReturnHappy() throws MoodAnalyzerException {
        MoodAnalyzer analyzer = new MoodAnalyzer("I'm happy");
        String mood = analyzer.analyzeMood();
        Assert.assertEquals("happy", mood);
    }

    @Test
    public void givenNullMessageShouldReturnHappy() {
        MoodAnalyzerException ex = assertThrows(
                MoodAnalyzerException.class,
                () -> new MoodAnalyzer().analyzeMood(null)
        );

        Assert.assertEquals("Invalid mood.", ex.getMessage());
    }
}
