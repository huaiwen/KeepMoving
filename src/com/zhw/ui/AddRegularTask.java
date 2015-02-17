package com.zhw.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.imudges.keepmoving.R;
import com.neo.database.Task;

/**
 * Created with IntelliJ IDEA.
 * User: Neos
 * Date: 13-8-2
 * Time: 下午3:28
 * To change this template use File | Settings | File Templates.
 */
public class AddRegularTask extends Activity {

	private EditText taskName;
	private EditText taskContent;
	private Button btn; 
	private Task task;
	public SimpleDateFormat formatter;
	public Date curDate;
	private RatingBar ratBar;
	
	 public ArrayList<String> taskID_list=new ArrayList<String>();
     public ArrayList<String> taskName_list=new ArrayList<String>();
     public ArrayList<String> taskContent_list=new ArrayList<String>();
     public ArrayList<String> taskImportance_list=new ArrayList<String>();
     public ArrayList<String> taskDeadline_list=new ArrayList<String>();
	
	
    private static Spinner taskFrequence;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private Button TimeText;
    
    public static String curTime;
    public static String str;
    public static int flag;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.add_regular_task);
        
        
        taskName = (EditText)findViewById(R.id.task_name_et);
        taskContent = (EditText)findViewById(R.id.editText1);
        
        btn = (Button)findViewById(R.id.FrequenceOKButton);
        ratBar = (RatingBar)findViewById(R.id.task_imp_st);
        
        
        formatter  =  new SimpleDateFormat("yyyyMMddHHmmss");
        curDate=new Date(System.currentTimeMillis());
        task = new Task();  
        TimeText=(Button)findViewById(R.id.RegularSelectDateTime);
        
        list.add("每天");
        list.add("每周");
        list.add("每月");
        list.add("每年");
        taskFrequence=(Spinner)findViewById(R.id.TaskFrequnce);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskFrequence.setAdapter(adapter);
        
        
        
        TimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(AddRegularTask.this);
                final View view1= LayoutInflater.from(AddRegularTask.this).inflate(R.layout.date_time_picker,null);
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
				 System.out.println(taskFrequence.getSelectedItem().toString()+"   sdfsdfsdf");
				 bar = (int)ratBar.getRating();
			        if(newpwd.equals(""))
			        {
			        	Toast.makeText(AddRegularTask.this, "请填写任务名称！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(content.equals(""))
			        {
			        	Toast.makeText(AddRegularTask.this, "请填写任务内容！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(bar==0)
			        {
			        	Toast.makeText(AddRegularTask.this, "宝贝，没有任务重要度哦！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(flag==0)
			        {
			        	Toast.makeText(AddRegularTask.this, "要填写你的任务截止时间哦！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(formatter.format(curDate).compareTo(curTime)>0)
			        {
			        	Toast.makeText(AddRegularTask.this, "截止时间要大于当前时间哦！", Toast.LENGTH_LONG).show();
			        	return;
			        }
				// TODO Auto-generated method stub
				task.Connect(AddRegularTask.this);
				if(task.Connect(AddRegularTask.this)==0)
					System.out.println("Connent OK");
				task.TaskID= formatter.format(curDate);     
				task.TaskName=taskName.getText().toString();
				task.TaskContext=taskContent.getText().toString();
				task.Importance=(int)ratBar.getRating();
				
				task.TaskType="RegularTask";
				task.DueTime=curTime+taskFrequence.getSelectedItem().toString();
			    
				
                task.Insert();
                task.Close();
                
                
                
                
                
                
                
            
                Intent intent = new Intent();
                intent.setClass(AddRegularTask.this, LongTaskPageActivity.class);
                startActivity(intent);
			}
		});
        
        
    }
}