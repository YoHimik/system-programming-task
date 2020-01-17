package com.stockmarket.core;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger {
    private static final String LOG_FILE_NAME = "log.txt";

    private Logger() {
    }

    public static void toLog(String txt, boolean toConsole) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String out = dateFormat.format(date) + " " + txt;
        if (toConsole) {
            System.out.println(txt);
            out += " (from console)";
        }
        try (FileWriter writer = new FileWriter(LOG_FILE_NAME, true)) {
            writer.write(out);
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void toLog(String txt) {
        toLog(txt, true);
    }
}
