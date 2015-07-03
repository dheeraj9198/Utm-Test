package tester.tag.utm.utmtest;

import android.os.Environment;

import java.io.File;

/**
 * Created by dheeraj on 15/6/15.
 */
public class LoggerConfig {

    private LoggerConfig() {
    }

        public static File getLogFolder() {
                File file = new File(Environment.getExternalStorageDirectory(), "UTM_LOGS");
                if (!file.exists()) {
                        file.mkdirs();
                }
                return file;
        }

    public static final String xmlConfig = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<configuration>\n" +
            "\n" +
            "    <appender name=\"file\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n" +
            "        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n" +
            "            <level>INFO</level>\n" +
            "            <onMatch>ACCEPT</onMatch>\n" +
            "            <onMismatch>DENY</onMismatch>\n" +
            "        </filter>\n" +
            "        <File>" + getLogFolder().getAbsolutePath() + File.separator + "UTM_LOGS.txt</File>\n" +
            "        <encoder>\n" +
            "            <pattern>%date{dd MMM yyyy;HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>\n" +
            "        </encoder>\n" +
            "        <rollingPolicy class=\"ch.qos.logback.core.rolling.FixedWindowRollingPolicy\">\n" +
            "            <maxIndex>5</maxIndex>\n" +
            "            <FileNamePattern>" + getLogFolder().getAbsolutePath() + File.separator + "UTM_LOGS.%i.txt</FileNamePattern>\n" +
            "        </rollingPolicy>\n" +
            "        <triggeringPolicy class=\"ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy\">\n" +
            "            <maxFileSize>1500KB</maxFileSize>\n" +
            "        </triggeringPolicy>\n" +
            "    </appender>\n" +
            "\n" +
            "    <appender name=\"logcat\" class=\"ch.qos.logback.classic.android.LogcatAppender\">\n" +
            "        <filter class=\"ch.qos.logback.classic.filter.LevelFilter\">\n" +
            "            <level>DEBUG</level>\n" +
            "            <onMatch>ACCEPT</onMatch>\n" +
            "            <onMismatch>DENY</onMismatch>\n" +
            "        </filter>\n" +
            "        <encoder>\n" +
            "            <pattern>%date{dd MMM yyyy;HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>\n" +
            "        </encoder>\n" +
            "    </appender>\n" +
            "\n" +
            "    <root level=\"DEBUG\">\n" +
            "        <appender-ref ref=\"file\" />\n" +
            "        <appender-ref ref=\"logcat\" />\n" +
            "    </root>\n" +
            "\n" +
            "</configuration>\n";
}
