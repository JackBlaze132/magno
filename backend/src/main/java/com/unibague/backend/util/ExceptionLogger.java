package com.unibague.backend.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionLogger {

    private static final String LOG_FOLDER = "./logs";


    static {
        File logDirectory = new File(LOG_FOLDER);
        if (!logDirectory.exists()) {
            logDirectory.mkdirs();
        }
    }

    public static void logException(Exception exception) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String logFileName = LOG_FOLDER + "/log_" + timestamp + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileName))) {
            writer.write("Exception occurred at: " + LocalDateTime.now());
            writer.newLine();
            writer.write("Exception message: " + exception.getMessage());
            writer.newLine();
            writer.write("Stack trace:");
            writer.newLine();
            for (StackTraceElement element : exception.getStackTrace()) {
                writer.write("\tat " + element.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing log file: " + e.getMessage());
        }
    }
}
