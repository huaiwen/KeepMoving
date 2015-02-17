package com.zhw.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created with IntelliJ IDEA.
 * User: Wymon Zhang
 * Date: 13-8-6
 * Time: 下午12:13
 * To change this template use File | Settings | File Templates.
 */
public class response extends BroadcastReceiver {
    private static final String CLICK_NAME_ACTION = "com.zhw.widget.click";

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(CLICK_NAME_ACTION)){

        }
    }
}
