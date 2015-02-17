package com.zhw.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.imudges.keepmoving.R;
import com.zhw.guide.SplashActivity;
import com.zhw.ui.LongTaskPageActivity;
import com.zhw.ui.ShortTaskPageActivity;
import com.zhw.ui.UiMainActivity;

/**
 * Created with IntelliJ IDEA.
 * User: Wymon Zhang
 * Date: 13-8-5
 * Time: 下午12:07
 * To change this template use File | Settings | File Templates.
 */


public class WidgetProvider extends AppWidgetProvider {
    public static int  CURRENT_SATUS=0;
    private static final String CLICK_EVE_ICON_ACTION = "com.zhw.widget.icon_eve_clicked";
    private static final String CLICK_SHT_ICON_ACTION = "com.zhw.widget.icon_sht_clicked";
    private static final String CLICK_LON_ICON_ACTION = "com.zhw.widget.icon_lon_clicked";
    /* private static final String CLICK_EVE_TEXT_ACTION = "com.zhw.widget.eve_clicked";
     private static final String CLICK_SHT_TEXT_ACTION = "com.zhw.widget.sht_clicked";
     private static final String CLICK_LON_TEXT_ACTION = "com.zhw.widget.lon_clicked";*/
    private static int EVE = 0, SHT = 0, LON = 0;

    private static RemoteViews rv;

    /**
     * onUpdate 为组件在桌面上生成时调用，并更新组件UI.
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // TODO Auto-generated method stub
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    /*
   * onReceiver 为接收广播时调用更新UI，一般这两个方法是比较常用的。
   * 在后台注册Receiver
   * 通过判断传进来的广播来触发动作
   * **/
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);
        if (rv == null) {
            rv = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        }
        if (intent.getAction().equals(CLICK_EVE_ICON_ACTION)) {
            rv.setImageViewResource(R.id.widget_user_logo,R.drawable.importance1);
            rv.setTextViewText(R.id.widget_task_name,"每日任务");
            rv.setTextViewText(R.id.widget_task_bi,"人生已如此的艰难");
            rv.setTextViewText(R.id.widget_count_time_tv,"12:10");
            Toast.makeText(context,"每日任务",Toast.LENGTH_LONG).show();
            Intent intent_Ui_Main = new Intent(context, UiMainActivity.class);
            PendingIntent P_intent_Ui_Main = PendingIntent.getActivity(context, 0, intent_Ui_Main, 0);
            rv.setOnClickPendingIntent(R.id.list_item, P_intent_Ui_Main);
        }
        if (intent.getAction().equals(CLICK_SHT_ICON_ACTION)) {
            rv.setImageViewResource(R.id.widget_user_logo,R.drawable.importance2);
            rv.setTextViewText(R.id.widget_task_name,"短期任务");
            rv.setTextViewText(R.id.widget_task_bi,"有些事情就不要拆穿");
            rv.setTextViewText(R.id.widget_count_time_tv,"12:20");
            Toast.makeText(context,"短期任务",Toast.LENGTH_LONG).show();
            Intent intent_Sh_act = new Intent(context, ShortTaskPageActivity.class);
            PendingIntent P_intent_Sh_act = PendingIntent.getActivity(context, 0, intent_Sh_act, 0);
            rv.setOnClickPendingIntent(R.id.list_item, P_intent_Sh_act);
        }
        if (intent.getAction().equals(CLICK_LON_ICON_ACTION)) {
            rv.setImageViewResource(R.id.widget_user_logo,R.drawable.importance3);
            rv.setTextViewText(R.id.widget_task_name,"周期任务");
            rv.setTextViewText(R.id.widget_task_bi,"我没有说谎");
            rv.setTextViewText(R.id.widget_count_time_tv,"12:30");
            Toast.makeText(context,"周期任务",Toast.LENGTH_LONG).show();
            Intent intent_Lon_act = new Intent(context, LongTaskPageActivity.class);
            PendingIntent P_intent_Lon_act = PendingIntent.getActivity(context, 0, intent_Lon_act, 0);
            rv.setOnClickPendingIntent(R.id.list_item, P_intent_Lon_act);
        }


        AppWidgetManager appWidgetManger = AppWidgetManager
                .getInstance(context);
        int[] appIds = appWidgetManger.getAppWidgetIds(new ComponentName(
                context, WidgetProvider.class));
        appWidgetManger.updateAppWidget(appIds, rv);
    }

    /*
    * 创建组件时 onUpdate 调用的更新UI的方法，
    * 代码中使用RemoteView 找到组件的布局文件，
    * 同时为其设置广播接收器CLICK_NAME_ACTION
    * 通过RemoteView 的setOnClickPendingIntent 方法
    * 找到触发事件的TextView 为其设置广播。
    * */
    public static void updateAppWidget(Context context,
                                       AppWidgetManager appWidgetManager, int appWidgetId) {
        //找到视图，然后，再处理。
        rv = new RemoteViews(context.getPackageName(), R.layout.widget_main);

        //三个图标被点击后的消息传递 ，转换视图下面的tv
        Intent Icon_intent_eve = new Intent(CLICK_EVE_ICON_ACTION);
        Intent Icon_intent_sht = new Intent(CLICK_SHT_ICON_ACTION);
        Intent Icon_intent_lon = new Intent(CLICK_LON_ICON_ACTION);
        //点击logo和下方分tv是响应act
        Intent intent_Splash = new Intent(context, SplashActivity.class);

        PendingIntent P_Intent_sht = PendingIntent.getBroadcast(context, 0, Icon_intent_sht, 0);
        PendingIntent P_Intent_eve = PendingIntent.getBroadcast(context, 0, Icon_intent_eve, 0);
        PendingIntent P_Intent_lon = PendingIntent.getBroadcast(context, 0, Icon_intent_lon, 0);
        PendingIntent P_intent_Splash = PendingIntent.getActivity(context, 0, intent_Splash, 0);

        rv.setOnClickPendingIntent(R.id.widget_main, P_intent_Splash);
        rv.setOnClickPendingIntent(R.id.widget_eve, P_Intent_eve);
        rv.setOnClickPendingIntent(R.id.widget_st, P_Intent_sht);
        rv.setOnClickPendingIntent(R.id.widget_cl, P_Intent_lon);
        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }
}
