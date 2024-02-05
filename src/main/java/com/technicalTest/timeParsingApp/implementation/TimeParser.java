package com.technicalTest.timeParsingApp.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeParser {
    public static String parseRelativeTime(String input, String baseDateValue) {
        // Get the current time
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        if (input.equals("now()")) {
            return now.getTime().toString();
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
            // Format the modified time in the desired format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            return dateFormat.format(now.getTime());

        } else if (baseDateValue != null) {

            Calendar now2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

            Pattern pattern2 = Pattern.compile("([-+])(\\d+)([smhdkyn])");

            Matcher matcher2 = pattern2.matcher(input);
            if (baseDateValue != null) {
                String datePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
                SimpleDateFormat dateFormat2 = new SimpleDateFormat(datePattern);

                try {
                    now2.setTime(dateFormat2.parse(baseDateValue));
                } catch (ParseException e) {

                    e.printStackTrace();
                }
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
                }
            }
            // Format the modified time in the desired format
            SimpleDateFormat dm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dm.setTimeZone(TimeZone.getTimeZone("UTC"));

            return dm.format(now2.getTime());

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
            String value = matcher.group(1) != null ? matcher.group(1) : matcher.group(3);
            String unit = matcher.group(2);

            // Include the sign along with the value
            result.append(matcher.group()).append(",");

        }

        // Convert the result to a String array
        String[] components = result.toString().split(",");

        return components;
    }

    public String executeParser(String textString) {

        String[] analysedInput = parseTimeExpression(textString);
        int count = analysedInput.length;

        String newDate = null;
        String modifiedDate = null;

        if (count < 2) {
            return parseRelativeTime(analysedInput[0], "");
        } else {

            for (String targetDateTime : analysedInput) {

                newDate = parseRelativeTime(targetDateTime, newDate);
                modifiedDate = newDate;
            }

            return modifiedDate;

        }

    }

    public static void main(String[] args) {
        TimeParser tm = new TimeParser();

        System.out.println(tm.executeParser("now()+10d+12h"));
    }

}
