package com.neo.database;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Neo
 * Date: 13-7-20
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHelper {

    private SQLiteDatabase db;

    public DatabaseHelper(){
    }

    /**
     * 创建数据库
     * 安装软件时或者初始化时执行
     * 不要每次打开软件时执行
     * @param activity 将当前的Activity作为参数传递
     * @return 0 执行成功
     */
    public int CreateDatabase (Activity activity) {
        db=activity.openOrCreateDatabase("KeepMoving.db",Context.MODE_WORLD_WRITEABLE,null);
        Log.i("Database","Create database");
        return 0;
    }

    /**
     * 删除已经存在的数据库
     * 当需要删除数据库时执行
     * 删除数据库将删除数据库中所有的表以及信息
     * @return 0 执行成功
     * @param activity 将当前的Activity作为参数传递
     */
    public int DeleteDatabase(Activity activity) {
        activity.deleteDatabase("KeepMoving.db");
        Log.i("Database","Delete Database");
        return 0;
    }
}
