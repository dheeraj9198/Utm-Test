package tester.tag.utm.utmtest.receivers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import tester.tag.utm.utmtest.JsonHandler;

/**
 * Created by dheeraj on 1/7/15.
 */
public class CustomCampaignTrackingReceiver extends CampaignTrackingReceiver {
    private static final String TAG = CustomCampaignTrackingReceiver.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(TAG);
    private static final Marker MARKER = MarkerFactory.getMarker(TAG);


    @Override
    public void onReceive(Context context, Intent intentx) {
        super.onReceive(context, intentx);
        LOGGER.info(MARKER, "on Receive called");

        try {
            for (String key : intentx.getExtras().keySet()) {
                try {
                    LOGGER.info(MARKER,"key : "+key+" => value : "+String.valueOf(intentx.getExtras().get(key)));
                } catch (Exception e) {
                    LOGGER.error(MARKER, "caught exception in on key retrieval ", e);
                }
            }
        } catch (Exception e) {
            LOGGER.error(MARKER, "caught exception in key loop ", e);
        }

        try{
            LOGGER.info(MARKER,"intent.toString"+intentx.toString());
        }catch (Exception e){
            LOGGER.error(MARKER, "caught exception in intent toString", e);
        }

        try{
            LOGGER.info(MARKER,"bundle.toString"+intentx.getExtras().toString());
        }catch (Exception e){
            LOGGER.error(MARKER, "caught exception in bundle toString", e);
        }


        try {

            String s1 = JsonHandler.stringify(intentx);
            LOGGER.info(MARKER, "String S1 : " + (s1 == null ? "null" : s1));
            String s2 = JsonHandler.stringify(intentx.getExtras());
            LOGGER.info(MARKER, "String S2 : " + (s2 == null ? "null" : s2));

            String test = "null";

            if (s1 == null && s2 == null) {
                test = "both null";
            } else if (s2 != null) {
                test = s2;
            } else {
                test = s1;
            }

            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dheeraj.sachan@superprofs.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Device Logs");
            String email = "";
            intent.putExtra(Intent.EXTRA_TEXT, "Email used for registration : " + test);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(intent, "Send email..."));
        } catch (Exception e) {
            LOGGER.error(MARKER,"caught exception in big catch",e);
        }
    }
}
