package com.imudges.keepmoving;

import com.neo.database.Task;
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
import android.widget.TextView;

public class DetailShort extends Activity {
	private EditText taskName;
	private EditText taskContent;
	private Button btn; 
	private TextView taskDue;
	
	private RatingBar ratBar;
	
	public static String name;
	public static String content;
	public static int importance;
	public static String ID;
	public static String deadline;
	public static String daily;
	public static String due;
	private Task task;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_short);
		taskName = (EditText)findViewById(R.id.task_name_et);
		taskContent = (EditText)findViewById(R.id.editText1);
		btn = (Button)findViewById(R.id.DailyOKButton);
        ratBar = (RatingBar)findViewById(R.id.task_imp_st);
        taskDue = (TextView)findViewById(R.id.task_name_due);
        task = new Task();
        
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        content=intent.getStringExtra("content");
        importance=intent.getIntExtra("importance", 0);
        ID=intent.getStringExtra("ID");
        deadline=intent.getStringExtra("deadline");
        daily=intent.getStringExtra("daily");
        due = intent.getStringExtra("deadline");
        due=due.substring(0, 4)+"-"+due.substring(4, 6)+"-"+due.substring(6, 8)+"   "+due.substring(8, 10)+":"+due.substring(10, 12);
        taskName.setText(name);
        taskName.setCursorVisible(false);      
        taskName.setFocusable(false);         
        taskName.setFocusableInTouchMode(false);    
        taskContent.setText(content);
        taskContent.setCursorVisible(false);      
        taskContent.setFocusable(false);         
        taskContent.setFocusableInTouchMode(false);  
        ratBar.setRating((float)importance);
        ratBar.setClickable(false);
        ratBar.setFocusable(false);
        ratBar.setEnabled(false);
        taskDue.setText(due);
    //    ratBar.setCursorVisible(false);      
     //   ratBar.setFocusable(false);         
     //   ratBar.setFocusableInTouchMode(false);
				btn.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								System.out.println(daily+"sdsdsdsdsdsdsdsdsd");
					
									Intent intent = new Intent();
					                intent.setClass(DetailShort.this, ShortTaskPageActivity.class);
					                startActivity(intent);
					
							}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_short, menu);
		return true;
	}

}
