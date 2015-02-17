package com.zhw.ui.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.imudges.keepmoving.R;

/**
 * Created with IntelliJ IDEA.
 * User: Wymon Zhang
 * Date: 13-7-27
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class ListItem extends LinearLayout {

    private ImageView userlogo;
    private TextView task_name, task_bi;
   /* private int imag_Id;
    private String Name,Task;*/

    public ListItem(Context context, int imag_Id, String Name, String Task) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =  inflater.inflate(R.layout.listitem_layout, this);
        userlogo = (ImageView)view.findViewById(R.id.user_logo);
        task_name = (TextView)view.findViewById(R.id.task_name);
        task_bi = (TextView)view.findViewById(R.id.task_bi);
        this.userlogo.setImageResource( imag_Id);
        this.task_name.setText(Name);
        this.task_bi.setText(Task);

    }
    public ListItem(Context context, ImageView userlogo, TextView task_name, TextView task_bi) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =  inflater.inflate(R.layout.listitem_layout, this);
        userlogo = (ImageView)view.findViewById(R.id.user_logo);
        task_name = (TextView)view.findViewById(R.id.task_name);
        task_bi = (TextView)view.findViewById(R.id.task_bi);
        this.userlogo = userlogo;
        this.task_name = task_name;
        this.task_bi = task_bi;
    }

    public ListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view =  inflater.inflate(R.layout.listitem_layout, this);
        userlogo = (ImageView)view.findViewById(R.id.user_logo);
        task_name = (TextView)view.findViewById(R.id.task_name);
        task_bi = (TextView)view.findViewById(R.id.task_bi);


    }

    /**
     * 设置图片资源
     */
    public void setUserlogo(ImageView userlogo){
                   this.userlogo = userlogo;
    }
    public void setImageResource(int resId) {
        userlogo.setImageResource(resId);
    }


    public void setNameTextViewText(String text) {
        task_name.setText(text);
    }

    public void setTaskTextViewText(String text) {
        task_bi.setText(text);
    }
    public ImageView getImage() {
        return      userlogo;
    }


    public String getNameTextViewText() {
     return  (String)task_name.getText();
    }

    public String getTaskTextViewText() {
        return  (String)task_bi.getText();
    }
}

