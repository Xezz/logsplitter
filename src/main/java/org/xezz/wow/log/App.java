package org.xezz.wow.log;

import org.joda.time.DateTime;

/**
 * User: Xezz
 * Date: 06.11.13
 * Time: 17:52
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String logLine = "10/31 21:45:58.405  SPELL_CAST_SUCCESS,0x00000000000CD68A,\"Shockhappens\",0x512,0x0000000000000000,nil,0x80000000,25533,\"Searing Totem\",0x4";
        DateTime dateTime = LineAnalyzer.getTime(logLine, 2013);
        System.out.println(dateTime.toString());
        System.out.println("----- -----");
        logLine = "11/1 21:31:04.296  SPELL_SUMMON,0x00000000000D3B29,\"Vuvusha\",0x512,0x00000000000D3B29,\"Vuvusha\",0x512,46546,\"Ritual of Summoning\",0x20";
        dateTime = LineAnalyzer.getTime(logLine, 2013);
        System.out.println(dateTime.toString());

    }
}
