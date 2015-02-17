package com.zhw.ui;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import java.lang.Integer;

import com.xuejiabin.*;
import com.imudges.keepmoving.R;
import com.neo.database.*;
import com.zhw.adapter.MenuListAdapter;
import com.zhw.callback.SizeCallBackForMenu;
import com.zhw.ui.ui.MenuHorizontalScrollView;


public class ChengJiuPageActivity extends Activity {
    private LinearLayout uimain, shorttask, longtask, chengjiu;
    TextView toptv;

    private MenuHorizontalScrollView scrollView;
    private ListView menuList;
    private View chengjiu_page;
    private Button menuBtn;
    private Button shareBtn;
    private MenuListAdapter menuListAdapter;
    private long   firstTime=0;
    private ListView ChengJiu_List;
    private ListItemAdapter1 CJ_ListAdapter;
    private Person person;
    private TextView user_name;
    private TextView user_dengji;
    private Cursor curse;
    private ImageView HeadImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        setContentView(inflater.inflate(R.layout.menu_scroll_view, null));
        this.scrollView = (MenuHorizontalScrollView)findViewById(R.id.scrollView);
        this.menuList = (ListView)findViewById(R.id.menuList);
    //    score a=new score();
  //      a.increase(ChengJiuPageActivity.this);
        this.chengjiu_page = inflater.inflate(R.layout.chengjiu_page, null);
        ////////////////////////////////////////////////////////////////////////
        person=new Person();
        person.UserImage=null;
        person.UserName="薛嘉宾";
        person.Achievement="0";
        person.Connect(ChengJiuPageActivity.this);
        person.Insert();
        person.GetUser();
        this.user_name=(TextView)chengjiu_page.findViewById(R.id.task_name);
	    this.user_dengji=(TextView)chengjiu_page.findViewById(R.id.task_bi);
	    this.HeadImage=(ImageView)chengjiu_page.findViewById(R.id.HeadImage);
        if(person.GetUser()==1)
        {
        	user_name.setText("坚持达人");
        	user_dengji.setText("坚持小菜");
    	    this.CJ_ListAdapter = new ListItemAdapter1(this);
        }
        else
        {
	    user_name.setText(person.UserName);
	    int i=java.lang.Integer.parseInt(person.Achievement, 10);
	    this.CJ_ListAdapter = new ListItemAdapter1(this, i);
	    if(person.UserImage!=null)
	    {
	    HeadImage.setImageResource(Integer.parseInt(person.UserImage));
	    }
	    if(i<5)
	    {
	    	user_dengji.setText("逆水行舟");
	    }
	    else
	    {
	    	if(i<15)
	    	{
	    		user_dengji.setText("初出茅庐");
	    	}
	    	else
	    	{
	    		if(i<35)
	    		{
	    			user_dengji.setText("突飞猛进");
	    		}
	    		else
	    		{
	    			if(i<55)
	    			{
	    				user_dengji.setText("倍道而进");
	    			}
	    			else
	    			{
	    				if(i<90)
	    				{
	    					user_dengji.setText("青云直上");
	    				}
	    				else
	    				{
	    					if(i<160)
	    					{
	    						user_dengji.setText("与日剧增");
	    					}
	    					else
	    					{
	    						if(i<260)
	    						{
	    							user_dengji.setText("一如既往");
	    						}
	    						else
	    						{
	    							if(i<400)
	    							{
	    								user_dengji.setText("水滴石穿");
	    							}
	    							else
	    							{
	    								if(i<550)
	    								{
	    									user_dengji.setText("坚定不移");
	    								}
	    								else
	    								{
	    									if(i<700)
	    									{
	    										user_dengji.setText("百折不挠");
	    									}
	    									else
	    									{
	    										if(i<880)
	    										{
	    											user_dengji.setText("百尺竿头");
	    										}
	    										else
	    										{
	    											if(i<1000)
	    											{
	    												user_dengji.setText("辉煌成就");
	    											}
	    											else
	    											{
	    												if(i<2000)
	    												{
	    													user_dengji.setText("九转功成");
	    												}
	    												else
	    												{
	    													if(i<5000)
	    													{
	    														user_dengji.setText("功成名就");
	    													}
	    													else
	    													{
	    														user_dengji.setText("登峰造极");
	    													}
	    												}
	    											}
	    										}
	    									}
	    								}
	    							}
	    						}
	    					}
	    				}
	    			}
	    		}
	    	}
	    }
        }
	    /////////////////////////////////////////////////////////////////////////
       /* display = getWindowManager().getDefaultDisplay();
            this.headImage = (ImageView)this.chengjiu_page.findViewById(R.id.HeadImage);
        this.headImage.setLayoutParams(new LinearLayout.LayoutParams(
                px2dip(this,display.getWidth()),
                px2dip(this,display.getHeight())
        ));*/
       /* List<MyData> list=new ArrayList<MyData>();
    	list.add(new MyData("To be the change",50));
    	list.add(new MyData("you want to see",20));
    	list.add(new MyData("in the world",80));
    	MyAdapter myAdapter=new MyAdapter(this,list); */
    	//listView.setAdapter(myAdapter);
        
        this.ChengJiu_List = (ListView) this.chengjiu_page.findViewById(R.id.chengjiu_listview);
        this.ChengJiu_List.setAdapter(CJ_ListAdapter);
        this.ChengJiu_List.setItemsCanFocus(false);
        this.ChengJiu_List.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.ChengJiu_List.setCacheColorHint(Color.TRANSPARENT);  
        
       /* this.ChengJiu_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListItemAdapter.ViewHolder vHollder = (ListItemAdapter.ViewHolder) view.getTag();
//在每次获取点击的item时将对于的checkbox状态改变，同时修改map的值。
                vHollder.cBox.toggle();
                ListItemAdapter.isSelected.put(position, vHollder.cBox.isChecked());
            }
        });*/
      /*  this.ChengJiu_List.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                //To change body of implemented methods use File | Settings | File Templates.
                MenuInflater inflater = getMenuInflater();
                contextMenu.setHeaderTitle("任务操作").setHeaderIcon(R.drawable.userlogo);
                inflater.inflate(R.menu.content_menu, contextMenu);

            }
        });*/
        this.menuBtn = (Button)this.chengjiu_page.findViewById(R.id.zhw_menuBtn);
        this.menuBtn.setOnClickListener(onClickListener);
        this.shareBtn=(Button)this.chengjiu_page.findViewById(R.id.share_bt);
        shareBtn.setOnClickListener(new OnClickListener() {            

   	     public void onClick(View v) {

   		              Intent intent=new Intent(Intent.ACTION_SEND);  

   		               intent.setType("image/*");  

   		                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");  

   		               intent.putExtra(Intent.EXTRA_TEXT, "I have successfully share my message through my app");  

   		               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  

   		               startActivity(Intent.createChooser(intent, getTitle()));  

   		       }  

   		}); 
        this.menuListAdapter = new MenuListAdapter(this, 4,this.menuBtn);
        this.menuList.setAdapter(menuListAdapter);

        View leftView = new View(this);
        leftView.setBackgroundColor(Color.TRANSPARENT);
        final View[] children = new View[]{leftView, chengjiu_page};
        this.scrollView.initViews(children, new SizeCallBackForMenu(this.menuBtn), this.menuList);
        this.scrollView.setMenuBtn(this.menuBtn);
       // Init();
    }
   /* public void Init() {
        uimain = (LinearLayout) this.chengjiu_page.findViewById(R.id.home);
        uimain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChengJiuPageActivity.this, UiMainActivity.class);
                ChengJiuPageActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);

            }
        });


        shorttask = (LinearLayout) this.chengjiu_page.findViewById(R.id.channel);
        shorttask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChengJiuPageActivity.this, ShortTaskPageActivity.class);
                ChengJiuPageActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);

            }
        });


        longtask = (LinearLayout) this.chengjiu_page.findViewById(R.id.fav);
        longtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(ChengJiuPageActivity.this, LongTaskPageActivity.class);
                ChengJiuPageActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.right_in,R.anim.left_out);

            }
        });




        chengjiu = (LinearLayout)this.chengjiu_page. findViewById(R.id.search);
        //        searchtv.setBackgroundResource(R.drawable.selected);
        chengjiu.setBackgroundResource(R.drawable.tab_two_highlight);
       *//* toptv = (TextView) this.chengjiu_page.findViewById(R.id.centerContent);*//*

    }*/
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            {

               long secondTime = System.currentTimeMillis();

                if (secondTime - firstTime > 1000) {//如果两次按键时间间隔大于1000毫秒，则不退出
                    Toast.makeText(ChengJiuPageActivity.this, "再按一次退出程序...", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//Ui更新firstTime
                    return true;
                } else {
                  /*  System.exit(0);//否则退出程序*/
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
            scrollView.clickMenuBtn();
        }
    };
}