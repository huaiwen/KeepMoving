package com.neo.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Neo
 * Date: 13-7-20
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
public class Task {
    public String TaskName;
    public String TaskContext;
    public String DueTime;
    public int Importance;
    public String TaskType;
    public String TaskID;
    public Cursor GlobalCursor;

    private SQLiteDatabase db;

    public enum TaskAttribute{TaskName,TaskContext,DueTime,Importance,TaskType,TaskID};
    public static final String DAILY="Daily";
    public static final String SHORTTIME="ShortTime";
    public static final String LONGTIME="LongTime";
    DatabaseHelper mOpenHelper;
    public Task()
    {
        TaskID=new String();
        TaskName=new String();
        TaskContext=new String();
        DueTime=new String();
        Importance=0;
        TaskType=new String();
    }

    
   
    
    /**
     * 连接数据库
     * 请在进行数据操作之前先连接数据库
     * @return 0表示执行成功
     * @param activity 将当前的Activity传入
     * */
    public int Connect(Activity activity){
        db=activity.openOrCreateDatabase("KeepMoving.db",Context.MODE_WORLD_WRITEABLE,null);
        Log.i("Database","Open KeepMoving.db");
     //   db.execSQL("select * from Task");
        return 0;
    }

    /**
     * 关闭连接
     * 请务必在进行完操作后进行连接的关闭，否则将会影响下次操作
     * @return 0表示执行成功
     * */
    public int Close()
    {
        db.close();
        Log.i("Database","Close KeepMoving.db");
        return 0;
    }


    /**
     * 创建表
     * 在软件安装时或者初始化时执行
     * 这个函数应该执行一次，再次执行将会把原来的表删除并建立新表
     * @return 0表示执行成功
     * */
    public int CreateTable() {
        db.execSQL("DROP TABLE IF EXISTS Task");
        db.execSQL("CREATE TABLE Task (TaskID VARCHAR PRIMARY KEY, TaskName VARCHAR, TaskType VARCHAR, TaskContext VARCHAR, DueTime VARCHAR, Importance INTEGER)");
        Log.i("TaskTable", "Create Task Table");
        return  0;
    }

    /**
     * 删除表
     * 在需要删除表时执行
     * @return 0表示执行成功
     * * */
    public int DropTable() {
        db.execSQL("DROP TABLE IF EXISTS Task");
        Log.i("TaskTable","Drop Task");
        return 0;
    }

    /**
     * 向数据库中插入任务信息
     * 执行该函数将会把当前对象里面的信息插入到表中
     * @return 0表示执行成功
     * */
    public int Insert() {
        ContentValues cv=new ContentValues();
        cv.put("TaskID",TaskID);
        cv.put("TaskName",TaskName);
        cv.put("TaskType",TaskType);
        cv.put("TaskContext",TaskContext);
        cv.put("DueTime",DueTime);
        cv.put("Importance",Importance);
        db.insert("Task",null,cv);
        Log.i("TaskTable","Insert Task");
        return 0;
    }

    
   
    
    /**
     * 修改数据库中的任务信息
     * 执行该函数将会修改表中的数据为当前对象中储存的信息
     * @return 0表示执行成功
     * */
    public int Update() {
        ContentValues cv=new ContentValues();
        cv.put("TaskID",TaskID);
        cv.put("TaskName",TaskName);
        cv.put("TaskType",TaskType);
        cv.put("TaskContext",TaskContext);
        cv.put("DueTime",DueTime);
        cv.put("Importance",Importance);
        db.update("Task",cv,"TaskID= ?",new String[]{TaskID});
        Log.i("TaskTable","Update Task");
        return 0;
    }
    
    
    /**
     * 修改数据库中的任务信息
     * 执行该函数将会修改当前任务的某项属性
     * 不可修改TaskID
     * @return 0表示执行成功
     * */
    public int UpdateTask(TaskAttribute attr,String name) {
        ContentValues cv=new ContentValues();
        switch (attr) {
            case TaskName:
                cv.put("TaskName",name);
                db.update("Task",cv,"TaskID= ?",new String[]{TaskID});
                break;
            case TaskContext:
                cv.put("TaskContext",name);
                db.update("Task",cv,"TaskID= ?",new String[]{TaskID});
                break;
            case DueTime:
                cv.put("DueTime",name);
                db.update("Task",cv,"TaskID= ?",new String[]{TaskID});
                break;
            case Importance:
                cv.put("Importance",Integer.getInteger(name));
                db.update("Task",cv,"TaskID= ?",new String[]{TaskID});
                break;
            case TaskType:
                cv.put("TaskType",TaskType);
                db.update("Task",cv,"TaskID= ?",new String[]{TaskID});
                break;
            default:
                Log.e("UpdateTask","Error Task Attribute");
                break;
        }
        return 0;
    }

    /**
     * 删除任务信息
     * 执行该函数将删除当前任务所有的相关信息
     * @return 0表示执行成功
     * */
    public int DeleteTask(String ID){
        db.delete("Task", "TaskID= ?", new String[]{ID});
        Log.i("TaskTable","Delete Task");
        return 0;
    }
    
     
    public int Select(int importance){
    //	String[] columns = new String[] { TaskName, TaskContext}; 
    	
    	db.query("Task", null, null, null ,null, null, importance+" DESC");
    	return 0;
    }
    
    /**
     * 根据ID获取任务信息
     * 执行该函数将获取数据库中的用户信息，保存在该对象中
     * @return 0表示执行成功 1表示执行失败
     * @param ID 要获得的任务的ID
     * */
    public int GetTask(String ID){
        Cursor cursor=db.rawQuery("select * from Task where TaskID= ?",new String[]{ID});
        if (cursor.moveToFirst()){
            TaskID=cursor.getString(0);
            TaskName=cursor.getString(1);
            TaskType=cursor.getString(2);
            TaskContext=cursor.getString(3);
            DueTime=cursor.getString(4);
            Importance=cursor.getInt(5);
            return 0;
        }
        return 1;
    }

    /**
     * 根据类型获取日常任务信息
     * 执行该函数将获取数据库中的用户信息，保存在该对象中
     * @param Type 请使用本类自带的常量来说明要查询的任务类型
     * @return 0表示执行成功 1表示执行失败
     * */
    public int GetTaskByType(String Type,String SortKey){
        if (GlobalCursor!=null){
            GlobalCursor.close();
            GlobalCursor=null;
        }
        String str="select * from Task where TaskType='"+Type+"' order by "+SortKey+" ASC";
        GlobalCursor=db.rawQuery(str,null);
        if (GlobalCursor.moveToFirst()){
            TaskID=GlobalCursor.getString(0);
            TaskName=GlobalCursor.getString(1);
            TaskType=GlobalCursor.getString(2);
            TaskContext=GlobalCursor.getString(3);
            DueTime=GlobalCursor.getString(4);
            Importance=GlobalCursor.getInt(5);
            return 0;
        }
        return 1;
    }
    public int GetTaskByType1(String Type,String SortKey){
        if (GlobalCursor!=null){
            GlobalCursor.close();
            GlobalCursor=null;
        }
        String str="select * from Task where TaskType='"+Type+"' order by "+SortKey+" DESC";
        GlobalCursor=db.rawQuery(str,null);
        if (GlobalCursor.moveToFirst()){
            TaskID=GlobalCursor.getString(0);
            TaskName=GlobalCursor.getString(1);
            TaskType=GlobalCursor.getString(2);
            TaskContext=GlobalCursor.getString(3);
            DueTime=GlobalCursor.getString(4);
            Importance=GlobalCursor.getInt(5);
            return 0;
        }
        return 1;
    }
   
    /*移动到当前位置*/
    public int MoveCur()
    {
    	if (GlobalCursor==null)
            return 2;
        if (GlobalCursor.moveToPrevious())
        {
            TaskID=GlobalCursor.getString(0);
            TaskName=GlobalCursor.getString(1);
            TaskType=GlobalCursor.getString(2);
            TaskContext=GlobalCursor.getString(3);
            DueTime=GlobalCursor.getString(4);
            Importance=GlobalCursor.getInt(5);
            return 0;
        }
        else{
            GlobalCursor.close();
            GlobalCursor=null;
            return 1;
        }
    			
    }
    
    
    /*判断是否为第一条*/
    public int isFirst()
    {
    	if (GlobalCursor==null)
            return 2;
        if (GlobalCursor.isFirst())
        {
            TaskID=GlobalCursor.getString(0);
            TaskName=GlobalCursor.getString(1);
            TaskType=GlobalCursor.getString(2);
            TaskContext=GlobalCursor.getString(3);
            DueTime=GlobalCursor.getString(4);
            Importance=GlobalCursor.getInt(5);
            return 0;
        }
        else{
            GlobalCursor.close();
            GlobalCursor=null;
            return 1;
        }
    			
    }
    
    /**
     * 获取当前类型的下一条任务
     * 执行该函数将获取数据库中的用户信息，保存在该对象中
     * @return 0 执行成功 1 到最后一条了 2 还没有执行GetTaskByType函数
     * */
    public int MoveNext()
    {
    	
    	
        if (GlobalCursor==null)
            return 2;
        if (GlobalCursor.moveToNext())
        {
            TaskID=GlobalCursor.getString(0);
            TaskName=GlobalCursor.getString(1);
            TaskType=GlobalCursor.getString(2);
            TaskContext=GlobalCursor.getString(3);
            DueTime=GlobalCursor.getString(4);
            Importance=GlobalCursor.getInt(5);
            return 0;
        }
        else{
            GlobalCursor.close();
            GlobalCursor=null;
            return 1;
        }
    }
    public int GetCount()
    {
    	return GlobalCursor.getCount();
    }
    
    
}

