package org.xezz.wow.log.parsing;

import org.joda.time.DateTime;

import java.io.IOException;

/**
 * User: Xezz
 * Date: 06.11.13
 * Time: 23:16
 */
public interface Writer {
    void write(String line);

    void close();

    void startNew(DateTime dateTime) throws IOException;
}
