package tester.tag.utm.utmtest.receivers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.analytics.CampaignTrackingReceiver;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import tester.tag.utm.utmtest.JsonHandler;

/**
 * Created by dheeraj on 1/7/15.
 */
public class CustomCampaignTrackingReceiver extends CampaignTrackingReceiver {
    @Override
    public void onReceive(Context context, Intent intentx) {
        super.onReceive(context, intentx);
        try {

            String s1 = JsonHandler.stringify(intentx);
            String s2 = JsonHandler.stringify(intentx.getExtras());

            String test = "null";

            if(s1 == null && s2 == null){
                test = "both null";
            }else if(s2 != null){
                test = s2;
            }else {
                test = s1;
            }

            Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dheeraj.sachan@superprofs.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Device Logs");
            String email = "";
            intent.putExtra(Intent.EXTRA_TEXT, "Email used for registration : " + test  );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(intent, "Send email..."));
        } catch (Exception e) {
            Toast.makeText(context, "Unable to mail logs : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
