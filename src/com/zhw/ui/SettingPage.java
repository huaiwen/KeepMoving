package com.zhw.ui;

/**
 * Created with IntelliJ IDEA.
 * User: Wymon Zhang
 * Date: 13-8-2
 * Time: 上午11:49
 * To change this template use File | Settings | File Templates.
 */

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import com.imudges.keepmoving.R;
import com.zhw.adapter.DirChooserDialog;
import com.zhw.ui.ui.SlipButton;
import com.zhw.util.DisplayManager;

import java.util.ArrayList;
import java.util.List;

public class SettingPage extends Activity {

    private  int PasswordFlag=0;
    private  int ShakeFlag=0;
    private  int MusicFlag=0;
    private Vibrator vibrator=null;
    NotificationManager nmr;
    Notification nt;
    int soundID;
    String MusicPath =null;
    String ShakeMode=new String();
    Spinner shakemode;
    View view;
    DirChooserDialog dlg;


    private LinearLayout ll_main;
    private TableLayout tableLayout;

    private LinearLayout.LayoutParams layoutParams;

    private static final String MSG_0[] = { "返回主界面" };

    private static final String MSG_1[] = { "密码保护", "设置密码" };

    private static final String MSG_2[] = { "振动模式", "震动类型", "响铃模式", "铃声选择"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setingpage);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        showTable();
    }


    public void showTable() {

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.bottomMargin = 30;
        layoutParams.topMargin = 10;
        ll_main.addView(getTable(MSG_0), layoutParams);

        ll_main.addView(getTable(MSG_1), layoutParams);

        ll_main.addView(getTable(MSG_2), layoutParams);


    }


    int i;

    public TableLayout getTable(final String[] array) {

        tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(layoutParams);
        tableLayout.setStretchAllColumns(true);

        for (i = 0; i < array.length; i++) {
            TableRow tableRow = new TableRow(this);
            View view = getView(array[i], i, array.length);
            tableRow.addView(view);

            tableLayout.addView(tableRow);

        }
        return tableLayout;

    }


    public View getView(final String msg, int current_Id, int totle_Num) {

        LinearLayout linearLayout = new LinearLayout(this);

        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.height = 1;
        linearLayout.setOrientation(1);

        View line = new View(this);
        line.setLayoutParams(layoutParams1);
        line.setBackgroundColor(getResources().getColor(R.color.black));

        view = LayoutInflater.from(SettingPage.this).inflate(
                R.layout.item, null);
        view.setBackgroundDrawable(new BitmapDrawable());

        view.setFocusable(true);
        view.setClickable(true);
        TextView textView = (TextView) view.findViewById(R.id.tv_list_item);
        textView.setText(msg);
        textView.setTextSize(20);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_list_item);

        SlipButton s=(SlipButton)view.findViewById(R.id.switch_list_item);
        int tmpwidth,tmpheight;
        tmpheight=DisplayManager.dip2px(this,24);
        tmpwidth=DisplayManager.dip2px(this,55);
        s.setLayoutParams(new LinearLayout.LayoutParams(tmpwidth,tmpheight));
        s.setOnChangedListener(new SlipButton.OnChangedListener() {
            @Override
            public void OnChanged(boolean CheckState) {
                if (msg.equals(MSG_1[0])) {
                    if (PasswordFlag==0)
                        PasswordFlag=1;
                    else
                        PasswordFlag=0;
                }
                if (msg.equals(MSG_2[0])) {
                    if (ShakeFlag==0)
                        ShakeFlag=1;
                    else
                        ShakeFlag=0;
                }
                if (msg.equals(MSG_2[2])) {
                    if (MusicFlag==0) {
                        MusicFlag=1;
                    }
                    else
                        MusicFlag=0;
                }
            }
        });

        if(msg.equals(MSG_1[0])){
            imageView.setVisibility(View.GONE);
            s.setVisibility(View.VISIBLE);
        }
        if(msg.equals(MSG_2[0])){
                imageView.setVisibility(View.GONE);
                s.setVisibility(View.VISIBLE);
        }
        if(msg.equals(MSG_2[2])){
                imageView.setVisibility(View.GONE);
                s.setVisibility(View.VISIBLE);
        }





        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (msg.equals(MSG_0[0]))
                    startActivity(new Intent(SettingPage.this,UiMainActivity.class));


                if (msg.equals(MSG_1[1]))
                {
                    if (PasswordFlag==1) {
                        LayoutInflater inflater=getLayoutInflater();
                        View layout=inflater.inflate(R.layout.password_dialog,(ViewGroup)findViewById(R.id.password_daliog));
                        new AlertDialog.Builder(SettingPage.this).setTitle("新密码").setView(layout)
                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                })
                                .setNegativeButton("取消",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //取消按钮
                                    }
                                }).show();
                    }
                }

                if (msg.equals(MSG_2[1]))
                {
                    if (ShakeFlag==1) {
                        LayoutInflater inflater=getLayoutInflater();
                        View layout=inflater.inflate(R.layout.shakemode_dialog,(ViewGroup)findViewById(R.id.shakemode_dailog_spinner));

                        shakemode=(Spinner)layout.findViewById(R.id.shakemode_dailog_spinner);
                        List<String> list = new ArrayList<String>();
                        ArrayAdapter<String> adapter;
                        list.add("长震");
                        list.add("短震");
                        list.add("点震");
                        adapter=new ArrayAdapter<String>(SettingPage.this,android.R.layout.simple_spinner_item,list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        shakemode.setAdapter(adapter);
                        shakemode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                switch ((int)l)
                                {
                                    case 0:
                                        vibrator.cancel();
                                        //vibrator.vibrate(new long[]{50,100},1);
                                        vibrator.vibrate(5000);
                                        ShakeMode="长震";
                                        break;
                                    case 1:
                                        vibrator.cancel();
                                        vibrator.vibrate(new long[]{1000,0,200,1000},0);
                                        ShakeMode="短震";
                                        break;
                                    case 2:
                                        vibrator.cancel();
                                        vibrator.vibrate(new long[]{1000,0,250,250},0);
                                        ShakeMode="点震";
                                    default:
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                //To change body of implemented methods use File | Settings | File Templates.
                            }
                        });


                        new AlertDialog.Builder(SettingPage.this).setTitle("震动模式").setView(layout)
                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //确定按钮
                                        vibrator.cancel();
                                    }
                                })
                                .setNegativeButton("取消",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //取消按钮
                                        vibrator.cancel();
                                    }
                                }).show();
                    }
                }

                if (msg.equals(MSG_2[3]))
                {

                    nmr=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    nt=new Notification();



                    if (MusicFlag==1) {
                        nmr.cancelAll();

                        String[] filterType={"mp3","wav","ogg"};
                        dlg=new DirChooserDialog(SettingPage.this,2,filterType, MusicPath);
                        dlg.setTitle("选择音乐");

                        dlg.notificationManager=nmr;
                        dlg.notification=nt;

                        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                if (!dlg.path.equals("/")){
                                    MusicPath =new String(dlg.path+dlg.DecideMusic);
                                    if (!dlg.DecideMusic.equals("")) {
                                        ((TextView) view.findViewById(R.id.tv_list_item)).setText(dlg.DecideMusic);
                                        Log.d("Music", MusicPath);

                                        nmr.cancelAll();
                                    }
                                }
                            }
                        });

                        dlg.show();

                    }
                }
            }
        });

        if (totle_Num == 1) {
            view.setBackgroundResource(R.drawable.default_selector);
            return view;
        }
        else if (current_Id == 0) {
            view.setBackgroundResource(R.drawable.list_top_selector);
        }
        else if (current_Id == totle_Num - 1) {
            view.setBackgroundResource(R.drawable.list_bottom_selector);
            line.setVisibility(View.GONE);
        } else
            view.setBackgroundResource(R.drawable.list_center_selector);

        linearLayout.addView(view);
        linearLayout.addView(line);

        return linearLayout;
    }

}
