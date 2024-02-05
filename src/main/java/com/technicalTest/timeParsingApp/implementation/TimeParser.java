package com.technicalTest.timeParsingApp.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;

public class TimeParser {
    Logger logger = Logger.getLogger(getClass().getName());

    public static String parseRelativeTime(String input, String baseDateValue) {
        // Get the current time
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        String datePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

        if (input == "now()") {
            return dateFormat.format(now.getTime());
        }
        if (input.contains("@d")) {

            LocalDateTime localDateTime = LocalDateTime.now();

            LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN).withSecond(0);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

            return startOfDay.format(formatter);

        }

       

        // Regular expression to match relative time expressions
        Pattern pattern = Pattern.compile("([-+])(\\d+)([smhdkyn])");

        // Match the input against the pattern
        Matcher matcher = pattern.matcher(input);

        // If there is a match, extract components
        if (matcher.find() && baseDateValue == null) {
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

            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            return dateFormat.format(now.getTime());

        } else if (baseDateValue != null) {

            Calendar now2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

            Pattern pattern2 = Pattern.compile("([-+])(\\d+)([smhdkyn])");

            Matcher matcher2 = pattern2.matcher(input);

            try {
                now2.setTime(dateFormat.parse(baseDateValue));
            } catch (ParseException e) {

                e.printStackTrace();
            }

            while (matcher2.find()) {

                char sign = matcher.group(1).charAt(0);
                int value = Integer.parseInt(matcher.group(2));
                char unit = matcher.group(3).charAt(0);

                switch (unit) {

                    case 'n':
                        now2.getTime();

                        break;
                    case 's':
                        now2.add(Calendar.SECOND, (sign == '-' ? -1 : 1) * value);
                        break;
                    case 'm':
                        now2.add(Calendar.MINUTE, (sign == '-' ? -1 : 1) * value);
                        break;
                    case 'h':
                        now2.add(Calendar.HOUR_OF_DAY, (sign == '-' ? -1 : 1) * value);
                        break;
                    case 'd':
                        now2.add(Calendar.DAY_OF_MONTH, (sign == '-' ? -1 : 1) * value);
                        break;
                    case 'k':
                        now2.add(Calendar.MONTH, (sign == '-' ? -1 : 1) * value);
                        break;
                    case 'y':
                        now2.add(Calendar.YEAR, (sign == '-' ? -1 : 1) * value);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported unit");
                }
            }

            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            return dateFormat.format(now2.getTime());

        }

        // Return no match is found
        return "No match found";
    }

    public static String [] parseTimeExpression(String input) {
        // Regular expression to match time expression components
        Pattern pattern = Pattern.compile("([+-]?\\d+)([smhd])");

        // Match the input against the pattern
        Matcher matcher = pattern.matcher(input);

        // List to store matched components
        StringBuilder result = new StringBuilder();

        // Find all matches
        while (matcher.find()) {
        String value = matcher.group(1) != null ? matcher.group(1) :
        matcher.group(3);
        String unit = matcher.group(2);

        // Include the sign along with the value
        result.append(matcher.group()).append(",");

        }

        // Convert the result to a String array
        return result.toString().split(",");

    }

    public String[] parseTimeExpressionold(String input) {
        String regex = "(?=[-+@])";

        return input.split(regex);

    }

    public String executeParser(String textString) {

        //String checkString= textString.substring(5);

        String[] analysedInput = parseTimeExpression(textString);
        int count = analysedInput.length;

        String newDate = null;
        String modifiedDate = null;

        if (count < 2) {
            return parseRelativeTime(textString, null);
        } else {

            for (String targetDateTime : analysedInput) {

                newDate = parseRelativeTime(targetDateTime, newDate);
                modifiedDate = newDate;
            }

            return modifiedDate;

        }

    }

   

}
