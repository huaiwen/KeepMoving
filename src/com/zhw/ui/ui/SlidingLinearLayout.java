package com.zhw.ui.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class SlidingLinearLayout extends LinearLayout {


	private float lastMotionX = -1;
	
	public SlidingLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

    /**
     * 拦截和监控手势*/
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		//return super.onInterceptTouchEvent(ev);
		if(ev.getAction() == MotionEvent.ACTION_DOWN){
			this.lastMotionX = (int)ev.getRawX();
		}
		if(this.lastMotionX < 50)
			return true;
		else if(MenuHorizontalScrollView.menuOut)
			return true;
		else 
			return super.onInterceptTouchEvent(ev);
	}

}
