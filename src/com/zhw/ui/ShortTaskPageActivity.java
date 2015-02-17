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
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.ui.Receiver;
import com.imudges.keepmoving.DetailShort;
import com.imudges.keepmoving.EditShortTask;
import com.imudges.keepmoving.R;
import com.neo.database.Task;
import com.zhw.adapter.ListItemAdapter;
import com.zhw.adapter.MenuListAdapter;
import com.zhw.callback.SizeCallBackForMenu;
import com.zhw.ui.ui.MenuHorizontalScrollView;
import com.zhw.util.DisplayManager;


public class ShortTaskPageActivity extends Activity {
    private LinearLayout uimain, shorttask, longtask, chengjiu;
    TextView toptv;
    private MenuHorizontalScrollView scrollView;
    private ListView menuList;
    private View short_task_Page;
    private Button menuBtn;
    private MenuListAdapter menuListAdapter;
    private long firstTime = 0;
    private ListView short_task_List;
    private ListItemAdapter short_ListAdapter;
    private ImageButton top_add;
    private ImageButton add_new_bt, sequence_menu;
    private Task task;
    /*5个ArrayList  动态数组*/
    public static ArrayList<String> taskID_list=new ArrayList<String>();
    public static ArrayList<String> taskName_list=new ArrayList<String>();
    public static ArrayList<String> taskContent_list=new ArrayList<String>();
    public static ArrayList<Integer> taskImportance_list=new ArrayList<Integer>();
    public static ArrayList<String> taskDeadline_list=new ArrayList<String>();
    public static 	AlarmManager alarmManager;
    public static PendingIntent pendingIntent;
    public static Date date;

    public static Date curDate;
    public static SimpleDateFormat formatter;
    public static String cur;
    public static String end;
    public static Date beginTime;
    public static Date endTime;
    public static long l;
    public static String temp;
    public static String show;
    public static int count=1;
    public static int tishi;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);

        setContentView(inflater.inflate(R.layout.menu_scroll_view, null));
        task = new Task();
        tishi=0;
        this.scrollView = (com.zhw.ui.ui.MenuHorizontalScrollView) findViewById(R.id.scrollView);
        this.menuList = (ListView) findViewById(R.id.menuList);

        this.short_task_Page = inflater.inflate(R.layout.short_task_page, null);
        this.short_task_List = (ListView) this.short_task_Page.findViewById(R.id.short_task_list);
         
  /******************************************************************************************************/      
        task.Connect(ShortTaskPageActivity.this);
        //       task.GetTaskByType("DailyTask","Importance");
               
            
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
        System.out.println(beginTime+"  curcurcur");
               
               if(task.GetTaskByType("ShortTask","TaskID")==1)
               {
                   	System.out.println("nihao");
               }
               else {
              
               Context ctx = ShortTaskPageActivity.this;       
               SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

               	 if(sp.getInt("INT_KEY_SHORT", 1)==0)
               	  		task.GetTaskByType("ShortTask","Importance");
               	 if(sp.getInt("INT_KEY_SHORT", 1)==1)
               		 	task.GetTaskByType("ShortTask","TaskID");
               	 if(sp.getInt("INT_KEY_SHORT", 1)==2)
               	 {
               		task.GetTaskByType1("ShortTask","DueTime");
               	 }
               		    
               	 alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE); 
   		            	do
   		                {
   		            		System.out.println(temp.substring(0, 12)+"  s111");	 
   		            		
   		            		if(temp.substring(0, 12).compareTo(task.DueTime.substring(0, 12))>=0)
		       		        {
		       		            	
		       		            	tishi=1;
		       		        }
		       		        
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
   		            		System.out.println(endTime.getMonth()-beginTime.getMonth()+"   qqqqqqq");
   		            		System.out.println(l/(1000*60)+"   lllll");
   		            		if(endTime.getYear()-beginTime.getYear()>1)
   		            		{
   		            		//show=String.valueOf(l/(1000*60*60*24*366))+"年";
   		            			show ="距截止时间约："+String.valueOf(endTime.getYear()-beginTime.getYear()+"年");
   		            			System.out.println(show);
   		            		}
   		            			
   		            		else if(endTime.getYear()-beginTime.getYear()==0&&endTime.getMonth()-beginTime.getMonth()>=1)
   		            		{
   		            			show ="距截止时间约："+String.valueOf(endTime.getMonth()-beginTime.getMonth()+"月");
   		            		}
   		            		else if(endTime.getYear()-beginTime.getYear()==1&&endTime.getMonth()-beginTime.getMonth()<0)
   		            		{
   		            			show ="距截止时间约："+String.valueOf(endTime.getMonth()+12-beginTime.getMonth()+"月");
   		            		}
   		            		else if(endTime.getYear()-beginTime.getYear()==1&&endTime.getMonth()-beginTime.getMonth()>=0)
   		            		{
   		            			show ="距截止时间约：1年";
   		            		}
   		            			else if(l/(1000*60*60*24*366)<1&&l/(1000*60*60*24)>=1)
   		            		{
   		            			show="距截止时间约："+String.valueOf(l/(1000*60*60*24))+"天";
   		            			System.out.println(show);
   		            		}
   		            			
   		            		else if(l/(1000*60*60*24*366)<1&&l/(1000*60*60*24)<1&&l/(1000*60*60)>=1)
   		            		{
   		            			show="距截止时间约："+String.valueOf(l/(1000*60*60))+"小时";
   		            			System.out.println(show);
   		            		}
   		            			
   		            		else if(l/(1000*60*60*24*366)<1&&l/(1000*60*60*24)<1&&l/(1000*60*60)<1&&l/(1000*60)>=1)
   		            		{
   		            			show="距截止时间约："+String.valueOf(l/(1000*60))+"分钟";
   		            			System.out.println(show);
   		            		}	
   		            		else
   		            		{
   		            			show="没定时完成哦";
   		            			System.out.println(show);
   		            		}
   		            		
   		            		
   		            		
   		            		
   		            		
//   		         		设置单次闹钟： 
   		         		   
   		         			Intent intent = new Intent(this, Receiver.class); 
   		         			intent.setAction("com.alarm.action_alarm_on");
   		         			pendingIntent = PendingIntent.getBroadcast(this,count, intent, 0);
   		         		if(temp.substring(0, 12).compareTo(task.DueTime.substring(0, 12))>0)
	       		        {
	       		            	System.out.println("kong");
	       		            	
	       		        }
   		         		else	alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + l, pendingIntent); 
   		         		   count++;
   		         		
   		         	    
//   		         		设置周期闹钟： 
//   		         		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (10*1000), (24*60*60*1000), pendingIntent); 
   		            		
   		            		
   		            		
   		            		
   		            		
   		            		
   		            		
   		            		taskID_list.add(show);
   							taskName_list.add(task.TaskName);
   							taskContent_list.add(task.TaskContext);
   							taskImportance_list.add(task.Importance);
   							System.out.println();
   							taskDeadline_list.add(task.DueTime);
   							
   		                }while(task.MoveNext()==0);
   		         //   	Toast toast = Toast.makeText(UiMainActivity.this, task.TaskName, Toast.LENGTH_SHORT); 
   		         //   	toast.show();
   		            	if(tishi==1)
		            	{
		            		    	Toast toast = Toast.makeText(ShortTaskPageActivity.this, "有没定时完成的任务，要加油喽！", Toast.LENGTH_SHORT); 
		            		    	toast.show();
		            		    	tishi=0;
		            	}
   		            	
   						
   				        
   		            	this.short_ListAdapter = new ListItemAdapter(this,taskID_list,taskName_list,taskContent_list,taskImportance_list,taskDeadline_list,2);
   		                this.short_task_List.setAdapter(short_ListAdapter);   
   		       
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
          
          
          
          
          
          
        
        

        this.add_new_bt = (ImageButton) this.short_task_Page.findViewById(R.id.top_bar_add);
        this.add_new_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                Intent it = new Intent(ShortTaskPageActivity.this, AddShortTaskActivity.class);
                startActivity(it);

            }
        });
        this.sequence_menu = (ImageButton) this.short_task_Page.findViewById(R.id.sequence_menu);
        this.sequence_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 显示 popupWindow
                PopupWindow popupWindow = makePopupWindow(ShortTaskPageActivity.this);
                int[] xy = new int[2];
                sequence_menu.getLocationOnScreen(xy);
                popupWindow.showAtLocation(sequence_menu, Gravity.RIGHT | Gravity.TOP, -xy[0] / 2, xy[1] * 5 / 4 + sequence_menu.getWidth());
                //  popupWindow.showAsDropDown(sequence_menu,0, 0);
            }
        });

        this.menuBtn = (Button) this.short_task_Page.findViewById(R.id.short_task_menuBtn);
        this.top_add = (ImageButton) this.short_task_Page.findViewById(R.id.top_bar_add);
        this.top_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                Intent in = new Intent(ShortTaskPageActivity.this, AddShortTaskActivity.class);
                startActivity(in);
            }
        });
        this.menuBtn.setOnClickListener(onClickListener);
        
   
        this.short_task_List.setItemsCanFocus(false);
        this.short_task_List.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        this.short_task_List.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                //To change body of implemented methods use File | Settings | File Templates.

                MenuInflater inflater = getMenuInflater();
                contextMenu.setHeaderTitle("任务操作").setHeaderIcon(R.drawable.userlogo);
                inflater.inflate(R.menu.content_menu, contextMenu);


            }
        });
        this.menuListAdapter = new MenuListAdapter(this, 2,this.menuBtn);
        this.menuList.setAdapter(menuListAdapter);

        View leftView = new View(this);
        leftView.setBackgroundColor(Color.TRANSPARENT);
        final View[] children = new View[]{leftView, short_task_Page};
        this.scrollView.initViews(children, new SizeCallBackForMenu(this.menuBtn), this.menuList);
        this.scrollView.setMenuBtn(this.menuBtn);
      //  Init();
    }


   /* public void Init() {
        uimain = (LinearLayout) this.short_task_Page.findViewById(R.id.home);
        uimain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShortTaskPageActivity.this, UiMainActivity.class);
                ShortTaskPageActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });


        shorttask = (LinearLayout) this.short_task_Page.findViewById(R.id.channel);
        TextView channeltv = (TextView) this.short_task_Page.findViewById(R.id.channeltv);
//        channeltv.setBackgroundResource(R.drawable.selected);
        shorttask.setBackgroundResource(R.drawable.tab_two_highlight);
        //  toptv = (TextView) this.short_task_Page.findViewById(R.id.centerContent);

        longtask = (LinearLayout) this.short_task_Page.findViewById(R.id.fav);
        longtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShortTaskPageActivity.this, LongTaskPageActivity.class);
                ShortTaskPageActivity.this.startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            }
        });

        chengjiu = (LinearLayout) this.short_task_Page.findViewById(R.id.search);
        chengjiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShortTaskPageActivity.this, ChengJiuPageActivity.class);
                ShortTaskPageActivity.this.startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, R.anim.stay);

            }
        });
    }*/

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
    			   Context ctx = ShortTaskPageActivity.this; 
    			   SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
    		        Editor editor = sp.edit();
    			    editor.putInt("INT_KEY_SHORT", 0);
    			     //   editor.putBoolean("BOOLEAN_KEY", true);
    			    editor.commit();
    		  //      System.out.println(sp.getInt("INT_KEY_SHORT", 1)+"  fufufufufufuf");
    		        
    		        finish();  
    		        Intent intent = new Intent(ShortTaskPageActivity.this, ShortTaskPageActivity.class);  
    		        startActivity(intent);
    			 
    		   }
    		   if(arg2 == 1)
    		   {
    			   Context ctx = ShortTaskPageActivity.this; 
    			   SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
    		        Editor editor = sp.edit();
    			    editor.putInt("INT_KEY_SHORT", 1);
    			  
    			    editor.commit();
    			    
    			    finish();  
    		        Intent intent = new Intent(ShortTaskPageActivity.this, ShortTaskPageActivity.class);  
    		        startActivity(intent);  
    			 //   System.out.println(sp.getInt("INT_KEY_SHORT", 1)+"  fufufufufufuf");
    		   }
    		   if(arg2==2){
    			   Context ctx = ShortTaskPageActivity.this; 
    			   SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
    		        Editor editor = sp.edit();
    			    editor.putInt("INT_KEY_SHORT", 2);
    			     //   editor.putBoolean("BOOLEAN_KEY", true);
    			    editor.commit();
    			    
    			    finish();  
    		        Intent intent = new Intent(ShortTaskPageActivity.this, ShortTaskPageActivity.class);  
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
            long secondTime = System.currentTimeMillis();

            if (secondTime - firstTime > 1000) {//如果两次按键时间间隔大于1000毫秒，则不退出
                Toast.makeText(ShortTaskPageActivity.this, "再按一次退出程序...", Toast.LENGTH_SHORT).show();
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

        return super.onKeyUp(keyCode, event);
    }

    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            scrollView.clickMenuBtn();
        }
    };
    
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
        	  task.Connect(ShortTaskPageActivity.this);
        	  if(task.GetTaskByType("ShortTask","TaskID")==1)
        	  {
        	       System.out.println("nihao");
        	  }
        	  else {  
        	            Context ctx = ShortTaskPageActivity.this;       
        	            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

        	            	 if(sp.getInt("INT_KEY_SHORT", 1)==0)
        	            	  		task.GetTaskByType("ShortTask","Importance");
        	            	 if(sp.getInt("INT_KEY_SHORT", 1)==1)
        	            		 	task.GetTaskByType("ShortTask","TaskID");
        	            	 if(sp.getInt("INT_KEY_SHORT", 1)==2)
        	               	 {
        	               		task.GetTaskByType1("ShortTask","DueTime");
        	               	 }

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
        	
        	   System.out.println(taskID_list_temp.get(short_ListAdapter.getCount()-info.position-1)+"wowowoowwwSHSHSHSHSHS");
        	
   //         	Toast.makeText(this,"点卡第一个的修改", Toast.LENGTH_SHORT).show();
    //        	System.out.println(taskID_list_temp+"  wowowoowowo");

                
     //           if(info.position ==0)
      //      	{}
                Intent intent = new Intent();
                intent.setClass(ShortTaskPageActivity.this, EditShortTask.class);
                intent.putExtra("name", taskName_list_temp.get(short_ListAdapter.getCount()-info.position-1));
                intent.putExtra("content", taskContent_list_temp.get(short_ListAdapter.getCount()-info.position-1));
                intent.putExtra("importance", taskImportance_list_temp.get(short_ListAdapter.getCount()-info.position-1));
                intent.putExtra("ID", taskID_list_temp.get(short_ListAdapter.getCount()-info.position-1));
                intent.putExtra("deadline", taskDeadlie_list_temp.get(short_ListAdapter.getCount()-info.position-1));
                intent.putExtra("daily", "ShortTask");
                taskID_list_temp.clear();
                taskName_list_temp.clear();
                taskContent_list_temp.clear();
                taskImportance_list_temp.clear();
                taskDeadlie_list_temp.clear();
                
                
                
                startActivity(intent);
                
               
        	
        	System.out.println("更改。。。。。。"+short_ListAdapter.getCount());
            return true;
        case R.id.content_menu_delete:
            // do something
        	
        	task.Connect(ShortTaskPageActivity.this);
      	  if(task.GetTaskByType("ShortTask","TaskID")==1)
      	  {
      	       System.out.println("nihao");
      	  }
      	  else {  
      		alarmManager.cancel(pendingIntent);
      	            Context ctx = ShortTaskPageActivity.this;       
      	            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

      	            	 if(sp.getInt("INT_KEY_SHORT", 1)==0)
      	            	  		task.GetTaskByType("ShortTask","Importance");
      	            	 if(sp.getInt("INT_KEY_SHORT", 1)==1)
      	            		 	task.GetTaskByType("ShortTask","TaskID");
      	            	 if(sp.getInt("INT_KEY_SHORT", 1)==2)
      	               	 {
      	               		task.GetTaskByType1("ShortTask","DueTime");
      	               	 }

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
      			            	task.DeleteTask(taskID_list_temp.get(short_ListAdapter.getCount()-info.position-1));
      	            }
      	            
      	            task.Close();
      	          finish();  
   		        Intent intent1 = new Intent(ShortTaskPageActivity.this, ShortTaskPageActivity.class);  
   		        startActivity(intent1);
      	
      	   System.out.println(taskID_list_temp.get(short_ListAdapter.getCount()-info.position-1)+"wowowoowww");
      	
 
            
              
             
      	
         	System.out.println("更改。。。。。。"+short_ListAdapter.getCount());
        	
        	
        	
        	System.out.println("删除。。。。。。");
            return true;
        case R.id.content_menu_detail:
            // do something
        	
        	 task.Connect(ShortTaskPageActivity.this);
       	  if(task.GetTaskByType("ShortTask","TaskID")==1)
       	  {
       	       System.out.println("nihao");
       	  }
       	  else {  
       	            Context ctx = ShortTaskPageActivity.this;       
       	            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

       	            	 if(sp.getInt("INT_KEY_SHORT", 1)==0)
       	            	  		task.GetTaskByType("ShortTask","Importance");
       	            	 if(sp.getInt("INT_KEY_SHORT", 1)==1)
       	            		 	task.GetTaskByType("ShortTask","TaskID");
       	            	 if(sp.getInt("INT_KEY_SHORT", 1)==2)
       	               	 {
       	               		task.GetTaskByType1("ShortTask","DueTime");
       	               	 }

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
       	       
       	
       	   System.out.println(taskID_list_temp.get(short_ListAdapter.getCount()-info.position-1)+"wowowoowww");
       	
  //         	Toast.makeText(this,"点卡第一个的修改", Toast.LENGTH_SHORT).show();
   //        	System.out.println(taskID_list_temp+"  wowowoowowo");

               
    //           if(info.position ==0)
     //      	{}
               Intent intent2 = new Intent();
               intent2.setClass(ShortTaskPageActivity.this, DetailShort.class);
               intent2.putExtra("name", taskName_list_temp.get(short_ListAdapter.getCount()-info.position-1));
               intent2.putExtra("content", taskContent_list_temp.get(short_ListAdapter.getCount()-info.position-1));
               intent2.putExtra("importance", taskImportance_list_temp.get(short_ListAdapter.getCount()-info.position-1));
               intent2.putExtra("ID", taskID_list_temp.get(short_ListAdapter.getCount()-info.position-1));
               intent2.putExtra("deadline", taskDeadlie_list_temp.get(short_ListAdapter.getCount()-info.position-1));
               intent2.putExtra("daily", "ShortTask");
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
}
