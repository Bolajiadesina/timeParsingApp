package com.technicalTest.timeParsingApp.implementation;

public interface TimeParserInterface {

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


    public String parseTimeSeconds(String valueString); 
    public String parseMinuteSeconds(String valueString); 
    public String parseHourSeconds(String valueString); 
    public String parseDaySeconds(String valueString); 
    public String parseMonthSeconds(String valueString); 
    public String parseYearSeconds(String valueString); 
    public String parseTime(String valueString); 
    
    

    
} 