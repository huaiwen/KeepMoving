package com.zhw.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.imudges.keepmoving.R;
import com.neo.database.Task;
import com.zhw.adapter.ListItemAdapter;


/**
 * Created with IntelliJ IDEA.
 * User: Neos
 * Date: 13-8-2
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
public class AddDailyTaskActivity extends Activity {

	/*************SKHon coding ****************/
	private EditText taskName;
	private EditText taskContent;
	private Button btn; 
    private Task task;
    public SimpleDateFormat formatter;
    public Date curDate;
    private RatingBar ratBar;
    
    private View dailyActivity;
    private ListItemAdapter adapter;
    private ListView listView;
    public UiMainActivity arrayList;

//  private ListAdapter UiMainListAdapter;
    
    
    /*5个ArrayList  动态数组*/
    public ArrayList<String> taskID_list=new ArrayList<String>();
    public ArrayList<String> taskName_list=new ArrayList<String>();
    public ArrayList<String> taskContent_list=new ArrayList<String>();
    public ArrayList<String> taskImportance_list=new ArrayList<String>();
    public ArrayList<String> taskDeadline_list=new ArrayList<String>();
    
    int flag;
    
    public UiMainActivity DBStor;
    /*************SKHon coding ****************/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_daily_task);
        
/*************SKHon coding ****************/
        
        taskName = (EditText)findViewById(R.id.task_name_et);
        taskContent = (EditText)findViewById(R.id.editText1);
        ratBar = (RatingBar)findViewById(R.id.task_imp_st);
        btn = (Button)findViewById(R.id.DailyOKButton);
        task = new Task();
        
        formatter  =  new SimpleDateFormat("yyyyMMddHHmmss");
        curDate=new Date(System.currentTimeMillis());
        flag=1;
        
        
        LayoutInflater layout=this.getLayoutInflater();
        View view=layout.inflate(R.layout.main_ui_page, null);
    //   Button b=(Button)view.findViewById(R.id.b); 
        listView = (ListView)view.findViewById (R.id.Uimain_list);
        
        
        
        
        
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 String newpwd = taskName.getText().toString().trim();
				 String content= taskContent.getText().toString().trim();
				 int bar;
				 bar = (int)ratBar.getRating();
			        if(newpwd.equals(""))
			        {
			        	Toast.makeText(AddDailyTaskActivity.this, "请填写任务名称！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(content.equals(""))
			        {
			        	Toast.makeText(AddDailyTaskActivity.this, "请填写任务内容！", Toast.LENGTH_LONG).show();
			        	return;
			        }
			        if(bar==0)
			        {
			        	Toast.makeText(AddDailyTaskActivity.this, "宝贝，没有任务重要度哦！", Toast.LENGTH_LONG).show();
			        	return;
			        }
				// TODO Auto-generated method stub
				task.Connect(AddDailyTaskActivity.this);
				if(task.Connect(AddDailyTaskActivity.this)==0)
					System.out.println("Connent OK");
				task.TaskID= formatter.format(curDate);     
				task.TaskName=taskName.getText().toString();
				task.TaskContext=taskContent.getText().toString();
				task.Importance=(int)ratBar.getRating();
				
				task.TaskType="DailyTask";
				task.DueTime=String.valueOf(Integer.valueOf(task.TaskID.substring(0, 8)).intValue()+1)+"000000";
                task.Insert();
                task.Close();
                
                
                
                
                
                
                
                
                
                
                Intent intent = new Intent();
                intent.setClass(AddDailyTaskActivity.this, UiMainActivity.class);
                startActivity(intent);
			}
		});
    }
}