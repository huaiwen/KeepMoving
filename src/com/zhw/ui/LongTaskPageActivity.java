package com.zhw.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

import com.imudges.keepmoving.DetailRegular;
import com.imudges.keepmoving.DetailShort;
import com.imudges.keepmoving.EditRegular;
import com.imudges.keepmoving.EditShortTask;
import com.imudges.keepmoving.R;
import com.neo.database.Task;
import com.zhw.adapter.ListItemAdapter;
import com.zhw.adapter.MenuListAdapter;
import com.zhw.callback.SizeCallBackForMenu;
import com.zhw.ui.ui.MenuHorizontalScrollView;
import com.zhw.util.DisplayManager;


public class LongTaskPageActivity extends Activity {
    private LinearLayout uimain, shorttask, longtask, chengjiu;
    TextView toptv;
	private MenuHorizontalScrollView scrollView;
	private ListView menuList;
	private View longTask;
	private Button menuBtn;
	private MenuListAdapter menuListAdapter;
    private long   firstTime=0;
    private ListView long_task_List;
    private ListItemAdapter long_ListAdapter;
    private ImageButton add_new_bt,sequence_menu;
    
    public static int count ;
    private Task task;
    /*5个ArrayList  动态数组*/
    public static ArrayList<String> taskID_list=new ArrayList<String>();
    public static ArrayList<String> taskName_list=new ArrayList<String>();
    public static ArrayList<String> taskContent_list=new ArrayList<String>();
    public static ArrayList<Integer> taskImportance_list=new ArrayList<Integer>();
    public static ArrayList<String> taskDeadline_list=new ArrayList<String>();
    public static String show;
    
    public static 	AlarmManager alarmManager;
    public static PendingIntent pendingIntent;
    public static Date curDate;
    public static SimpleDateFormat formatter;
    public static String cur;
    public static String end;
    public static Date beginTime;
    public static Date endTime;
    public static long l;
    public static String temp;
    public static String regular;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		LayoutInflater inflater = LayoutInflater.from(this);
		setContentView(inflater.inflate(R.layout.menu_scroll_view, null));
		count=1;
		task = new Task();
		this.scrollView = (MenuHorizontalScrollView)findViewById(R.id.scrollView);

		this.menuList = (ListView)findViewById(R.id.menuList);
		
		this.longTask = inflater.inflate(R.layout.longtaskpage, null);
		this.long_task_List = (ListView) this.longTask.findViewById(R.id.long_task_list);
		
		 
		 formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        curDate=new Date(System.currentTimeMillis());
	        cur = formatter.format(curDate);
	        temp=cur;
	        cur = cur.substring(0, 4)+"-"+cur.substring(4, 6)+"-"+cur.substring(6, 8)+" "+cur.substring(8, 10)+":"+cur.substring(10, 12);
	        try {
				beginTime = StringToDate(cur);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/******************************************************************************************************/      
        task.Connect(LongTaskPageActivity.this);
        //       task.GetTaskByType("DailyTask","Importance");
               
            
               
     
               
               if(task.GetTaskByType("RegularTask","TaskID")==1)
               {
                   	System.out.println("nihao");
               }
               else {
              
               Context ctx = LongTaskPageActivity.this;       
               SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

               	 if(sp.getInt("INT_KEY_Regular", 1)==0)
               	  		task.GetTaskByType("RegularTask","Importance");
               	 if(sp.getInt("INT_KEY_Regular", 1)==1)
               		 	task.GetTaskByType("RegularTask","TaskID");
               	 alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE); 
   		            	do
   		                {
   		            		regular = task.DueTime.substring(14,16);
   		            		end=task.DueTime.substring(0, 4)+"-"+task.DueTime.substring(4, 6)+"-"+task.DueTime.substring(6, 8)+" "+task.DueTime.substring(8, 10)+":"+task.DueTime.substring(10, 12);
   		            		System.out.println(end+"    endendnend");
   		            		try {
								endTime=StringToDate(end);
							} catch (java.text.ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
   		            		System.out.println(beginTime+"  beginTime  "+task.Importance);
   		            		System.out.println(endTime+"  endtimetime "+task.Importance );
   		            		l=endTime.getTime()-beginTime.getTime();
   		            		
   		            		Intent intent = new Intent(this, Receiver.class); 
   		         			intent.setAction("com.alarm.action_alarm_on");
   		         			pendingIntent = PendingIntent.getBroadcast(this,count, intent, 0);
   		         			if(regular.compareTo("每日")==0)
   		         		        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + l, 86400000, pendingIntent); 
   		         		    if(regular.compareTo("每月")==0)
		         		        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + l, 86400000*30, pendingIntent); 
   		         		    if(regular.compareTo("每年")==0)
		         		        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + l, 86400000*30*12, pendingIntent); 
   		         		    count++;
   		         		
   		         		
   		         		
   		            		System.out.println("hello oworld");	
   		            		show=task.DueTime.substring(0, 4)+"-"+task.DueTime.substring(4, 6)+"-"+task.DueTime.substring(6, 8)+" "+task.DueTime.substring(8, 10)+":"+task.DueTime.substring(10, 12)+"\n"+task.DueTime.substring(14,16);
   		            		taskID_list.add(show);
   							taskName_list.add(task.TaskName);
   							taskContent_list.add(task.TaskContext);
   							taskImportance_list.add(task.Importance);
   							System.out.println();
   							taskDeadline_list.add(task.DueTime);
   		                }while(task.MoveNext()==0);
   		         //   	Toast toast = Toast.makeText(UiMainActivity.this, task.TaskName, Toast.LENGTH_SHORT); 
   		         //   	toast.show();
   		            	
   		            	
   		                this.long_ListAdapter = new ListItemAdapter(this,taskID_list,taskName_list,taskContent_list,taskImportance_list,taskDeadline_list,3);
   		                this.long_task_List.setAdapter(long_ListAdapter);
   				        
   		          //  	this.short_ListAdapter = new ListItemAdapter(this,taskID_list,taskName_list,taskContent_list,taskImportance_list,taskDeadline_list,2);
   		          //      this.short_task_List.setAdapter(short_ListAdapter);   
   		       
   		                taskID_list.clear();
   		                taskName_list.clear();
   		                taskContent_list.clear();
   		                taskImportance_list.clear();
   		                taskDeadline_list.clear();
   		      //          UiMainListAdapter.notifyDataSetChanged();
   		             //   System.out.println();
               }
               
               task.Close();
        
        
  //      this.short_ListAdapter = new ListItemAdapter(this);
   //       this.short_task_List.setAdapter(short_ListAdapter);
        
  /******************************************************************************************************/ 
               
        this.add_new_bt = (ImageButton)this.longTask.findViewById(R.id.top_bar_add);
        this.add_new_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                Intent it = new Intent(LongTaskPageActivity.this, AddRegularTask.class);
                startActivity(it);

            }
        });
        this.sequence_menu = (ImageButton) this.longTask.findViewById(R.id.sequence_menu);
        this.sequence_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 显示 popupWindow
                PopupWindow popupWindow = makePopupWindow(LongTaskPageActivity.this);
                int[] xy = new int[2];
                sequence_menu.getLocationOnScreen(xy);
                popupWindow.showAtLocation(sequence_menu, Gravity.RIGHT | Gravity.TOP, -xy[0] / 2, xy[1] * 5 / 4 + sequence_menu.getWidth());
                //  popupWindow.showAsDropDown(sequence_menu,0, 0);
            }
        });


       

        this.long_task_List.setItemsCanFocus(false);
        this.long_task_List.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       /* this.long_task_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListItemAdapter.ViewHolder vHollder = (ListItemAdapter.ViewHolder) view.getTag();
//在每次获取点击的item时将对于的checkbox状态改变，同时修改map的值。
                vHollder.cBox.toggle();
                ListItemAdapter.isSelected.put(position, vHollder.cBox.isChecked());
            }
        });*/
        this.long_task_List.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                //To change body of implemented methods use File | Settings | File Templates.

                MenuInflater inflater = getMenuInflater();
                contextMenu.setHeaderTitle("任务操作").setHeaderIcon(R.drawable.userlogo);
                inflater.inflate(R.menu.content_menu, contextMenu);


            }
        });
		this.menuBtn = (Button)this.longTask.findViewById(R.id.zhw_menuBtn);
		this.menuBtn.setOnClickListener(onClickListener);
        this.menuListAdapter = new MenuListAdapter(this, 3,this.menuBtn);
        this.menuList.setAdapter(menuListAdapter);
		View leftView = new View(this);
		leftView.setBackgroundColor(Color.TRANSPARENT);
		final View[] children = new View[]{leftView, longTask};
		this.scrollView.initViews(children, new SizeCallBackForMenu(this.menuBtn), this.menuList);
		this.scrollView.setMenuBtn(this.menuBtn);
    //    Init();
	}

   /* public void Init() {
        uimain = (LinearLayout) this.longTask.findViewById(R.id.home);
        uimain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LongTaskPageActivity.this, UiMainActivity.class);
                LongTaskPageActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);

            }
        });


        shorttask = (LinearLayout) this.longTask.findViewById(R.id.channel);
        shorttask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LongTaskPageActivity.this, ShortTaskPageActivity.class);
                LongTaskPageActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);

            }
        });


        longtask = (LinearLayout) this.longTask.findViewById(R.id.fav);
        TextView favtv = (TextView) this.longTask.findViewById(R.id.favtv);
//        favtv.setBackgroundResource(R.drawable.selected);
        longtask.setBackgroundResource(R.drawable.tab_two_highlight);
     *//*   toptv = (TextView) this.longTask.findViewById(R.id.centerContent);*//*



        chengjiu = (LinearLayout)this.longTask. findViewById(R.id.search);
        chengjiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(LongTaskPageActivity.this, ChengJiuPageActivity.class);
                LongTaskPageActivity.this.startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);



            }
        });
    }*/
    // 创建一个包含自定义view的PopupWindow
    private PopupWindow makePopupWindow(Context cx) {
        PopupWindow window;
        window = new PopupWindow(cx);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow, null);
        window.setContentView(contentView);
        ListView listView = new ListView(this);
        listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        String[] strs = {"重要程度", "添加时间", "紧急程度"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.popwindow_item, strs);
        listView.setAdapter(adapter);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.addView(listView);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        window.setContentView(linearLayout);
        window.setWidth(DisplayManager.dip2px(this, 160));
        window.setHeight(DisplayManager.dip2px(this, 158));
        
        OnItemClickListener clickListener = new OnItemClickListener() {

    		@Override
    		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
    				long arg3) {
    			// TODO Auto-generated method stub
    		   if(arg2 ==0){
    			   Context ctx = LongTaskPageActivity.this; 
    			   SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
    		        Editor editor = sp.edit();
    			    editor.putInt("INT_KEY_Regular", 0);
    			     //   editor.putBoolean("BOOLEAN_KEY", true);
    			    editor.commit();
    		  //      System.out.println(sp.getInt("INT_KEY_SHORT", 1)+"  fufufufufufuf");
    		        
    		        finish();  
    		        Intent intent = new Intent(LongTaskPageActivity.this, LongTaskPageActivity.class);  
    		        startActivity(intent);
    			 
    		   }
    		   if(arg2 == 1)
    		   {
    			   Context ctx = LongTaskPageActivity.this; 
    			   SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
    		        Editor editor = sp.edit();
    			    editor.putInt("INT_KEY_Regular", 1);
    			  
    			    editor.commit();
    			    
    			    finish();  
    		        Intent intent = new Intent(LongTaskPageActivity.this, LongTaskPageActivity.class);  
    		        startActivity(intent);  
    			 //   System.out.println(sp.getInt("INT_KEY_SHORT", 1)+"  fufufufufufuf");
    		   }
    		   if(arg2==2){
    			   Context ctx = LongTaskPageActivity.this; 
    			   SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
    		        Editor editor = sp.edit();
    			    editor.putInt("INT_KEY_Regular", 2);
    			     //   editor.putBoolean("BOOLEAN_KEY", true);
    			    editor.commit();
    			    
    			    finish();  
    		        Intent intent = new Intent(LongTaskPageActivity.this, LongTaskPageActivity.class);  
    		        startActivity(intent);  
    		        
    		//	    System.out.println(sp.getInt("INT_KEY_SHORT", 1)+"  fufufufufufuf");
    		   }
    		}
    	};
       
    //	UiMainList.setOnItemClickListener(clickListener);
    	
    	listView.setOnItemClickListener(clickListener);
        
        
        // 设置PopupWindow外部区域是否可触摸
        window.setFocusable(true); //设置PopupWindow可获得焦点
        window.setTouchable(true); //设置PopupWindow可触摸
        window.setOutsideTouchable(true); //设置非PopupWindow区域可触摸
        return window;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        {
                long secondTime = System.currentTimeMillis();

                if (secondTime - firstTime > 1000) {//如果两次按键时间间隔大于1000毫秒，则不退出
                    Toast.makeText(LongTaskPageActivity.this, "再按一次退出程序...", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//Ui更新firstTime
                    return true;
                } else {
                   /* System.exit(0);//否则退出程序*/
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        }
        return super.onKeyUp(keyCode, event);
    }
	
	private OnClickListener onClickListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			scrollView.clickMenuBtn();
		}
	};
	
	
	 public static Date StringToDate(String dateStr) throws java.text.ParseException{
			DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date=null;
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
	 @Override
	    public boolean onContextItemSelected(MenuItem item) {
	        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
	                .getMenuInfo(); // info包含了上下文菜单每一项的信息,比如info.position是上下文菜单项的位置
	    
	        
	        
	         ArrayList<String> taskID_list_temp=new ArrayList<String>();
	         ArrayList<String> taskName_list_temp=new ArrayList<String>();
	         ArrayList<String> taskContent_list_temp= new ArrayList<String>();
	         ArrayList<Integer> taskImportance_list_temp = new ArrayList<Integer>();
	         ArrayList<String> taskDeadlie_list_temp = new ArrayList<String>();
	    //    String  tempID=new String();
	        switch (item.getItemId()) {
	        case R.id.content_menu_change:
	           
	        //	UiMainListAdapter.getItem(1);
	        	
	        	alarmManager.cancel(pendingIntent);
	        	  task.Connect(LongTaskPageActivity.this);
	        	  if(task.GetTaskByType("RegularTask","TaskID")==1)
	        	  {
	        	       System.out.println("nihao");
	        	  }
	        	  else {  
	        	            Context ctx = LongTaskPageActivity.this;       
	        	            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

	        	            	 if(sp.getInt("INT_KEY_Regular", 1)==0)
	        	            	  		task.GetTaskByType("RegularTask","Importance");
	        	            	 if(sp.getInt("INT_KEY_Regular", 1)==1)
	        	            		 	task.GetTaskByType("RegularTask","TaskID");

	        			            	do
	        			                {
	        			            		System.out.println("hello oworld");	  
	        			            		taskID_list_temp.add(task.TaskID);
	        			            		taskName_list_temp.add(task.TaskName);
	        			            		taskContent_list_temp.add(task.TaskContext);
	        			            		taskImportance_list_temp.add(task.Importance);
	        								System.out.println();
	        								taskDeadlie_list_temp.add(task.DueTime);
	        			                }while(task.MoveNext()==0);     			    
	        	            }
	        	            
	        	            task.Close();
	        	
	        	   System.out.println(taskID_list_temp.get(long_ListAdapter.getCount()-info.position-1)+"wowowoowwwSHSHSHSHSHS");
	        	
	   //         	Toast.makeText(this,"点卡第一个的修改", Toast.LENGTH_SHORT).show();
	    //        	System.out.println(taskID_list_temp+"  wowowoowowo");

	                
	     //           if(info.position ==0)
	      //      	{}
	                Intent intent = new Intent();
	                intent.setClass(LongTaskPageActivity.this, EditRegular.class);
	                intent.putExtra("name", taskName_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	                intent.putExtra("content", taskContent_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	                intent.putExtra("importance", taskImportance_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	                intent.putExtra("ID", taskID_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	                intent.putExtra("deadline", taskDeadlie_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	                intent.putExtra("daily", "RegularTask");
	                taskID_list_temp.clear();
	                taskName_list_temp.clear();
	                taskContent_list_temp.clear();
	                taskImportance_list_temp.clear();
	                taskDeadlie_list_temp.clear();
	                
	                
	                
	                startActivity(intent);
	                
	               
	        	
	        	System.out.println("更改。。。。。。"+long_ListAdapter.getCount());
	            return true;
	        case R.id.content_menu_delete:
	            // do something
	        	alarmManager.cancel(pendingIntent);
	        	task.Connect(LongTaskPageActivity.this);
	      	  if(task.GetTaskByType("RegularTask","TaskID")==1)
	      	  {
	      	       System.out.println("nihao");
	      	  }
	      	  else {  
	      	            Context ctx = LongTaskPageActivity.this;       
	      	            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

	      	            	 if(sp.getInt("INT_KEY_Regular", 1)==0)
	      	            	  		task.GetTaskByType("RegularTask","Importance");
	      	            	 if(sp.getInt("INT_KEY_Regular", 1)==1)
	      	            		 	task.GetTaskByType("RegularTask","TaskID");

	      			            	do
	      			                {
	      			            		System.out.println("hello oworld");	  
	      			            		taskID_list_temp.add(task.TaskID);
	      			            		taskName_list_temp.add(task.TaskName);
	      			            		taskContent_list_temp.add(task.TaskContext);
	      			            		taskImportance_list_temp.add(task.Importance);
	      								System.out.println();
	      								taskDeadlie_list_temp.add(task.DueTime);
	      			                }while(task.MoveNext()==0);     			 
	      			            	task.DeleteTask(taskID_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	      	            }
	      	            
	      	            task.Close();
	      	          finish();  
	   		        Intent intent1 = new Intent(LongTaskPageActivity.this, LongTaskPageActivity.class);  
	   		        startActivity(intent1);
	      	
	      	   System.out.println(taskID_list_temp.get(long_ListAdapter.getCount()-info.position-1)+"wowowoowww");
	      	
	 
	            
	              
	             
	      	
	         	System.out.println("更改。。。。。。"+long_ListAdapter.getCount());
	        	
	        	
	        	
	        	System.out.println("删除。。。。。。");
	            return true;
	        case R.id.content_menu_detail:
	            // do something
	        	 task.Connect(LongTaskPageActivity.this);
	       	  if(task.GetTaskByType("RegularTask","TaskID")==1)
	       	  {
	       	       System.out.println("nihao");
	       	  }
	       	  else {  
	       	            Context ctx = LongTaskPageActivity.this;       
	       	            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

	       	            	 if(sp.getInt("INT_KEY_Regular", 1)==0)
	       	            	  		task.GetTaskByType("RegularTask","Importance");
	       	            	 if(sp.getInt("INT_KEY_Regular", 1)==1)
	       	            		 	task.GetTaskByType("RegularTask","TaskID");

	       			            	do
	       			                {
	       			            		System.out.println("hello oworld");	  
	       			            		taskID_list_temp.add(task.TaskID);
	       			            		taskName_list_temp.add(task.TaskName);
	       			            		taskContent_list_temp.add(task.TaskContext);
	       			            		taskImportance_list_temp.add(task.Importance);
	       								System.out.println();
	       								taskDeadlie_list_temp.add(task.DueTime);
	       			                }while(task.MoveNext()==0);     			    
	       	            }
	       	            
	       	            task.Close();
	       	       
	       	
	       	   System.out.println(taskID_list_temp.get(long_ListAdapter.getCount()-info.position-1)+"wowowoowww");
	       	
	  //         	Toast.makeText(this,"点卡第一个的修改", Toast.LENGTH_SHORT).show();
	   //        	System.out.println(taskID_list_temp+"  wowowoowowo");

	               
	    //           if(info.position ==0)
	     //      	{}
	               Intent intent2 = new Intent();
	               intent2.setClass(LongTaskPageActivity.this, DetailRegular.class);
	               intent2.putExtra("name", taskName_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	               intent2.putExtra("content", taskContent_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	               intent2.putExtra("importance", taskImportance_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	               intent2.putExtra("ID", taskID_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	               intent2.putExtra("deadline", taskDeadlie_list_temp.get(long_ListAdapter.getCount()-info.position-1));
	               intent2.putExtra("daily", "RegularTask");
	               taskID_list_temp.clear();
	               taskName_list_temp.clear();
	               taskContent_list_temp.clear();
	               taskImportance_list_temp.clear();
	               taskDeadlie_list_temp.clear();
	               
	               
	               
	               startActivity(intent2);
	        	
	        	
	        	System.out.println("详细。。。。。。");
	            return true;
	        default:
	            break;
	            
	            
	        }
	        return super.onContextItemSelected(item);
	    }
}
