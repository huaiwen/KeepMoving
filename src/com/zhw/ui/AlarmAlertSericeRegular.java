package com.zhw.ui;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.imudges.keepmoving.R;

public class AlarmAlertSericeRegular extends IntentService{
	private static final String TAG = "MyService";

	public AlarmAlertSericeRegular() {
		super("MyService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
		
		
		showNotification(true);

	}

	private void showNotification(Boolean finished) {
		Notification notification;
		int icon = R.drawable.wight_main2; // icon from resources
		long when = System.currentTimeMillis(); // notification time
		Context context = getApplicationContext(); // application Context
		CharSequence contentTitle = "坚持达人"; // message title
		CharSequence contentText = "周期任务到时间了!"; // message text

		Intent notificationIntent = new Intent(this, ShortTaskPageActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		// the next two lines initialize the Notification, using the
		// configurations above
		NotificationManager manage = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// TODO Auto-generated method stub
		if (finished) {
			
			CharSequence tickerText = "有新信息哦!"; // ticker-text
			notification = new Notification(icon, tickerText, when);
			notification.setLatestEventInfo(context, contentTitle, contentText,
					contentIntent);
			notification.defaults = Notification.DEFAULT_ALL;
			manage.notify(0, notification);
		} 

	}
	
	
	
        /*
        
         public AlarmAlertService()
         {
                   super("AlarmAlertService");
                  
         }
 
        
         @Override
         protected void onHandleIntent(Intent arg0)
         {
       /* 	int icon = R.drawable.cream; // icon from resources
     		long when = System.currentTimeMillis();
        	NotificationManager nm= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        	 
        	CharSequence contentTitle = "My notification"; // message title
     		CharSequence contentText = "Hello World!"; // message text
        	 Notification notification=new Notification(icon,contentTitle,System.currentTimeMillis());
         	//后面的参数分别是显示在顶部通知栏的小图标，小图标旁的文字（短暂显示，自动消失）系统当前时间（不明白这个有什么用）
         	notification.defaults=Notification.DEFAULT_ALL; */
         	//这是设置通知是否同时播放声音或振动，声音为Notification.DEFAULT_SOUND
         	//振动为Notification.DEFAULT_VIBRATE;
         	//Light为Notification.DEFAULT_LIGHTS，在我的Milestone上好像没什么反应
         	//全部为Notification.DEFAULT_ALL
         	//如果是振动或者全部，必须在AndroidManifest.xml加入振动权限
      //   	PendingIntent pt=PendingIntent.getActivity(this, 0, new Intent(this,MainActivity.class), 0);
         	//点击通知后的动作，这里是转回main 这个Acticity
     //    	notification.setLatestEventInfo(this,contentTitle,contentText,pt);
     //    	nm.notify(R.drawable.cream, notification);  */
      //  	 System.out.println("wocaocaocao");
     //   	 Notification notification;
 /*    		int icon = R.drawable.cream; // icon from resources
     		long when = System.currentTimeMillis(); // notification time
     		Context context = getApplicationContext(); // application Context
     		CharSequence contentTitle = "My notification"; // message title
     		CharSequence contentText = "Hello World!"; // message text

     		Intent notificationIntent = new Intent(this, MainActivity.class);
     		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

     		// the next two lines initialize the Notification, using the
     		// configurations above
     		NotificationManager manage = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
     		CharSequence tickerText = "下载结束";
			notification = new Notification(icon, tickerText, when);
			notification.setLatestEventInfo(context, contentTitle, contentText,
					contentIntent);
			notification.defaults |= Notification.DEFAULT_SOUND;
         }
 */
        
}
