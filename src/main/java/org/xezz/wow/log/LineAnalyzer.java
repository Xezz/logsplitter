package org.xezz.wow.log;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * User: Xezz
 * Date: 06.11.13
 * Time: 17:52
 */
public class LineAnalyzer {
    private static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss.SSS";
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(DATE_PATTERN);

    /**
     * Parse a logfile line for a date
     *
     * @param logLine the line to analyze
     * @param year    the year the log happened
     * @return DateTime representing the time at which the line got logged
     */
    public static DateTime getTime(String logLine, int year) {
        /*
            Format of a Date string is:
            Month/Day hour:minute:second.millisecond
            prepending the given year as such:
            Y/M/d H:m:s.S
         */
        final String[] dateString = logLine.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(year).append("/").append(dateString[0]).append(" ").append(dateString[1]);

        return FORMATTER.parseDateTime(sb.toString());
    }
}
