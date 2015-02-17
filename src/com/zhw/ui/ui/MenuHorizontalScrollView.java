package com.zhw.ui.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import com.zhw.callback.SizeCallBack;
import com.zhw.callback.SizeCallBackForMenu;
import com.imudges.keepmoving.R;


  /**
   * 菜单的水平滚动视图类
   * */
public class MenuHorizontalScrollView extends HorizontalScrollView {
	
	
	private MenuHorizontalScrollView me;
   	private ListView menu;
	public static boolean menuOut;
	private final int ENLARGE_WIDTH = 80;
	private int menuWidth;
	private float lastMotionX = -1;
	private Button menuBtn;
	private int current;
	private int scrollToViewPos;
	
	public MenuHorizontalScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
    public MenuHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public MenuHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	       /**
            * 初始化滚动视图的一些属性
            * */
	private void init(){
              //水平和竖直滚动时边框渐隐效果
        //true时效果有时不好。。。。
		this.setHorizontalFadingEdgeEnabled(false);
		this.setVerticalFadingEdgeEnabled(false);
		this.me = this;
		this.me.setVisibility(View.INVISIBLE);
        //默认状态，菜单没有滚出去
		menuOut = false;
	}

      /*
               View leftView = new View(this);
        final View[] children = new View[]{leftView, UiMainPage};
      this.scrollView.initViews(children, new SizeCallBackForMenu(this.menuBtn), this.menuList);
*/
      public void initViews(View[] children, SizeCallBackForMenu sizeCallBack, ListView menu){
		this.menu = menu;
		ViewGroup parent = (ViewGroup)getChildAt(0);
		 //在父视图里添加姿势图
		for(int i = 0; i < children.length; i++){
			children[i].setVisibility(View.INVISIBLE);
			parent.addView(children[i]);
		}
		 OnGlobalLayoutListener onGlLayoutistener = new  MenuOnGlobalLayoutListener(parent,
				 children, sizeCallBack);
		 getViewTreeObserver().addOnGlobalLayoutListener(onGlLayoutistener);
		 
	}
	    //不拦截触控事件
	 @Override
	 public boolean onInterceptTouchEvent(MotionEvent ev){
	        return false;
	 }
	 
	
	 public void setMenuBtn(Button btn){
		 this.menuBtn = btn;
	 } 
	 
	  /**
       * 点击菜单的按钮
       * */
	 public void clickMenuBtn(){
                 //如果菜单没出去，此时menu的宽度是0，否则。。。。。
		 if(!menuOut){
			 this.menuWidth = 0;
		 }
		 else{
			 this.menuWidth = this.menu.getMeasuredWidth() - this.menuBtn.getMeasuredWidth() - this.ENLARGE_WIDTH;
		 }
		 menuSlide();
	 }
	 
	 
	 private void menuSlide(){
		             //出去了就回来，回来了，就出去
		 if(this.menuWidth == 0){
			 menuOut = true;
		 }
		 else{
			 menuOut = false;
		 }
         //滚动
		 me.scrollTo(this.menuWidth, 0);
		 if(menuOut == true)
			 this.menuBtn.setBackgroundResource(R.drawable.menu_fold);
		 else
			 this.menuBtn.setBackgroundResource(R.drawable.menu_unfold);
	 }
	 
	 

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		if(l < (this.menu.getMeasuredWidth() - this.menuBtn.getMeasuredWidth() - this.ENLARGE_WIDTH) / 2){
			this.menuWidth = 0;
		}
		else{
			this.menuWidth = this.menu.getWidth() - this.menuBtn.getMeasuredWidth() - this.ENLARGE_WIDTH;
		}
		this.current = l;
	}


      /***
       * 重点来了，触控事件！
       *
       */

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int x = (int)ev.getRawX();
		
		if(ev.getAction() == MotionEvent.ACTION_DOWN){
			
			this.lastMotionX = (int)ev.getRawX();
		}
		if((this.current == 0 && x < this.scrollToViewPos) || 
				(this.current == this.scrollToViewPos * 2 && x > this.ENLARGE_WIDTH)){
			return false;
		}
        //一千是不是有点多啊，官方实例用的是20
		if(menuOut == false && this.lastMotionX > 1000){
			return true;
		}
			
		else{
			if(ev.getAction() == MotionEvent.ACTION_UP){
				menuSlide();
				return false;
			}
		}
		return super.onTouchEvent(ev);
	}


	
	
	public class MenuOnGlobalLayoutListener implements OnGlobalLayoutListener {
		
		private ViewGroup parent;
		private View[] children;
		//private int scrollToViewIndex = 0;
		private SizeCallBack sizeCallBack;
		
		public MenuOnGlobalLayoutListener(ViewGroup parent, View[] children, SizeCallBackForMenu sizeCallBack) {
			
			this.parent = parent;
			this.children = children;
			this.sizeCallBack = sizeCallBack;
		}

		@Override
		public void onGlobalLayout() {
			// TODO Auto-generated method stub
			me.getViewTreeObserver().removeGlobalOnLayoutListener(this);
	        this.sizeCallBack.onGlobalLayout();
	        this.parent.removeViewsInLayout(0, children.length);
	        int width = me.getMeasuredWidth();
	        int height = me.getMeasuredHeight();
	        
	        int[] dims = new int[2];
	        scrollToViewPos = 0;
	        
	        for(int i = 0; i < children.length; i++){
	        	this.sizeCallBack.getViewSize(i, width, height, dims);
	            children[i].setVisibility(View.VISIBLE);
	            parent.addView(children[i], dims[0], dims[1]);
	            if(i == 0){
	                scrollToViewPos += dims[0];
	            }
	        }
	        
	        new Handler().post(new Runnable(){
	            @Override
	            public void run(){
	            	me.scrollBy(scrollToViewPos, 0);
	                
	            
	            	me.setVisibility(View.VISIBLE);
	                menu.setVisibility(View.VISIBLE);
	            }
	        });
	    }
	}

}
