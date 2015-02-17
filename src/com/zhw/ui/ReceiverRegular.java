package com.zhw.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceiverRegular extends BroadcastReceiver {
	private Notification notification;    
    //声明NotificationManager    
    private NotificationManager mNotification;    
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("wowowowowoow");
		if("com.alarm.action_alarm_on".equals(intent.getAction()))
        {
                 //跳转至AlarmAlertService.class,判断是否已到定时时间
			     System.out.println("hehehe");
                 Intent i=new Intent(context,AlarmAlertSericeRegular.class);
                 context.startService(i);
        }
	     
	}

}
