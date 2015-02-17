package com.zhw.ui.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ListView;
import com.imudges.keepmoving.R;


public class SetingListView extends ListView {
	public SetingListView(Context context) {
		super(context);
	}

	public SetingListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int x = (int) ev.getX();
			int y = (int) ev.getY();
			int itemnum = pointToPosition(x, y);
			if (itemnum == AdapterView.INVALID_POSITION)
				break;
			else {
				if (itemnum == 0) {
					if (itemnum == (getAdapter().getCount() - 1)) {

						setSelector(R.drawable.list_round);
					} else {

						setSelector(R.drawable.list_top_round);
					}
				} else if (itemnum == (getAdapter().getCount() - 1))

					setSelector(R.drawable.list_bottom_round);
				else {

					setSelector(R.drawable.list_center_round);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return super.onTouchEvent(ev);
	}
}