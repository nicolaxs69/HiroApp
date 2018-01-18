package xebia.ismail.e_learning;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService  {
    private static final String TAG = "FirebaseMessagingServic";

    public FirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Map<String, String> data = remoteMessage.getData();

        //you can get your text message here.
        String message= data.get("text");
        String title = data.get("title");
        sendNotification(title, message);

    /*You can read more on notification here:
    https://developer.android.com/training/notify-user/build-notification.html
    https://www.youtube.com/watch?v=-iog_fmm6mE
    */
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//        }
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            String title = remoteMessage.getNotification().getTitle(); //get title
//            String message = remoteMessage.getNotification().getBody(); //get message
//
//            Log.d(TAG, "Message Notification Title: " + title);
//            Log.d(TAG, "Message Notification Body: " + message);
//
//            sendNotification(title, message);
//        }
    }

    @Override
    public void onDeletedMessages() {

    }

    private void sendNotification(String title, String messageBody) {

        Intent resul = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resul);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);

        Log.d("Intent", "sendNotification: "+resul.toString());
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        //.setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setSmallIcon(R.drawable.rere_c)
                        .setContentTitle("Alerta de Tsunami!")
                        .setContentText("Hiro App")
                        .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarm_1))
                       // .setDefaults(Notification.DEFAULT_SOUND)
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true);
                        long[] pattern = {0, 5000, 1000, 5000};
                        mBuilder .setVibrate(pattern);
                        mBuilder.setLights(Color.RED, 500, 1000);


        RemoteViews notificationView = getComplexNotificationView();
        notificationView.setOnClickPendingIntent(R.id.yes, resultPendingIntent);
        mBuilder.setCustomBigContentView(notificationView);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }


    private RemoteViews getComplexNotificationView() {
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews notificationView = new RemoteViews(
                getPackageName(),
                R.layout.activity_custom_notification
        );

        // Locate and set the Image into customnotificationtext.xml ImageViews
        notificationView.setImageViewResource(
                R.id.imagenotileft,
                R.drawable.ic_nami); //Logo de Hiro

        // Locate and set the Text into customnotificationtext.xml TextViews
        notificationView.setTextViewText(R.id.title, "ALERTA DE STUNAMI!!");

        String msg;
        Spanned msgHtml;

        notificationView.setImageViewResource(
                R.id.imageAppIcon1,
                R.drawable.ic_alert);

        msg = "Evacuacion preventiva por "
                + "<b>" + "Amenaza de Tsunami" + "</b>."
                + " Dirijase al punto de resguardo mas cercano ";
        msgHtml = Html.fromHtml(msg);
        notificationView.setTextViewText(R.id.textBelow, msgHtml);

        return notificationView;
    }
}
