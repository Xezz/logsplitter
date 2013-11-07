package org.xezz.wow.log;

import org.joda.time.DateTime;
import org.xezz.wow.log.parsing.Reader;
import org.xezz.wow.log.parsing.Writer;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Read a log file and split it up into several distinct ones
 * User: Xezz
 * Date: 06.11.13
 * Time: 23:07
 */
public class LogSplitter implements Writer {
    private Reader reader;
    private Writer writer;


    public LogSplitter(final Path logFile, final String targetDirectory) {
        this.reader = new LogReader(logFile, this);
        this.writer = new LogWriter(targetDirectory);
    }

    @Override
    public void write(String line) {
        writer.write(line);
    }

    @Override
    public void close() {
        writer.close();
    }

    @Override
    public void startNew(DateTime dateTime) throws IOException {
        writer.startNew(dateTime);
    }

    public void parse() throws IOException {
        reader.parse();
    }
}
