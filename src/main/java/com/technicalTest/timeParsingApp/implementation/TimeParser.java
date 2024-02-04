package com.technicalTest.timeParsingApp.implementation;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeParser {
    public static String parseRelativeTime(String input) {
        // Get the current time
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        if(input.equals("now()"))
        {
                return now.getTime().toString();
        }


        // Regular expression to match relative time expressions
        Pattern pattern = Pattern.compile("([-+])(\\d+)([smhdkyn])");

        // Match the input against the pattern
        Matcher matcher = pattern.matcher(input);

        // If there is a match, extract components
        if (matcher.find()) {
            char sign = matcher.group(1).charAt(0);
            int value = Integer.parseInt(matcher.group(2));
            char unit = matcher.group(3).charAt(0);

            

            if (input.contains("mon")) {
                unit = 'k';
            }

            // Modify time based on the relative components
            switch (unit) {
                case 'n':
                    now.getTime();

                    break;
                case 's':
                    now.add(Calendar.SECOND, (sign == '-' ? -1 : 1) * value);
                    break;
                case 'm':
                    now.add(Calendar.MINUTE, (sign == '-' ? -1 : 1) * value);
                    break;
                case 'h':
                    now.add(Calendar.HOUR_OF_DAY, (sign == '-' ? -1 : 1) * value);
                    break;
                case 'd':
                    now.add(Calendar.DAY_OF_MONTH, (sign == '-' ? -1 : 1) * value);
                    break;
                case 'k':
                    now.add(Calendar.MONTH, (sign == '-' ? -1 : 1) * value);
                    break;
                case 'y':
                    now.add(Calendar.YEAR, (sign == '-' ? -1 : 1) * value);
                    break;
            }
            // Format the modified time in the desired format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            return dateFormat.format(now.getTime());

        }

        // Return null if no match is found
        return null;
    }

  

    public static String[] parseTimeExpression(String input) {
        // Regular expression to match time expression components
        Pattern pattern = Pattern.compile("([+-]?\\d+)([smhd])");

        // Match the input against the pattern
        Matcher matcher = pattern.matcher(input);

        // List to store matched components
        StringBuilder result = new StringBuilder();

        // Find all matches
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            String unit = matcher.group(2);

            // Append the matched components to the result
            result.append(value).append(unit).append(",");
        }

        // Convert the result to a String array
        String[] components = result.toString().split(",");

        for (String component : components) {
            System.out.println(component);
        }

        return components;
    }

    public String executeParser(String textString) {

        String[] analysedInput = parseTimeExpression(textString);
        int count = analysedInput.length;

        if (count < 2) {
            return parseRelativeTime(analysedInput[0]);
        } else {
            String newDate = "";
            for (String targetDateTime : analysedInput) {
                newDate = parseRelativeTime(targetDateTime);

            }

            return newDate;

        }

    }

    public static void main(String[] args) {
        TimeParser tm = new TimeParser();

        System.out.println(tm.parseTimeExpression("now()+10d+12h"));
    }

}
