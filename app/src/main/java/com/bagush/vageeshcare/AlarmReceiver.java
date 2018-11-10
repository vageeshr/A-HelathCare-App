package com.bagush.vageeshcare;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "vageeshcare";

    String name, res;
    int id;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

             name = "You can take a break from work";


        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        if(id != -1) {

            Intent notificationIntent = new Intent(context, ExerciseActivity.class);



            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 777,
                    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("It's time to exercise!!")
                    .setContentText(name)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            notificationManager.notify(777, mNotifyBuilder.build());
        } else {

            //TODO show some other type of notifications if used not logged in
            //TODO Ex: 1 - Register account notification, 2 - Add user detail notification

        }



    }
}
