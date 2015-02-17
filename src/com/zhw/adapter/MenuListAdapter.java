package com.zhw.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.imudges.keepmoving.R;
import com.zhw.ui.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 同志们，这是一个左侧菜单列表适配器
* */
public class MenuListAdapter extends BaseAdapter {


    private Context context;
    //这是菜单的条目的表
    private List<Map<String, Object>> listItems;
    //这是条目的数量
    private int itemCount;
    //压缩泵
    private LayoutInflater listInflater;
    //item是否被压点击。。。
    private boolean isPressed[];
    //这个是每个条目前的小图标，那个黄色的五角星。。
    //由于没有素材，只弄了一个，有素材的话，谁有的话请把他换成int[]
    //注意同时修改getview的方法
    private int imageId[] = {R.drawable.qq, R.drawable.clock, R.drawable.calendar, R.drawable.list, R.drawable.like};
    //这是条目的内容。。。由于到底几个条目尚不清楚。。。前两个已经定义，后面的item内容都是这个，请知道的换成数组
    //并改本文最后的一个if语句为switch。。。。

    //这个是item的条数。。。想要增加或减少，请改这个值
    private final int COUNT = 5;
    private int pressedId;
    private Button menuBtn;

    //这个类，是上面的表里的那个对象
    public final class ListItemsView {
        public ImageView menuIcon;
        public TextView menuText;
        public TextView menuButton,menupc;
        public ProgressBar menuProgressBar;
        public   TextView   menuText_0;
        public  LinearLayout progrss_ll;

    }


    public MenuListAdapter(Context context, int pressedId, Button menubt) {

        this.context = context;
        this.pressedId = pressedId;
        this.menuBtn = menubt;
        this.init();
    }

    /*
    * 这个还用注释？。。。。。
    * */
    private void init() {

        this.itemCount = this.COUNT;
        this.listItems = new ArrayList<Map<String, Object>>();
        this.isPressed = new boolean[this.itemCount];
        for (int i = 0; i < this.itemCount; i++) {
            //对于每个item的初始化处理
            Map<String, Object> map = new HashMap<String, Object>();
            //if到时请改成switch
            if (i == 0) {

                map.put("menuIcon", imageId[i]);
                map.put("menuButton_img", R.drawable.setting);
                map.put("menuText_0", "坚持达人") ;
            } else if (i == 1) {
                map.put("menuText", "每日任务");
                map.put("menuIcon", imageId[i]);
                map.put("menuButton", " 50.00%");

            } else if (i == 2) {
                map.put("menuText", "短期任务");
                map.put("menuIcon", imageId[i]);
                map.put("menuButton", " 50.00%");

            } else if (i == 3) {
                map.put("menuText", "周期任务");
                map.put("menuIcon", imageId[i]);
                map.put("menuButton", " 50.00%");
            } else {
                map.put("menuText", "成就系统");
                map.put("menuIcon", imageId[i]);
                map.put("menuButton", " 50.00%");
            }
            this.listItems.add(map);
            this.isPressed[i] = false;
        }
        this.isPressed[this.pressedId] = true;
        this.listInflater = LayoutInflater.from(context);
    }

    /*
    * 返回菜单的item数目
    * */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.itemCount;
    }

    /*
  * 通过位置，返回菜单的的条目对象
  * 这个我没重载，想用的自己写
  * */
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    /*
    * 通过位置，返回菜单的的条目的ID
    * */
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //position是第几个item
        final int po = position;
        //定义了一个item对象，里面封装了item的前面的图标和item的内容
        ListItemsView listItemsView;
        if (convertView == null) {

            listItemsView = new ListItemsView();
            convertView = this.listInflater.inflate(R.layout.menu_list_item, null);
            //设定条目的图标视图
            listItemsView.menuIcon = (ImageView) convertView.findViewById(R.id.menuIcon);
            listItemsView.menuProgressBar = (ProgressBar) convertView.findViewById(R.id.menuProgressBar);
            //设定条目的textview
            listItemsView.menuText = (TextView) convertView.findViewById(R.id.menuText);
            listItemsView.menuText_0 = (TextView) convertView.findViewById(R.id.menuText_0);
            listItemsView.menuButton = (TextView) convertView.findViewById(R.id.menuButton);
            listItemsView.progrss_ll  =(LinearLayout) convertView.findViewById(R.id.progress_ll);
            listItemsView.menupc = (TextView)    convertView.findViewById(R.id.menupc);
            //设置 convertView和  listItemsView关联
            convertView.setTag(listItemsView);
        } else {
            listItemsView = (ListItemsView) convertView.getTag();
        }
        if (po == 0) {
            listItemsView.menuText_0.setVisibility(View.VISIBLE);
            listItemsView.menuText_0.setText((String) listItems.get(position).get("menuText_0"));
            listItemsView.menuButton.setBackgroundResource((Integer) listItems.get(position).get("menuButton_img"));
            listItemsView.progrss_ll.setVisibility(View.INVISIBLE);
            listItemsView.menuIcon.setImageResource((Integer) listItems.get(position).get("menuIcon"));
            listItemsView.menuIcon.setBackgroundResource(R.drawable.back_behind_usershow_style);
            listItemsView.menuText.setVisibility(View.GONE);

        //    convertView.setLayoutParams(new AbsListView.LayoutParams(convertView.getMeasuredWidth() - this.menuBtn.getMeasuredWidth() - 40, ViewGroup.LayoutParams.WRAP_CONTENT));
            convertView.setBackgroundResource(R.drawable.head_bg);
         listItemsView.menuButton.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View view) {
                 Log.i("hehe", "keyidianm ");
                 Intent it =new Intent(context,SettingPage.class);
                 context.startActivity(it);
             }
         });

        } else {
            listItemsView.menuText.setText((String) listItems.get(position).get("menuText"));

            listItemsView.menupc.setText((String) listItems.get(position).get("menuButton"));
            listItemsView.menuIcon.setBackgroundResource((Integer) listItems.get(position).get("menuIcon"));

        }
        //设置每个item的图片和文本


        if (po != 0) {
            if (this.isPressed[position] == true)
                convertView.setBackgroundResource(R.drawable.menu_item_bg_sel);
            else
                convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        if(po!=0)
        {
        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                changeState(po);
                gotoActivity(po);
                notifyDataSetInvalidated();
                new Handler().post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                    }

                });
            }
        });
        }

        return convertView;
    }

    /**
     * 名字已经写的很清楚了啊。
     * 页面跳转的时候请改下面的switch
     * T-T 早知道我去做数据库了。。。。
     * *
     */
    private void gotoActivity(int position) {
        Intent intent = new Intent();
        switch (position) {
            case 1:
                intent.setClass(context, UiMainActivity.class);
                context.startActivity(intent);
                //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

                break;
        /*----------------------------------------------------*/
            case 2:
                intent.setClass(context, ShortTaskPageActivity.class);
                context.startActivity(intent);
                break;
            /*----------------------------------------------------*/
            case 3:
                intent.setClass(context, LongTaskPageActivity.class);
                context.startActivity(intent);
                break;
        /*----------------------------------------------------*/
            case 4:
                intent.setClass(context, ChengJiuPageActivity.class);
                context.startActivity(intent);
        }
    }

    /**
     * 改变状态，是不是被按了下去
     */
    private void changeState(int position) {

        if (position != 0) {
            for (int i = 0; i < this.itemCount; i++) {
                isPressed[i] = false;
            }
            isPressed[position] = true;
        } else

        {
            for (int i = 0; i < this.itemCount; i++) {
                isPressed[i] = false;
            }
            isPressed[position] = true;
        }
    }


}



