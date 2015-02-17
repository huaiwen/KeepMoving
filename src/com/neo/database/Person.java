package com.neo.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Neo
 * Date: 13-7-20
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */
public class Person {
    public String UserName;
    public String UserPassword;
    public String UserImage;
    public String Achievement;

    private SQLiteDatabase db;

    public enum UserAttribute{UserName,UserPassword,UserImage,Achievement};

    public Person(){
        UserImage=new String();
        UserName=new String();
        UserPassword=new String();
        Achievement=new String();
    }


    /**
     * 连接数据库表
     * 在数据操作前执行
     * @return 0 成功执行
     * */
    public int Connect(Activity activity){
        db=activity.openOrCreateDatabase("KeepMoving.db",Context.MODE_WORLD_WRITEABLE,null);
        Log.i("Database","Open KeepMoving.db");
        return 0;
    }

    /**
     * 创建表
     * 在软件安装时或者初始化时执行
     * 这个函数应该执行一次，再次执行将会把原来的表删除并建立新表
     * */
    public int CreateTable() {
        db.execSQL("DROP TABLE IF EXISTS Person");
        db.execSQL("CREATE TABLE Person (UserName VARCHAR PRIMARY KEY, UserPassword VARCHAR, Achievement VARCHAR, UserImage VARCHAR)");
        Log.i("PersonTable","Create Person Table");
        return  0;
    }

    /**
     * 关闭连接
     * 在需要删除表时执行,在完成操作后请务必关闭连接，否则将会影响下次操作
     * */
    public int Close() {
        db.close();
        Log.i("PersonTable","Close Connect");
        return 0;
    }


    /**
     * 删除表
     * 在需要删除表时执行
     * */
    public int DropTable() {
        db.execSQL("DROP TABLE IF EXISTS Person");
        Log.i("PersonTable","Drop person");
        return 0;
    }

    /**
     * 向数据库中插入用户信息
     * 执行该函数将会把当前对象里面的信息插入到表中
     * */
    public int Insert() {
        ContentValues cv=new ContentValues();
        cv.put("UserName",UserName);
        cv.put("UserImage",UserImage);
        cv.put("UserPassword",MD5.GetMD5(UserPassword));
        cv.put("Achievement",Achievement);
        db.insert("Person",null,cv);
        Log.i("PersonTable","Insert person");
        return 0;
    }

    /**
     * 修改数据库中的用户信息
     * 执行该函数将会修改表中的数据为当前对象中储存的信息
     * */
    public int Update() {
        ContentValues cv=new ContentValues();
        cv.put("UserName",UserName);
        cv.put("UserImage",UserImage);
        cv.put("UserPassword",MD5.GetMD5(UserPassword));
        cv.put("Achievement",Achievement);
        db.update("Person",cv,null,null);
        Log.i("PersonTable","Update person");
        return 0;
    }

    /**
     * 修改数据库中的用户信息
     * 执行该函数将会修改用户的某项属性
     * */
    public int UpdateUser(UserAttribute attr,String str) {
        ContentValues cv=new ContentValues();
        switch (attr){
            case UserName:
                cv.put("UserName",str);
                db.update("Person",cv,null,null);
                Log.i("PersonTable","Update UserName");
                break;
            case UserImage:
                cv.put("UserImage",str);
                db.update("Person",cv,null,null);
                Log.i("PersonTable","Update UserImage");
                break;
            case UserPassword:
                cv.put("UserPassword",MD5.GetMD5(str));
                db.update("Person",cv,null,null);
                Log.i("PersonTable","Update UserPassword");
                break;
            case Achievement:
                cv.put("Achievement",str);
                db.update("Person",cv,null,null);
                Log.i("PersonTable","Update Achievement");
                break;
        }
        return 0;
    }

    /**
     * 删除用户信息
     * 执行该函数将删除用户所有的相关信息
     * */
    public int DeleteUser(){
        db.delete("Person", null, null);
        Log.i("PersonTable","Delete person");
        return 0;
    }

    /**
     * 获取用户信息
     * 执行该函数将获取数据库中的用户信息，保存在该对象中
     * */
    public int GetUser(){
        Cursor cursor=db.rawQuery("select * from Person",null);
        if (cursor.moveToFirst()){
            UserName=cursor.getString(0);
            UserPassword=cursor.getString(1);
            Achievement=cursor.getString(2);
            UserImage=cursor.getString(3);

            if (UserPassword.compareTo("D41D8CD98F00B204E9800998ECF8427E")==0)
                UserPassword="";
            return 0;
        }
        return 1;
    }
}

































