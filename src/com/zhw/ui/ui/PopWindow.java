package com.zhw.ui.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: Wymon Zhang
 * Date: 13-8-4
 * Time: 上午10:00
 * To change this template use File | Settings | File Templates.
 */
public class PopWindow extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] strs = {"1"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,strs);
        setListAdapter(adapter);
    }
}
