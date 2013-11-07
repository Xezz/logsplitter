package org.xezz.wow.log;

import org.joda.time.DateTime;
import org.xezz.wow.log.parsing.Writer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Write a partial WoW Combatlog to disc
 * User: Xezz
 * Date: 06.11.13
 * Time: 22:58
 */
public class LogWriter implements Writer {
    private final String targetDirectory;
    private BufferedWriter writer = null;
    private final String lineSep = System.getProperty("line.separator");

    public LogWriter(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    @Override
    public void write(String line) {
        try {
            writer.write(line);
            writer.write(lineSep);
        } catch (IOException e) {
            e.printStackTrace();  // FIXME: Handle this exception
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();  // FIXME: Handle this exception
        }
    }

    @Override
    public void startNew(DateTime dateTime) throws IOException {
        final int year = dateTime.getYear();
        final int monthOfYear = dateTime.getMonthOfYear();
        final int dayOfMonth = dateTime.getDayOfMonth();
        final int hourOfDay = dateTime.getHourOfDay();
        final int minuteOfHour = dateTime.getMinuteOfHour();
        final int secondOfMinute = dateTime.getSecondOfMinute();
        // Create the filename from the date
        StringBuilder builder = new StringBuilder();
        builder.append(year).append("-").append(String.format("%02d", monthOfYear));
        builder.append("-").append(String.format("%02d", dayOfMonth)).append("-").append(String.format("%02d", hourOfDay));
        builder.append("-").append(String.format("%02d", minuteOfHour)).append("-").append(String.format("%02d", secondOfMinute)).append(".txt");
        System.out.println(builder.toString());
        final Path logfile = Paths.get(targetDirectory, builder.toString());
        OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(logfile, StandardOpenOption.CREATE_NEW));
        writer = new BufferedWriter(new OutputStreamWriter(outputStream));
    }
}
