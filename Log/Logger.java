package Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Logger {
    private static String[] levels = {"DEBUG", "INFO", "WARNING", "ERROR", "CRITICAL"};
    private static Logger INSTANCE;
    private static String logFilePath;
    private static final DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'Time:' hh:mm:ss");


    private Logger(String logFilePath) {
        Logger.logFilePath = logFilePath;
    }

    public static Logger getInstance(String logFilePath) {
        Path path = Paths.get(logFilePath);
        if (!Files.exists(path)) {
            System.out.println("Log file does not exist, creating a new one.");
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.err.println("Error creating log file: " + e.getMessage());
                return null;
            }
        }

        if (INSTANCE == null) {
            INSTANCE = new Logger(logFilePath);
        }
        return INSTANCE;
    }

    public void log(String message, int logLevel) {
        if (message.isEmpty()) {
            throw new IllegalArgumentException ("Empty log message");
        }
        else if (logLevel >= levels.length) {
            throw new IllegalArgumentException ("Invalid log level value supported values: 0:DEBUG - 1:INFO - 2:WARNING - 3:ERROR - 4:CIRTICAL");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.append(
                String.format("[ %s ] - [ %s ] - [ %s ]\n", getCurrentDateTime(), levels[logLevel], message)
            );
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentDateTime() {
        return LocalDateTime.now().format(fomatter);
    }
}
