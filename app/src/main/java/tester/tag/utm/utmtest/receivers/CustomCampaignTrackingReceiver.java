package tester.tag.utm.utmtest.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.analytics.CampaignTrackingReceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tester.tag.utm.utmtest.JsonHandler;

/**
 * Created by dheeraj on 1/7/15.
 */
public class CustomCampaignTrackingReceiver extends BroadcastReceiver {
    private static final String TAG = CustomCampaignTrackingReceiver.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(TAG);
    private static final Marker MARKER = MarkerFactory.getMarker(TAG);


    @Override
    public void onReceive(Context context,final Intent intentx) {
        LOGGER.info(MARKER, "on Receive called");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    for (String key : intentx.getExtras().keySet()) {
                        try {
                            LOGGER.info(MARKER, key + " => " + String.valueOf(intentx.getExtras().get(key)));
                        } catch (Exception e) {
                            LOGGER.error(MARKER, "caught exception in on key retrieval ", e);
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error(MARKER, "caught exception in key loop ", e);
                }
            }
        });
        executorService.shutdown();
    }
}
