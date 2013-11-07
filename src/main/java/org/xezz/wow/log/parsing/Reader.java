package org.xezz.wow.log.parsing;

import java.io.IOException;

/**
 * User: Xezz
 * Date: 07.11.13
 * Time: 18:35
 */
public interface Reader {
    /**
     * Reads a stream and parses its output
     *
     * @throws IOException if the source file does not exist or the target file already exists
     */
    void parse() throws IOException;
}
