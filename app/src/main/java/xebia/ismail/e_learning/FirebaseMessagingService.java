package xebia.ismail.e_learning;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;

import java.util.Locale;
import java.util.Map;


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMessagingServic";
    public static Handler handlerDatos;
    public String message;
    public String lat;
    public String lon;
    public String coordenada_evacuacion;

    public FirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> data = remoteMessage.getData();
        //you can get your text message here.
        String message = data.get("text");
        String title = data.get("title");
        sendNotification(title, message);
        Log.d("Mensaje","Putaaaaaaaaaaaaaaaaaa");

        //PASO 1. Cuando llega el mensaje de Firebase, se notifica a la otra clase que prenda el GPS
        mThread.run();
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

    public class MyThread extends Thread {
        @Override
        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_MORE_FAVORABLE);
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
            Looper.prepare();

            // Envio la peticion
            Message dataToSend2 = sendBundle("Send GPS Coordinates");
            if (MainActivity.handlerDatos != null) {
                MainActivity.handlerDatos.sendMessage(dataToSend2);
            }
            Looper.loop();
        }
    }
    MyThread mThread = new MyThread();

    @Override
    public void onDeletedMessages() {
    }

    @SuppressLint("HandlerLeak")
    private void sendNotification(String title, String messageBody) {

        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();

                handlerDatos = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        handlerDatos.obtainMessage();

                        message = msg.getData().getString("data");
                        String LatLong = String.valueOf(message);
                         lat = LatLong.substring(10, 19);
                         lon = LatLong.substring(20, 29);

                    }
                };
                Looper.loop();
            }
        }).start();


        String coordenada_evacuacion = String.format(Locale.getDefault(), "google.navigation:q=-12.073743,-77.163599&mode=w");
//        Log.d("Mensaje",lat);
//        Log.d("Mensaje",lon);
 //      coordenada_evacuacion = String.format(Locale.getDefault(), "google.navigation:q="+lat+","+lon+"&mode=w");
       // Log.d("Mensaje",coordenada_evacuacion);
        // prepare intent to send to another app
        Intent intentTraceRoute = new Intent(Intent.ACTION_VIEW);
        intentTraceRoute.setData(Uri.parse(coordenada_evacuacion));
        // send intent
        //startActivity(intentTraceRoute);

        //Al presionar EVACUAR
        //Intent resul = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intentTraceRoute); // Aqui se pone el intente a donde se quiere mandar
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
                mBuilder.setSmallIcon(R.drawable.rere_c);
                mBuilder.setContentTitle("Alerta de Tsunami!");
                mBuilder.setContentText("Hiro App");
                mBuilder.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarm_1));
                mBuilder.setContentIntent(resultPendingIntent);
                mBuilder.setAutoCancel(true);
                long[] pattern = {0, 5000, 1000, 5000};
        mBuilder.setVibrate(pattern);
        mBuilder.setLights(Color.RED, 500, 1000);

        RemoteViews notificationView = getComplexNotificationView();
        notificationView.setOnClickPendingIntent(R.id.yes, resultPendingIntent); // ResultPendingIntent boton Evacuar
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

    private Message sendBundle(String mensaje) {

        Message dataToSend = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("data", mensaje);
        dataToSend.setData(bundle);
        return dataToSend;
    }

}
