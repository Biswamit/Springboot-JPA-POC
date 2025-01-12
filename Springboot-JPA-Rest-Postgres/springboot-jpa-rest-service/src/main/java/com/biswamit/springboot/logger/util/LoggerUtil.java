package com.biswamit.springboot.logger.util;

import com.biswamit.springboot.jpa.rest.constant.Log;
import com.biswamit.springboot.jpa.rest.constant.LogEntry;
import org.slf4j.Logger;

import java.util.Optional;

public class LoggerUtil {
    public static void logDetails(LogEntry logEntry, Logger logger, Log logLevel, String logMessage,
                                  Optional<String> optionalInput, Optional<Exception> exceptionOptional) {
        String logAppendedMessage = "";
        String logPrefix = logEntry.getPrefix();
        if (!optionalInput.isPresent()) {
            logAppendedMessage = logPrefix + logMessage;
        } else {
            logAppendedMessage = logPrefix + logMessage + " for " + optionalInput.get();
        }
        switch (logLevel) {
            case TRACE:
                logger.trace(logPrefix + logAppendedMessage);
                break;
            case DEBUG:
                logger.debug(logPrefix + logAppendedMessage);
                break;
            case INFO:
                logger.info(logPrefix + logAppendedMessage);
                break;
            case WARN:
                logger.warn(logPrefix + logAppendedMessage);
                break;
            case ERROR:
                if (exceptionOptional.isPresent()) {
                    logger.error(logPrefix + "Exception : " + logAppendedMessage, exceptionOptional.get());
                } else {
                    logger.error(logPrefix + "Exception : " + logAppendedMessage);
                }
                break;
        }
    }

    public static void logDetails(LogEntry logEntry, Logger logger, Log logLevel, String logMessage,
                                  String input, Exception exception) {
        logDetails(logEntry, logger, logLevel, logMessage, Optional.of(input), Optional.of(exception));
    }

    public static void logWithAdditionalInfo(LogEntry logEntry, Logger logger, Log logLevel,
                                             String logMessage, String input) {
        logDetails(logEntry, logger, logLevel, logMessage, Optional.of(input), Optional.empty());
    }

    public static void logTrace(LogEntry logEntry, Logger logger, String logMessage) {
        logDetails(logEntry, logger, Log.TRACE, logMessage, Optional.empty(), Optional.empty());
    }

    public static void logDebug(LogEntry logEntry, Logger logger, String logMessage) {
        logDetails(logEntry, logger, Log.DEBUG, logMessage, Optional.empty(), Optional.empty());
    }

    public static void logInfo(LogEntry logEntry, Logger logger, String logMessage) {
        logDetails(logEntry, logger, Log.INFO, logMessage, Optional.empty(), Optional.empty());
    }

    public static void logWarn(LogEntry logEntry, Logger logger, String logMessage) {
        logDetails(logEntry, logger, Log.WARN, logMessage, Optional.empty(), Optional.empty());
    }

    public static void logError(LogEntry logEntry, Logger logger, String logMessage) {
        logDetails(logEntry, logger, Log.ERROR, logMessage, Optional.empty(), Optional.empty());
    }

    public static void logError(LogEntry logEntry, Logger logger, String logMessage, Exception exception) {
        logDetails(logEntry, logger, Log.ERROR, logMessage, Optional.empty(), Optional.of(exception));
    }

    public static void logErrorWithAdditionalInfo(LogEntry logEntry, Logger logger, String logMessage,
                                                  String input, Exception exception) {
        logDetails(logEntry, logger, Log.ERROR, logMessage, Optional.of(input), Optional.of(exception));
    }

}
