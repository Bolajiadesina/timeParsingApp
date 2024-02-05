package com.technicalTest.timeParsingApp.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.technicalTest.timeParsingApp.implementation.TimeParser;

public class TimeParserTest {

    private TimeParser timeParser;

    @BeforeEach
    void setup() {
        timeParser = new TimeParser();
    }


    @Test
    void checkIfNowWorks() {
        // Given
        String dateValue = "+now()";
        // When
        String isValid = timeParser.executeParser(dateValue);

        // Then
        assertEquals("2024-02-05T21:03:41Z",isValid);
    }

    @Test
    void checkIfDayIcreamentWorks() {
        // Given
        String dateValue = "+now()+10d";
        // When
        String isValid = timeParser.executeParser(dateValue);

        // Then
        assertEquals(isValid,"2024-02-15T20:54:36Z");
    }

    @Test
    void checkIfDayIcreamentDoesNotWork() {
        // Given
        String dateValue = "+now()-10d";
        // When
        String isValid = timeParser.executeParser(dateValue);

        // Then
        assertEquals(isValid,"2024-01-26T20:55:51Z");
    }
    @Test
    void checkIfHourIncreamentWorks() {
        // Given
        String dateValue = "now()+10h";
        // When
        String isValid = timeParser.executeParser(dateValue);

        // Then
        assertEquals(isValid,"2024-02-06T06:59:04Z");
    }

    @Test
    void checkIfHourDecreamentDoesNotWorks() {
        // Given
        String dateValue = "now()-12h";
        // When
        String isValid = timeParser.executeParser(dateValue);

        // Then
        assertEquals(isValid,"2024-02-05T09:00:18Z");
    }

    @Test
    void checkIfsnappedDayWorks() {
        // Given
        String dateValue = "now()@d";
        // When
        String isValid = timeParser.executeParser(dateValue);

        // Then
        assertEquals(isValid,"2024-02-05T00:00:00Z");
    }


    @Test
    void checkSplitArrayWorks() {
        // Given
        String dateValue = "now()+10d+12h+30m+10s";
        // When
        String [] isValid = timeParser.parseTimeExpression(dateValue);

        // Then
        assertEquals("[+10d,12h,30m,10s]","[+10d,12h,30m,10s]");
    }
}
