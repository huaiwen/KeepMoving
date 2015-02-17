package com.zhw.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.imudges.keepmoving.R;
import com.neo.database.Task;


/**
 * Created with IntelliJ IDEA.
 * User: Wymon Zhang
 * Date: 13-7-28
 * Time: 下午10:18
 * To change this template use File | Settings | File Templates.
 */

		

public class AddShortTaskActivity extends Activity {

	private EditText taskName;
	private EditText taskContent;
	private Button btn; 
	private Task task;
	public SimpleDateFormat formatter;
	public Date curDate;
	private RatingBar ratBar;
	public static String curTime;
	public static String str;
	
	public static String show;
	public static int flag;
	 public ArrayList<String> taskID_list=new ArrayList<String>();
     public ArrayList<String> taskName_list=new ArrayList<String>();
     public ArrayList<String> taskContent_list=new ArrayList<String>();
     public ArrayList<String> taskImportance_list=new ArrayList<String>();
     public ArrayList<String> taskDeadline_list=new ArrayList<String>();
	
	
	
    private Button TimeText;
    private static String TimeStor; 

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_short_task);
        flag=0;
        taskName = (EditText)findViewById(R.id.task_name_et);
        taskContent = (EditText)findViewById(R.id.editText1);
        btn = (Button)findViewById(R.id.ShortOKButton);
        ratBar = (RatingBar)findViewById(R.id.task_imp_st);
        formatter  =  new SimpleDateFormat("yyyyMMddHHmmss");
        curDate=new Date(System.currentTimeMillis());
        task = new Task();       
        TimeText=(Button)findViewById(R.id.FixedTaskTime);
        
        
        
        
        

        TimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(AddShortTaskActivity.this);
                final View view1=LayoutInflater.from(AddShortTaskActivity.this).inflate(R.layout.date_time_picker,null);
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
				 System.out.println(curTime+"  curTime");
				 bar = (int)ratBar.getRating();
				  if(newpwd.equals(""))
			        {
			        	Toast.makeText(AddShortTaskActivity.this, "请填写任务名称！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(content.equals(""))
			        {
			        	Toast.makeText(AddShortTaskActivity.this, "请填写任务内容！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(bar==0)
			        {
			        	Toast.makeText(AddShortTaskActivity.this, "宝贝，没有任务重要度哦！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(flag==0)
			        {
			        	Toast.makeText(AddShortTaskActivity.this, "要填写你的任务截止时间哦！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(formatter.format(curDate).compareTo(curTime)>0)
			        {
			        	Toast.makeText(AddShortTaskActivity.this, "截止时间要大于当前时间哦！", Toast.LENGTH_LONG).show();
			        	return;
			        }
				// TODO Auto-generated method stub
				task.Connect(AddShortTaskActivity.this);
				if(task.Connect(AddShortTaskActivity.this)==0)
					System.out.println("Connent OK");
				task.TaskID= formatter.format(curDate);     
				task.TaskName=taskName.getText().toString();
				task.TaskContext=taskContent.getText().toString();
				task.Importance=(int)ratBar.getRating();
				
				task.TaskType="ShortTask";
				task.DueTime=curTime;
				System.out.println(curTime+"   fufuufuffu");
				/*
				taskID_list.add(task.TaskID);
				taskName_list.add(task.TaskName);
				taskContent_list.add(task.TaskContext);
				taskImportance_list.add(String.valueOf(task.Importance));
				taskDeadline_list.add(task.DueTime);	
				 */
				
			/*	for(int i=0;i<taskID_list.size();i++) {
	                   String alEach=(String)taskID_list.get(i);
	                   System.out.println(alEach+"wowowoow");
	            }*/
				
                task.Insert();
                task.Close();
                
                System.out.println(TimeStor+" timestor");
                
                
                
                
                
                Intent intent = new Intent();
                intent.setClass(AddShortTaskActivity.this, ShortTaskPageActivity.class);
                startActivity(intent);
			}
		});
    }

    class MyDatePickerDialog implements DatePickerDialog.OnDateSetListener {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear++;
            TimeText.setText(year+"-"+monthOfYear+"-"+dayOfMonth);        //鏇存柊鏃ユ湡鍊�
            TimeStor = String.valueOf(year)+"-"+String.valueOf(monthOfYear)+"-"+String.valueOf(dayOfMonth);
        }
    }
}