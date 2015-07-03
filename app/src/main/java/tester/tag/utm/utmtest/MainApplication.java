package tester.tag.utm.utmtest;

import android.app.Application;
import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * Created by dheeraj on 1/7/15.
 */
public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(TAG);
    private static final Marker MARKER = MarkerFactory.getMarker(TAG);

    @Override
    public void onCreate() {
        super.onCreate();
        configureLogbackByString();
        LOGGER.info(MARKER,"main application created ");
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                LOGGER.error(MARKER,"uncaught exception caught ",throwable);
                uncaughtExceptionHandler.uncaughtException(thread,throwable);
            }
        });
    }


    private void configureLogbackByString() {
        // reset the default context (which may already have been initialized)
        // since we want to reconfigure it
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        lc.reset();

        JoranConfigurator config = new JoranConfigurator();
        config.setContext(lc);

        InputStream stream = new ByteArrayInputStream(LoggerConfig.xmlConfig.getBytes());
        try {
            config.doConfigure(stream);
        } catch (JoranException e) {
            Log.e(TAG, "unable to configure logger", e);
        }
    }
}
