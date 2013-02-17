package org.hearingthevoice.innerlife.services;

import org.hearingthevoice.innerlife.R;
import org.hearingthevoice.innerlife.ui.activity.DashboardActivity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class AlarmReceiver extends BroadcastReceiver
{
	// This function is called when an alarm is received
	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO check whether an addition notification needs to be created
		
		NotificationManager nm;
		nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder nb = new NotificationCompat.Builder(context);
		nb.setContentTitle("New Questions Available");
		nb.setContentText("Click to participate. " + intent.getStringExtra("NOTIFICATION_TYPE"));
		nb.setSmallIcon(R.drawable.next_item);
		
		Intent clickIntent = new Intent(context, DashboardActivity.class);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(DashboardActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(clickIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		nb.setContentIntent(resultPendingIntent);
		nb.setAutoCancel(true);
		
		nb.setSound(Uri.parse("content://settings/system/notification_sound"));
		
		nm.notify(0, nb.build());
	}
}