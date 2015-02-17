package com.imudges.keepmoving;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.neo.database.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private DatabaseHelper helper;
    private Person person;
    private Task task;

    private Button createDatabase;
    private Button deleteDatabase;
    private Button createPersonTable;
    private Button deletePersonTable;
    private Button insertUser;
    private Button deleteUser;
    private Button queryUser;
    private Button updateUser;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        helper=new DatabaseHelper();
        person=new Person();
        task=new Task();

        createDatabase=(Button)findViewById(R.id.CreateDatabase);
        deleteDatabase=(Button)findViewById(R.id.DeleteDatabase);
        createPersonTable=(Button)findViewById(R.id.CreatePersonTable);
        deletePersonTable=(Button)findViewById(R.id.DeletePersonTable);
        insertUser=(Button)findViewById(R.id.InsertUser);
        updateUser=(Button)findViewById(R.id.UpdateUser);
        deleteUser=(Button)findViewById(R.id.DeleteUser);
        queryUser=(Button)findViewById(R.id.QueryUser);

        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.CreateDatabase(MainActivity.this);
                Toast.makeText(getApplicationContext(),"Create Database",Toast.LENGTH_LONG);
            }
        });

        deleteDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.DeleteDatabase(MainActivity.this);
                Toast.makeText(getApplicationContext(),"Delete Database",Toast.LENGTH_LONG);
            }
        });

        createPersonTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.Connect(MainActivity.this);
                task.CreateTable();
                task.Close();
            }
        });

        deletePersonTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.Connect(MainActivity.this);
                task.DropTable();
                task.Close();
            }
        });

        insertUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.Connect(MainActivity.this);
                SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat due=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                task.TaskID=df.format(new Date());
                task.TaskName="name";
                task.TaskContext="context";
                task.DueTime=due.format(new Date());
                task.Importance=5;
                task.TaskType=Task.DAILY;

                task.Insert();
                task.Close();
            }
        });

        queryUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.Connect(MainActivity.this);
                if (task.GetTaskByType(Task.DAILY,"Importance")==0) {
                    Log.i("TaskInfo",task.TaskID+" "+task.TaskName+" "+task.TaskContext);
                    while (task.MoveNext()==0) {
                        Log.i("TaskInfo",task.TaskID+" "+task.TaskName+" "+task.TaskContext);
                    }
                }
                else
                    Log.i("TaskInfo", "No Task");
                task.Close();
            }
        });

        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.Connect(MainActivity.this);
                task.UpdateTask(Task.TaskAttribute.Importance,"2");
                task.Close();
            }
        });

       deleteUser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
        	   
               task.Connect(MainActivity.this);
               task.DeleteTask(task.TaskID);
               task.Close();
           }
       });
    }
}
