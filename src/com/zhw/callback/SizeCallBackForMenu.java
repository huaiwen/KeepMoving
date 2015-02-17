package com.zhw.callback;

import android.widget.Button;
     /**
      * 重定义返回键的效果
      * **/
public class SizeCallBackForMenu implements SizeCallBack {

	private Button menu;
	private int menuWidth;
         //这是左侧菜单在弹出去后，给原来的界面留的宽度，越大，menu越窄
    private  final int YOUCEWIDTH = 100;
	
	
	public SizeCallBackForMenu(Button menu){
		//super();
		this.menu = menu;
	}
	@Override
	public void onGlobalLayout() {
		// TODO Auto-generated method stub

        this.menuWidth = this.menu.getMeasuredWidth() + YOUCEWIDTH;
	}
     /**
      * 得到当前视图的尺寸，然后反映到menu的宽度上*/
	@Override
	public void getViewSize(int idx, int width, int height, int[] dims) {
		// TODO Auto-generated method stub
		dims[0] = width;
		dims[1] = height;
		if(idx != 1){    //如果不是page，让menu页的宽度值为1080-228（128+100）
			dims[0] = width - this.menuWidth;
		}
	}

}
