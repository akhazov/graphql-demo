/**
 * Конфигурация логирования.
 * События пишутся в 2 файла. Общая информация и отдельно ошибки.
 */

def LOG_DIR = "log"
def LOG_LINE_PATTERN = "%d{dd.MM.yyyy HH:mm:ss.SSS} [%thread] %-5level %logger{20} - %msg%n"


// Вывод на консоль.
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = LOG_LINE_PATTERN
    }
}

// Вывод в файл ошибок.
appender("FILE_ERROR", RollingFileAppender) {
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${LOG_DIR}/%d{yyyy-MM-dd}_ERROR.log"
        maxHistory = 10
        totalSizeCap = "10MB"
    }
    encoder(PatternLayoutEncoder) {
        pattern = LOG_LINE_PATTERN
    }
}

//Вывод в файл информации с уровнем INFO.
appender("FILE_INFO", RollingFileAppender) {
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${LOG_DIR}/%d{yyyy-MM-dd}_INFO.log"
        maxHistory = 10
        totalSizeCap = "10MB"
    }
    encoder(PatternLayoutEncoder) {
        pattern = LOG_LINE_PATTERN
    }
}

// Логирование ошибок.
logger("com.drmun.graphqldemo", ERROR, ["FILE_ERROR"], false)
// Основное логирование.
root(INFO, ["STDOUT", "FILE_INFO"])