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
import android.widget.Toast;

public class Detaildaily extends Activity {
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
		setContentView(R.layout.activity_detaildaily);
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
    //    ratBar.setCursorVisible(false);      
     //   ratBar.setFocusable(false);         
     //   ratBar.setFocusableInTouchMode(false);
				btn.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								System.out.println(daily+"sdsdsdsdsdsdsdsdsd");
					
									Intent intent = new Intent();
					                intent.setClass(Detaildaily.this, UiMainActivity.class);
					                startActivity(intent);
					
							}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detaildaily, menu);
		return true;
	}

}
