package com.zhw.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import com.zhw.Swipe2Delete.MyDeletedViewAdapter;
import  com.zhw.Swipe2Delete.SwipeToDeleteListView;

import com.imudges.keepmoving.Detaildaily;
import com.imudges.keepmoving.R;
import com.imudges.keepmoving.ShowTask;
import com.neo.database.Task;
import com.zhw.adapter.ListItemAdapter;
import com.zhw.adapter.MenuListAdapter;
import com.zhw.callback.SizeCallBackForMenu;
import com.zhw.ui.ui.MenuHorizontalScrollView;
import com.zhw.util.DisplayManager;

/**
 * Created with IntelliJ IDEA.
 * User: Wymon Zhang
 * Date: 13-7-21
 * Time: 上午10:49
 * 如果修改本页的布局，请修改menu_ui_page
 */

public class UiMainActivity
        extends Activity
        implements
        SwipeToDeleteListView.OnItemDeletedListener,
        SwipeToDeleteListView.OnItemDeletionConfirmedListener,
        AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener
{

    private LinearLayout uimain, shorttask, longtask, chengjiu;
    TextView toptv;
    //新建一个滑动视图
    private MenuHorizontalScrollView scrollView;
    private ListView menuList;
    private View UiMainPage;
    private Button menuBtn;
    private MenuListAdapter menuListAdapter;
    private long firstTime = 0;
    private SwipeToDeleteListView UiMainList;
    private ListItemAdapter UiMainListAdapter;
    private ImageButton add_new_bt;
    private ImageButton sequence_menu;

    private int flag;
    private Task task;
    private String name;
    private String ID;
    private String content;
 
    public static int tishi=0;
    public Cursor cursor;
 //   public static int flag2=0;
 //   public SQLiteDatabase db2;
  //  DatabaseHelper mOpenHelper;
   
    /*5个ArrayList  动态数组*/
    public static ArrayList<String> taskID_list=new ArrayList<String>();
    public static ArrayList<String> taskName_list=new ArrayList<String>();
    public static ArrayList<String> taskContent_list=new ArrayList<String>();
    public static ArrayList<Integer> taskImportance_list=new ArrayList<Integer>();
    public static ArrayList<String> taskDeadline_list=new ArrayList<String>();
    
    
    public static Date curDate;
    public static SimpleDateFormat formatter;
    public static String cur;
   

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //初始化目录
        LayoutInflater inflater = LayoutInflater.from(this);
        setContentView(inflater.inflate(R.layout.menu_scroll_view, null));
        this.scrollView = (MenuHorizontalScrollView) findViewById(R.id.scrollView);
        this.menuList = (ListView) findViewById(R.id.menuList);
        task = new Task();
        flag=1;
        
        

        
        
        
        
        
     
        //这是主页面的布局及一些操作
        {
            this.UiMainPage = inflater.inflate(R.layout.main_ui_page, null);
            
            this.UiMainList = (SwipeToDeleteListView) this.UiMainPage.findViewById(R.id.Uimain_list);






            formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            curDate=new Date(System.currentTimeMillis());
            cur = formatter.format(curDate); 
            
            task.Connect(UiMainActivity.this);

            
            
            
            
            
            if(task.GetTaskByType("DailyTask","TaskID")==1)
            {
            	System.out.println("nihao");
            }
            else {
           
            Context ctx = UiMainActivity.this;       
            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

            	 if(sp.getInt("INT_KEY", 1)==0)
            	  		task.GetTaskByType("DailyTask","Importance");
            	 if(sp.getInt("INT_KEY", 1)==1)
            		 	task.GetTaskByType("DailyTask","TaskID");

		            	do
		                {
		            		System.out.println("hello oworld");	
		            		
		            		if(cur.substring(0, 8).compareTo(task.TaskID.substring(0, 8))>0)
		       		        {
		       		            	taskID_list.add("没定时完成哦！");
		       		            	task.DueTime="noAchieve";
		       		            	task.Update();
		       		        }
		       		        else
		       		        	taskID_list.add(task.TaskID.substring(8, 10)+":"+task.TaskID.substring(10, 12));
							taskName_list.add(task.TaskName);
							taskContent_list.add(task.TaskContext);
							taskImportance_list.add(task.Importance);
							System.out.println();
							taskDeadline_list.add(task.DueTime);
							if(task.DueTime.compareTo("noAchieve")==0)
								tishi=1;
		                }while(task.MoveNext()==0);
		            	
		            	if(tishi==1)
		            	{
		            		    	Toast toast = Toast.makeText(UiMainActivity.this, "没有完成当天的任务哦，要加油喽！", Toast.LENGTH_SHORT); 
		            		    	toast.show();
		            		    	tishi=0;
		            	}
		        
		            	
		            	

						
		            	this.UiMainListAdapter = new ListItemAdapter(this,taskID_list,taskName_list,taskContent_list,taskImportance_list,taskDeadline_list,1);



		             
		                
		                taskID_list.clear();
		                taskName_list.clear();
		                taskContent_list.clear();
		                taskImportance_list.clear();
		                taskDeadline_list.clear();

            }
            
            task.Close();
            
            
            
            
            
            
            
            





            UiMainList.setOnItemDeletedListener(this);
            UiMainList.setOnItemClickListener(this);
            UiMainList.setOnItemLongClickListener(this);
            UiMainList.setAdapter(UiMainListAdapter);

            UiMainList.setConfirmNeeded(true);
            UiMainList.setDeletedViewAdapter(new MyDeletedViewAdapter(UiMainList));
            UiMainList.setOnItemDeletionConfirmedListener(this);
            this.UiMainList.setItemsCanFocus(false);
            this.UiMainList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            

           
            
            this.UiMainList.setOnCreateContextMenuListener(this);
            
            this.add_new_bt = (ImageButton) this.UiMainPage.findViewById(R.id.top_bar_add);
            
            this.add_new_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //To change body of implemented methods use File | Settings | File Templates.
                	
                    Intent it = new Intent(UiMainActivity.this, AddDailyTaskActivity.class);
                    startActivity(it);
                }
            });
            this.sequence_menu = (ImageButton)this.UiMainPage.findViewById(R.id.sequence_menu);
            this.sequence_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 显示 popupWindow
                    PopupWindow popupWindow = makePopupWindow(UiMainActivity.this);
                    int[] xy = new int[2];
                    sequence_menu.getLocationOnScreen(xy);
                    popupWindow.showAtLocation(sequence_menu,Gravity.RIGHT|Gravity.TOP,-xy[0]/2,xy[1]*5/4+sequence_menu.getWidth());
                  //  popupWindow.showAsDropDown(sequence_menu,0, 0);
                }
            });
            this.menuBtn = (Button) this.UiMainPage.findViewById(R.id.zhw_menuBtn);
            this.menuBtn.setOnClickListener(onClickListener);
          //  Init();
        }
        this.menuListAdapter = new MenuListAdapter(this, 1,this.menuBtn);
        this.menuList.setAdapter(menuListAdapter); 
        View leftView = new View(this);
        leftView.setBackgroundColor(Color.TRANSPARENT);
        final View[] children = new View[]{leftView, UiMainPage};
        this.scrollView.initViews(children, new SizeCallBackForMenu(this.menuBtn), this.menuList);
        this.scrollView.setMenuBtn(this.menuBtn);
    }
    /**
     * 创建一个包含自定义view的PopupWindow
     * */
    private PopupWindow makePopupWindow(Context cx)
    {
        PopupWindow window;
        window = new PopupWindow(cx);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow, null);
        window.setContentView(contentView);
        ListView listView = new ListView(this);
        listView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        String[] strs = {"重要程度","添加时间"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.popwindow_item,strs);
        listView.setAdapter(adapter);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.addView(listView);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        window.setContentView(linearLayout);
        //  window.setBackgroundDrawable(getResources().getDrawable(R.drawable.popwindowdialog));
        window.setWidth(DisplayManager.dip2px(this,160));
        window.setHeight(DisplayManager.dip2px(this,100));
        
        
        OnItemClickListener clickListener = new OnItemClickListener() {

    		@Override
    		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
    				long arg3) {
    			// TODO Auto-generated method stub
    		   if(arg2 ==0){
    			   Context ctx = UiMainActivity.this; 
    			   SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
    		        Editor editor = sp.edit();
    			    editor.putInt("INT_KEY", 0);
    			     //   editor.putBoolean("BOOLEAN_KEY", true);
    			    editor.commit();
    		        System.out.println(sp.getInt("INT_KEY", 1)+"  fufufufufufuf");
    		        
    		        finish();  
    		        Intent intent = new Intent(UiMainActivity.this, UiMainActivity.class);  
    		        startActivity(intent);
    			 
    		   }
    		   if(arg2 == 1)
    		   {
    			   Context ctx = UiMainActivity.this; 
    			   SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
    		        Editor editor = sp.edit();
    			    editor.putInt("INT_KEY", 1);
    			     //   editor.putBoolean("BOOLEAN_KEY", true);
    			    editor.commit();
    			    
    			    finish();  
    		        Intent intent = new Intent(UiMainActivity.this, UiMainActivity.class);  
    		        startActivity(intent);  
    			 //   System.out.println(sp.getInt("INT_KEY", 1)+"  fufufufufufuf");
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


    /**
     * 上方的走遍菜单按钮的监听
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub

            scrollView.clickMenuBtn();
        }
    };

    /**
     * 退出程序
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            long secondTime = System.currentTimeMillis();

            if (secondTime - firstTime > 1000) {//如果两次按键时间间隔大于1000毫秒，则不退出
                Toast.makeText(UiMainActivity.this, "再按一次退出程序...", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        MenuInflater inflater = getMenuInflater();
        contextMenu.setHeaderTitle("任务操作").setHeaderIcon(R.drawable.userlogo);
        inflater.inflate(R.menu.content_menu, contextMenu);
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
        	
        	
        	  task.Connect(UiMainActivity.this);
        	  if(task.GetTaskByType("DailyTask","TaskID")==1)
        	  {
        	       System.out.println("nihao");
        	  }
        	  else {  
        	            Context ctx = UiMainActivity.this;       
        	            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

        	            	 if(sp.getInt("INT_KEY", 1)==0)
        	            	  		task.GetTaskByType("DailyTask","Importance");
        	            	 if(sp.getInt("INT_KEY", 1)==1)
        	            		 	task.GetTaskByType("DailyTask","TaskID");

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
        	
        	   System.out.println(taskID_list_temp.get(UiMainListAdapter.getCount()-info.position-1)+"wowowoowww");
        	

                Intent intent = new Intent();
                intent.setClass(UiMainActivity.this, ShowTask.class);
                intent.putExtra("name", taskName_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
                intent.putExtra("content", taskContent_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
                intent.putExtra("importance", taskImportance_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
                intent.putExtra("ID", taskID_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
                intent.putExtra("deadline", taskDeadlie_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
                intent.putExtra("daily", "DailyTask");
                taskID_list_temp.clear();
                taskName_list_temp.clear();
                taskContent_list_temp.clear();
                taskImportance_list_temp.clear();
                taskDeadlie_list_temp.clear();
                
                
                
                startActivity(intent);
                
               
        	
        	System.out.println("更改。。。。。。"+UiMainListAdapter.getCount());
            return true;
        case R.id.content_menu_delete:
            // do something
        	
        	task.Connect(UiMainActivity.this);
      	  if(task.GetTaskByType("DailyTask","TaskID")==1)
      	  {
      	       System.out.println("nihao");
      	  }
      	  else {  
      	            Context ctx = UiMainActivity.this;       
      	            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

      	            	 if(sp.getInt("INT_KEY", 1)==0)
      	            	  		task.GetTaskByType("DailyTask","Importance");
      	            	 if(sp.getInt("INT_KEY", 1)==1)
      	            		 	task.GetTaskByType("DailyTask","TaskID");

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
      			            	task.DeleteTask(taskID_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
      	            }
      	            
      	            task.Close();
      	          finish();  
   		        Intent intent1 = new Intent(UiMainActivity.this, UiMainActivity.class);  
   		        startActivity(intent1);
      	
      	   System.out.println(taskID_list_temp.get(UiMainListAdapter.getCount()-info.position-1)+"wowowoowww");
      	
 
            
              
             
      	
         	System.out.println("更改。。。。。。"+UiMainListAdapter.getCount());
        	
        	
        	
        	System.out.println("删除。。。。。。");
            return true;
        case R.id.content_menu_detail:
            // do something
        	 task.Connect(UiMainActivity.this);
       	  if(task.GetTaskByType("DailyTask","TaskID")==1)
       	  {
       	       System.out.println("nihao");
       	  }
       	  else {  
       	            Context ctx = UiMainActivity.this;       
       	            SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);

       	            	 if(sp.getInt("INT_KEY", 1)==0)
       	            	  		task.GetTaskByType("DailyTask","Importance");
       	            	 if(sp.getInt("INT_KEY", 1)==1)
       	            		 	task.GetTaskByType("DailyTask","TaskID");

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
       	       
       	
       	   System.out.println(taskID_list_temp.get(UiMainListAdapter.getCount()-info.position-1)+"wowowoowww");
       	

               Intent intent2 = new Intent();
               intent2.setClass(UiMainActivity.this, Detaildaily.class);
               intent2.putExtra("name", taskName_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
               intent2.putExtra("content", taskContent_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
               intent2.putExtra("importance", taskImportance_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
               intent2.putExtra("ID", taskID_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
               intent2.putExtra("deadline", taskDeadlie_list_temp.get(UiMainListAdapter.getCount()-info.position-1));
               intent2.putExtra("daily", "DailyTask");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_withconfirm, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId() == R.id.menu_withconfirm_confirm) {
            UiMainList.confirmAllDeletion();
            return true;
        }
        if(item.getItemId() == R.id.menu_withconfirm_cancel) {
            UiMainList.cancelAllDeletions();
            return true;
        }
        return false;
    }

    @Override
    public void onItemDeleted(SwipeToDeleteListView listView, int position) {
        Toast.makeText(this, position + " has been deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemDeletionConfirmed(SwipeToDeleteListView listView, int position) {

        //在这里添加删除操作。。。
       // UiMainListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, position + " has been long clicked", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, position + " has been clicked", Toast.LENGTH_SHORT).show();
    }


}
