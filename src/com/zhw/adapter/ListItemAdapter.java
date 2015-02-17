package com.zhw.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import com.imudges.keepmoving.R;
import com.zhw.ui.AddDailyTaskActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListItemAdapter extends BaseAdapter  implements ListAdapter {
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;
    public static Map<Integer, Boolean> isSelected;
    public int ImageId[] = {R.drawable.importance1,R.drawable.importance2,R.drawable.importance3,R.drawable.importance4,R.drawable.importance5};

    public ListItemAdapter adapter;
    public AddDailyTaskActivity arrayList;

    public Integer cao;
    /*
    public void set(ListItemAdapter adapter2){
    	adapter=adapter2;
  //  	return adapter;
    }*/

    public ListItemAdapter(Context context,ArrayList<String> ID,ArrayList<String> name,ArrayList<String> content,ArrayList<Integer> importance,ArrayList<String> deadline,int flag) {
		// TODO Auto-generated constructor stub
    	mInflater = LayoutInflater.from(context);
    	if(flag == 1)
          init(ID,name,content,importance,deadline);
    	if(flag == 2)
    	  init2(ID,name,content,importance,deadline);
    	if(flag == 3)
    	  init3(ID,name,content,importance,deadline);
	}

	//初始化
    private void init(ArrayList<String> ID,ArrayList<String> name,ArrayList<String> content,ArrayList<Integer> importance,ArrayList<String> deadline) {
            mData = new ArrayList<Map<String, Object>>();
            HashMap<String, Object> map = null;
            
            int temp;
            
            
               for(int i=ID.size()-1;i>=0;i--)
               {
            	    map=new HashMap<String, Object>();
            	    temp = importance.get(i).intValue();
            	    System.out.println(temp+"   temppppp");
            	    map.put("custom_img",ImageId[temp-1]);
            	    
	                map.put("custom_count_time",ID.get(i));
	                //名称
	                map.put("custom_name",name.get(i));
	                //描述
	                map.put("custom_description",content.get(i));
	                mData.add(map);
	                
               }
        
            
        //这儿定义isSelected这个map是记录每个listitem的状态，初始状态全部为false。
        isSelected = new HashMap<Integer, Boolean>();
        for (int k = 0; k < mData.size(); k++) {
            isSelected.put(k, false);
        }
    }

    private void init2(ArrayList<String> ID,ArrayList<String> name,ArrayList<String> content,ArrayList<Integer> importance,ArrayList<String> deadline) {
        mData = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map = null;
        
        int temp;
        
        
           for(int i=ID.size()-1;i>=0;i--)
           {
        	    map=new HashMap<String, Object>();
        	    temp = importance.get(i).intValue();
        	    System.out.println(temp+"   temppppp");
        	    map.put("custom_img",ImageId[temp-1]);
        	    
                map.put("custom_count_time",ID.get(i));
                //名称
                map.put("custom_name",name.get(i));
                //描述
                map.put("custom_description",content.get(i));
                mData.add(map);
                
           }
    
        
    //这儿定义isSelected这个map是记录每个listitem的状态，初始状态全部为false。
    isSelected = new HashMap<Integer, Boolean>();
    for (int k = 0; k < mData.size(); k++) {
        isSelected.put(k, false);
    }
}
    
    
    private void init3(ArrayList<String> ID,ArrayList<String> name,ArrayList<String> content,ArrayList<Integer> importance,ArrayList<String> deadline) {
        mData = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map = null;
        
        int temp;
        
        
           for(int i=ID.size()-1;i>=0;i--)
           {
        	    map=new HashMap<String, Object>();
        	    temp = importance.get(i).intValue();
        	    System.out.println(temp+"   temppppp");
        	    map.put("custom_img",ImageId[temp-1]);
        	    
                map.put("custom_count_time",ID.get(i));
                //名称
                map.put("custom_name",name.get(i));
                //描述
                map.put("custom_description",content.get(i));
                mData.add(map);
                
           }
    
        
    //这儿定义isSelected这个map是记录每个listitem的状态，初始状态全部为false。
    isSelected = new HashMap<Integer, Boolean>();
    for (int k = 0; k < mData.size(); k++) {
        isSelected.put(k, false);
    }
}

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        //To change body of implemented methods use File | Settings | File Templates.
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
    public boolean hasStableIds() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
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
               holder.curDel_btn = (Button)convertView.findViewById(R.id.curDel_btn);
            holder.count_down_time_ll = (TextView) convertView.findViewById(R.id.count_time_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img.setBackgroundResource((Integer) mData.get(position).get(
                "custom_img"));
        holder.task_name.setText(mData.get(position).get("custom_name").toString());
        holder.task_bi.setText(mData.get(position).get("custom_description").toString());
        holder.count_down_time_ll.setText(mData.get(position).get("custom_count_time").toString());
        holder.positon = position;
        //为每一个view项设置触控监听

        return convertView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getViewTypeCount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEmpty() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEnabled(int i) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public final class ViewHolder {
        public ImageView img;
        public double ux,x;
        //public TextView compt;
        public TextView task_name;
        public int positon;
        public TextView task_bi;
        public TextView count_down_time_ll;
        public Button curDel_btn;
    }
}