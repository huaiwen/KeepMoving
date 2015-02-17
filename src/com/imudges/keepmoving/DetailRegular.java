package com.imudges.keepmoving;

import com.neo.database.Task;
import com.zhw.ui.LongTaskPageActivity;
import com.zhw.ui.ShortTaskPageActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailRegular extends Activity {
	private EditText taskName;
	private EditText taskContent;
	private Button btn; 
	
	private RatingBar ratBar;
	private TextView regular;
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
		setContentView(R.layout.activity_detail_regular);
		taskName = (EditText)findViewById(R.id.task_name_et);
		taskContent = (EditText)findViewById(R.id.editText1);
		btn = (Button)findViewById(R.id.DailyOKButton);
        ratBar = (RatingBar)findViewById(R.id.task_imp_st);
        regular = (TextView)findViewById(R.id.task_name_due);
        task = new Task();
        
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        content=intent.getStringExtra("content");
        importance=intent.getIntExtra("importance", 0);
        ID=intent.getStringExtra("ID");
        deadline=intent.getStringExtra("deadline");
        deadline=deadline.substring(0, 4)+"-"+deadline.substring(4, 6)+"-"+deadline.substring(6, 8)+" "+deadline.substring(8, 10)+":"+deadline.substring(10, 12)+"("+deadline.substring(14,16)+")";
        daily=intent.getStringExtra("daily");
        regular.setText(deadline);
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
					                intent.setClass(DetailRegular.this, LongTaskPageActivity.class);
					                startActivity(intent);
					
							}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_regular, menu);
		return true;
	}

}
