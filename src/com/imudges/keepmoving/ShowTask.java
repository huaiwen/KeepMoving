package com.imudges.keepmoving;

import com.neo.database.Task;
import com.zhw.ui.AddRegularTask;
import com.zhw.ui.ShortTaskPageActivity;
import com.zhw.ui.UiMainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class ShowTask extends Activity {
	private EditText taskName;
	private EditText taskContent;
	private Button btn; 
	
	private RatingBar ratBar;
	
	public static String name;
	public static String content;
	public static int importance;
	public static String ID;
	public static String deadline;
	public static String daily;
	private Task task;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_task);
		taskName = (EditText)findViewById(R.id.task_name_et);
		taskContent = (EditText)findViewById(R.id.editText1);
		btn = (Button)findViewById(R.id.DailyOKButton);
        ratBar = (RatingBar)findViewById(R.id.task_imp_st);
        task = new Task();
        
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        content=intent.getStringExtra("content");
        importance=intent.getIntExtra("importance", 0);
        ID=intent.getStringExtra("ID");
        deadline=intent.getStringExtra("deadline");
        daily=intent.getStringExtra("daily");
        taskName.setText(name);
        taskContent.setText(content);
        ratBar.setRating((float)importance);
				btn.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								String newpwd = taskName.getText().toString().trim();
								 String content= taskContent.getText().toString().trim();
								 int bar;
								 bar = (int)ratBar.getRating();
							        if(newpwd.equals(""))
							        {
							        	Toast.makeText(ShowTask.this, "请填写任务名称！", Toast.LENGTH_LONG).show();
							        	return;
							        }
							        if(content.equals(""))
							        {
							        	Toast.makeText(ShowTask.this, "请填写任务内容！", Toast.LENGTH_LONG).show();
							        	return;
							        }
							        if(bar==0)
							        {
							        	Toast.makeText(ShowTask.this, "宝贝，没有任务重要度哦！", Toast.LENGTH_LONG).show();
							        	return;
							        }
								
								
								task.Connect(ShowTask.this);
								task.TaskID=ID;
								task.TaskName=taskName.getText().toString();
								task.TaskContext=taskContent.getText().toString();
								task.Importance=(int)ratBar.getRating();
			
							    task.DueTime=deadline;
							    task.TaskType=daily;
				                task.Update();
							
								task.Close();
								
									Intent intent = new Intent();
					                intent.setClass(ShowTask.this, UiMainActivity.class);
					                startActivity(intent);
								
								
								
							}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_task, menu);
		return true;
	}

}
