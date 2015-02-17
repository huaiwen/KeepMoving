package com.zhw.Swipe2Delete;

import android.view.View;
import android.view.ViewGroup;
import com.imudges.keepmoving.R;
import com.zhw.Swipe2Delete.SwipeToDeleteListView;


public class MyDeletedViewAdapter implements SwipeToDeleteListView.DeletedViewAdapter
{
	private SwipeToDeleteListView listView;

	public MyDeletedViewAdapter(SwipeToDeleteListView listView) {
		this.listView = listView;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		if(view == null) {
			view = View.inflate(listView.getContext(), R.layout.view_deletedview, null);
		}
		view.findViewById(R.id.deletedView_cancel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listView.cancelDeletion(position);
			}
		});
        view.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.confirmDeletion(position);
                       }
        });
		return view;
	}
}
