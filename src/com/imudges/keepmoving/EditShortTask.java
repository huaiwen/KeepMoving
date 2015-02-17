package com.imudges.keepmoving;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neo.database.Task;
import com.zhw.ui.AddRegularTask;
import com.zhw.ui.AddShortTaskActivity;
import com.zhw.ui.ShortTaskPageActivity;
import com.zhw.ui.UiMainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditShortTask extends Activity {
	private EditText taskName;
	private EditText taskContent;
	private Button btn; 
	private Button btnn;
	private RatingBar ratBar;
	
	public static String name;
	public static String content;
	public static int importance;
	public static String ID;
	public static String deadline;
	public static String daily;
	private Task task;
	
	private Button TimeText;
	public static String curTime;
	public static String str;
	public SimpleDateFormat formatter;
	public Date curDate;
	public static String show;
	public static int flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_short_task);
		flag=0;
		taskName = (EditText)findViewById(R.id.task_name_et);
		taskContent = (EditText)findViewById(R.id.editText1);
		btn = (Button)findViewById(R.id.DailyOKButton);
        ratBar = (RatingBar)findViewById(R.id.task_imp_st);
        btnn = (Button)findViewById(R.id.FixedTaskTime);
        task = new Task();
        
        
        formatter  =  new SimpleDateFormat("yyyyMMddHHmmss");
        curDate=new Date(System.currentTimeMillis());
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        content=intent.getStringExtra("content");
        importance=intent.getIntExtra("importance", 0);
        ID=intent.getStringExtra("ID");
        deadline=intent.getStringExtra("deadline");
        deadline=deadline.substring(0, 4)+"-"+deadline.substring(4, 6)+"-"+deadline.substring(6, 8)+"  "+deadline.substring(8, 10)+":"+deadline.substring(10, 12);
        daily=intent.getStringExtra("daily");
        taskName.setText(name);
        taskContent.setText(content);
        ratBar.setRating((float)importance);
        btnn.setText(deadline);
        
        
      TimeText=(Button)findViewById(R.id.FixedTaskTime);
        
        
        
        
        

        TimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(EditShortTask.this);
                final View view1=LayoutInflater.from(EditShortTask.this).inflate(R.layout.date_time_picker,null);
                alertDialog.setView(view1);
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatePicker datePicker=(DatePicker)view1.findViewById(R.id.mydate);
                        TimePicker timePicker=(TimePicker)view1.findViewById(R.id.mytime);
                        
                        if(timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()>=10)
                            str=String.valueOf(datePicker.getYear())+"-"+String.valueOf(datePicker.getMonth()+1)+"-"+String.valueOf(datePicker.getDayOfMonth())+" "+String.valueOf(timePicker.getCurrentHour())+":"+String.valueOf(timePicker.getCurrentMinute());
                        if(timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()<10)
                        	str=String.valueOf(datePicker.getYear())+"-"+String.valueOf(datePicker.getMonth()+1)+"-"+String.valueOf(datePicker.getDayOfMonth())+" "+String.valueOf(timePicker.getCurrentHour())+":"+"0"+String.valueOf(timePicker.getCurrentMinute());
                        if(timePicker.getCurrentHour()<10&&timePicker.getCurrentMinute()>=10)
                        	str=String.valueOf(datePicker.getYear())+"-"+String.valueOf(datePicker.getMonth()+1)+"-"+String.valueOf(datePicker.getDayOfMonth())+" "+"0"+String.valueOf(timePicker.getCurrentHour())+":"+String.valueOf(timePicker.getCurrentMinute());
                        if(timePicker.getCurrentHour()<10&&timePicker.getCurrentMinute()<10)
                        	str=String.valueOf(datePicker.getYear())+"-"+String.valueOf(datePicker.getMonth()+1)+"-"+String.valueOf(datePicker.getDayOfMonth())+" "+"0"+String.valueOf(timePicker.getCurrentHour())+":"+"0"+String.valueOf(timePicker.getCurrentMinute());
                        
                        
                        if(datePicker.getMonth()>=9&&datePicker.getDayOfMonth()<10&&timePicker.getCurrentHour()<10&&timePicker.getCurrentMinute()<10)
                            curTime=String.valueOf(datePicker.getYear())+String.valueOf(datePicker.getMonth()+1)+"0"+String.valueOf(datePicker.getDayOfMonth())+"0"+String.valueOf(timePicker.getCurrentHour())+"0"+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else if(datePicker.getMonth()<9&&datePicker.getDayOfMonth()>=10&&timePicker.getCurrentHour()<10&&timePicker.getCurrentMinute()<10)
                        	curTime=String.valueOf(datePicker.getYear())+"0"+String.valueOf(datePicker.getMonth()+1)+String.valueOf(datePicker.getDayOfMonth())+"0"+String.valueOf(timePicker.getCurrentHour())+"0"+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else if(datePicker.getMonth()<9&&datePicker.getDayOfMonth()<10&&timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()<10)
                        	curTime=String.valueOf(datePicker.getYear())+"0"+String.valueOf(datePicker.getMonth()+1)+"0"+String.valueOf(datePicker.getDayOfMonth())+String.valueOf(timePicker.getCurrentHour())+"0"+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else if(datePicker.getMonth()<9&&datePicker.getDayOfMonth()<10&&timePicker.getCurrentHour()<10&&timePicker.getCurrentMinute()>=10)
                        	curTime=String.valueOf(datePicker.getYear())+"0"+String.valueOf(datePicker.getMonth()+1)+"0"+String.valueOf(datePicker.getDayOfMonth())+"0"+String.valueOf(timePicker.getCurrentHour())+String.valueOf(timePicker.getCurrentMinute())+"00";
                        
                        
                        else if(datePicker.getMonth()>=9&&datePicker.getDayOfMonth()>=10&&timePicker.getCurrentHour()<10&&timePicker.getCurrentMinute()<10)
                        	curTime=String.valueOf(datePicker.getYear())+String.valueOf(datePicker.getMonth()+1)+String.valueOf(datePicker.getDayOfMonth())+"0"+String.valueOf(timePicker.getCurrentHour())+"0"+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else if(datePicker.getMonth()>=9&&datePicker.getDayOfMonth()<10&&timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()<10)
                        	curTime=String.valueOf(datePicker.getYear())+String.valueOf(datePicker.getMonth()+1)+"0"+String.valueOf(datePicker.getDayOfMonth())+String.valueOf(timePicker.getCurrentHour())+"0"+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else if(datePicker.getMonth()>=9&&datePicker.getDayOfMonth()<10&&timePicker.getCurrentHour()<10&&timePicker.getCurrentMinute()>=10)
                        	curTime=String.valueOf(datePicker.getYear())+String.valueOf(datePicker.getMonth()+1)+"0"+String.valueOf(datePicker.getDayOfMonth())+"0"+String.valueOf(timePicker.getCurrentHour())+String.valueOf(timePicker.getCurrentMinute())+"00";
                        
                        
                        else if(datePicker.getMonth()<9&&datePicker.getDayOfMonth()>=10&&timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()<10)
                        	curTime=String.valueOf(datePicker.getYear())+"0"+String.valueOf(datePicker.getMonth()+1)+String.valueOf(datePicker.getDayOfMonth())+String.valueOf(timePicker.getCurrentHour())+"0"+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else if(datePicker.getMonth()<9&&datePicker.getDayOfMonth()>=10&&timePicker.getCurrentHour()<10&&timePicker.getCurrentMinute()>=10)
                        	curTime=String.valueOf(datePicker.getYear())+"0"+String.valueOf(datePicker.getMonth()+1)+String.valueOf(datePicker.getDayOfMonth())+"0"+String.valueOf(timePicker.getCurrentHour())+String.valueOf(timePicker.getCurrentMinute())+"00";
                        
                        else if(datePicker.getMonth()<9&&datePicker.getDayOfMonth()<10&&timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()>=10)
                        	curTime=String.valueOf(datePicker.getYear())+"0"+String.valueOf(datePicker.getMonth()+1)+"0"+String.valueOf(datePicker.getDayOfMonth())+String.valueOf(timePicker.getCurrentHour())+String.valueOf(timePicker.getCurrentMinute())+"00";
                        
                        else if(datePicker.getMonth()>=9&&datePicker.getDayOfMonth()>=10&&timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()<10)
                        	curTime=String.valueOf(datePicker.getYear())+String.valueOf(datePicker.getMonth()+1)+String.valueOf(datePicker.getDayOfMonth())+String.valueOf(timePicker.getCurrentHour())+"0"+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else if(datePicker.getMonth()>=9&&datePicker.getDayOfMonth()>=10&&timePicker.getCurrentHour()<10&&timePicker.getCurrentMinute()>=10)
                        	curTime=String.valueOf(datePicker.getYear())+String.valueOf(datePicker.getMonth()+1)+String.valueOf(datePicker.getDayOfMonth())+"0"+String.valueOf(timePicker.getCurrentHour())+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else if(datePicker.getMonth()<9&&datePicker.getDayOfMonth()>=10&&timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()>=10)
                        	curTime=String.valueOf(datePicker.getYear())+"0"+String.valueOf(datePicker.getMonth()+1)+String.valueOf(datePicker.getDayOfMonth())+String.valueOf(timePicker.getCurrentHour())+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else if(datePicker.getMonth()>=9&&datePicker.getDayOfMonth()<10&&timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()>=10)
                        	curTime=String.valueOf(datePicker.getYear())+String.valueOf(datePicker.getMonth()+1)+"0"+String.valueOf(datePicker.getDayOfMonth())+String.valueOf(timePicker.getCurrentHour())+String.valueOf(timePicker.getCurrentMinute())+"00";
                        
                        else if(datePicker.getMonth()>=9&&datePicker.getDayOfMonth()>=10&&timePicker.getCurrentHour()>=10&&timePicker.getCurrentMinute()>=10)
                        	curTime=String.valueOf(datePicker.getYear())+String.valueOf(datePicker.getMonth()+1)+String.valueOf(datePicker.getDayOfMonth())+String.valueOf(timePicker.getCurrentHour())+String.valueOf(timePicker.getCurrentMinute())+"00";
                        else
                        	curTime=String.valueOf(datePicker.getYear())+"0"+String.valueOf(datePicker.getMonth()+1)+"0"+String.valueOf(datePicker.getDayOfMonth())+"0"+String.valueOf(timePicker.getCurrentHour())+"0"+String.valueOf(timePicker.getCurrentMinute())+"00";
                 //       show = (Integer.valueOf(str.substring(4,6))++).toString();
                        TimeText.setText(str);
                        
                        flag=1;
                        System.out.println(curTime+"  curTime22");
                    }
                });
                alertDialog.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
            }
        });

        
        
        
				btn.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								String newpwd = taskName.getText().toString().trim();
								 String content= taskContent.getText().toString().trim();
								 int bar;
								 bar = (int)ratBar.getRating();
							        if(newpwd.equals(""))
							        {
							        	Toast.makeText(EditShortTask.this, "请填写任务名称！", Toast.LENGTH_LONG).show();
							        	return;
							        }
							        if(content.equals(""))
							        {
							        	Toast.makeText(EditShortTask.this, "请填写任务内容！", Toast.LENGTH_LONG).show();
							        	return;
							        }
							        if(bar==0)
							        {
							        	Toast.makeText(EditShortTask.this, "宝贝，没有任务重要度哦！", Toast.LENGTH_LONG).show();
							        	return;
							        }
							        if(formatter.format(curDate).compareTo(curTime)>0)
							        {
							        	Toast.makeText(EditShortTask.this, "截止时间要大于当前时间哦！", Toast.LENGTH_LONG).show();
							        	return;
							        }
								
								task.Connect(EditShortTask.this);
								task.TaskID=ID;
								task.TaskName=taskName.getText().toString();
								task.TaskContext=taskContent.getText().toString();
								task.Importance=(int)ratBar.getRating();
			
							    task.DueTime=curTime;
							    task.TaskType=daily;
				                task.Update();
							
								task.Close();
								
								
									Intent intent = new Intent();
					                intent.setClass(EditShortTask.this, ShortTaskPageActivity.class);
					                startActivity(intent);
								
								
							}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_short_task, menu);
		return true;
	}

}
