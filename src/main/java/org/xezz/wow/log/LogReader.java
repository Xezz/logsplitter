package org.xezz.wow.log;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.xezz.wow.log.parsing.Reader;
import org.xezz.wow.log.parsing.Writer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Read a WoW Combatlog
 * User: Xezz
 * Date: 06.11.13
 * Time: 20:05
 */
public class LogReader implements Reader {
    private final Path logFile;
    private int parseYear = 2013;
    private Writer writer;
    /**
     * 30 Minutes of idle-time is allowed
     */
    //private Minutes maxIdleTime = Minutes.minutes(30);
    private int maxIdleTime = 30;

    public LogReader(Path sourceFile, Writer writer) {
        this.logFile = sourceFile;
        this.writer = writer;
    }

    @Override
    public void parse() throws IOException {
        final InputStream inputStream = Files.newInputStream(logFile, StandardOpenOption.READ);
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String readLine;
        DateTime lastLineDate = null;
        while ((readLine = br.readLine()) != null) {
            // Get the time of the current line
            DateTime currentLineDate = LineAnalyzer.getTime(readLine, parseYear);
            // this is the first line we have read, so start with a new line!
            if (lastLineDate == null) {
                lastLineDate = currentLineDate;
                writer.startNew(currentLineDate);
            }
            // If we idle too much we want to create a new file!
            if ((new Period(lastLineDate, currentLineDate, PeriodType.minutes())).getMinutes() > maxIdleTime) {
                writer.close();
                writer.startNew(currentLineDate);
            }
            writer.write(readLine);
            lastLineDate = currentLineDate;
        }
        writer.close();
        br.close();
    }

    public Path getLogFile() {
        return logFile;
    }

    public int getMaxIdleTime() {
        return maxIdleTime;
    }

    public void setMaxIdleTime(int maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    public int getParseYear() {
        return parseYear;
    }

    public void setParseYear(int parseYear) {
        this.parseYear = parseYear;
    }
}
