package com.xuejiabin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.imudges.keepmoving.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.neo.database.Person;
import com.zhw.ui.ChengJiuPageActivity;

public class ListItemAdapter1 extends BaseAdapter {
	
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;
    public static Map<Integer, Boolean> isSelected;
    private int item;
    public ListItemAdapter1(Context context,int i) {
        mInflater = LayoutInflater.from(context);
        item=i;
        init();
    }
    public ListItemAdapter1(Context context) {
        mInflater = LayoutInflater.from(context);
        item=0;
        init();
    }

	//初始化
    private void init() {
        mData = new ArrayList<Map<String, Object>>();
        
            HashMap<String, Object> map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=0)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
            //名称
            map.put("custom_name", "逆水行舟");
            //描述
            map.put("custom_description", "这是你获得的第一个成就，赶快去完成任务获取新的成就吧！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=5)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
            //名称
            map.put("custom_name", "初出茅庐 ");
            //描述
            map.put("custom_description", "完成五个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=15)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
            //名称
            map.put("custom_name", "突飞猛进");
            //描述
            map.put("custom_description", "完成十五个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=35)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
            //名称
            map.put("custom_name", "倍道而进");
            //描述
            map.put("custom_description", "完成三十五个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=55)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "青云直上");
            //描述
            map.put("custom_description", "完成五十五个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>90)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "与日剧增");
            //描述
            map.put("custom_description", "完成九十个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=160)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "一如既往");
            //描述
            map.put("custom_description", "完成一百六十个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=260)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "水滴石穿 ");
            //描述
            map.put("custom_description", "完成二百六十个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=400)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "坚定不移");
            //描述
            map.put("custom_description", "完成四百个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=550)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "百折不挠");
            //描述
            map.put("custom_description", "完成五百五十个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=700)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "百尺竿头");
            //描述
            map.put("custom_description", "完成七百个个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=880)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "辉煌成就");
            //描述
            map.put("custom_description", "完成八百八十个个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=1000)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "九转功成");
            //描述
            map.put("custom_description", "完成一千个个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=2000)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "功成名就");
            //描述
            map.put("custom_description", "完成两千个任务就可以获得此成就！");

            mData.add(map);
            map = new HashMap<String, Object>();
            //value值为图片的资源id
            if(item>=5000)
            {
            map.put("custom_img",R.drawable.c );
            }
            else
            {
            	map.put("custom_img",R.drawable.b );
            }
             
            //名称
            map.put("custom_name", "登峰造极");
            //描述
            map.put("custom_description", "完成五千个任务就可以获得此成就！，并且你能获得惊喜哦！");

            mData.add(map);
        //这儿定义isSelected这个map是记录每个listitem的状态，初始状态全部为false。
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < mData.size(); i++) {
            isSelected.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //convertView为null的时候初始化convertView。
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.uimainactivity_list_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.user_logo);
          //  holder.compt = (TextView) convertView.findViewById(R.id.comp_time);
            holder.task_name = (TextView) convertView.findViewById(R.id.task_name);
            holder.task_bi = (TextView) convertView.findViewById(R.id.task_bi);

       //     holder.count_down_time_ll = (TextView) convertView.findViewById(R.id.count_time_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img.setBackgroundResource((Integer) mData.get(position).get(
                "custom_img"));
        holder.task_name.setText(mData.get(position).get("custom_name").toString());
        holder.task_bi.setText(mData.get(position).get("custom_description").toString());
   //     holder.count_down_time_ll.setText(mData.get(position).get("custom_count_time").toString());
      //  holder.cBox.setChecked(isSelected.get(position));
        return convertView;
    }

    public final class ViewHolder {
        public ImageView img;
        public TextView task_name;
        public TextView task_bi;
    }
}