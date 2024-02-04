package com.technicalTest.timeParsingApp.implementation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;

public class TimeParserInterfaceImplementation implements TimeParserInterface{


     // Get the current time
            Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

            @Override
            public String parseTimeSeconds(String valueString) {
                 // Match the input against the pattern
    //    // Matcher matcher = pattern.matcher(input);
    //             char sign = matcher.group(1).charAt(0);
    //         int value = Integer.parseInt(matcher.group(2));

               // now.add(Calendar.SECOND, (sign == '-' ? -1 : 1) * value);
                // Format the modified time in the desired format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            return dateFormat.format(now.getTime());
            }

            @Override
            public String parseMinuteSeconds(String valueString) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'parseMinuteSeconds'");
            }

            @Override
            public String parseHourSeconds(String valueString) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'parseHourSeconds'");
            }

            @Override
            public String parseDaySeconds(String valueString) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'parseDaySeconds'");
            }

            @Override
            public String parseMonthSeconds(String valueString) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'parseMonthSeconds'");
            }

            @Override
            public String parseYearSeconds(String valueString) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'parseYearSeconds'");
            }

            @Override
            public String parseTime(String valueString) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'parseTime'");
            }


    //     case 's':
//     now.add(Calendar.SECOND, (sign == '-' ? -1 : 1) * value);
//     break;
// case 'm':
//     now.add(Calendar.MINUTE, (sign == '-' ? -1 : 1) * value);
//     break;
// case 'h':
//     now.add(Calendar.HOUR_OF_DAY, (sign == '-' ? -1 : 1) * value);
//     break;
// case 'd':
//     now.add(Calendar.DAY_OF_MONTH, (sign == '-' ? -1 : 1) * value);
//     break;
// case 'k':
//  now.add(Calendar.MONTH, (sign == '-' ? -1 : 1) * value);
//     break;
// case 'y':
//     now.add(Calendar.YEAR, (sign == '-' ? -1 : 1) * value);
//     break;
}
