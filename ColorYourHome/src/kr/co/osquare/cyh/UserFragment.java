package kr.co.osquare.cyh;

import kr.co.osquare.cyh.adapter.ImagePostAdapter;

import com.actionbarsherlock.app.SherlockFragment;
import com.skray.skraylibs.view.SkrayListView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class UserFragment extends SherlockFragment {
	
	private SkrayListView listView;
	private ImagePostAdapter adapter;
	private int page;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.user_fragment, container, false);
		
		ImageButton uploadedbtn = (ImageButton)v.findViewById(R.id.uploadedbtn);
		uploadedbtn.setBackgroundDrawable(null);
		
		ImageButton mydrawer = (ImageButton)v.findViewById(R.id.mydrawer);
		mydrawer.setBackgroundDrawable(null);
		
		ImageButton mylike = (ImageButton)v.findViewById(R.id.mylike);
		mylike.setBackgroundDrawable(null);
		return v;
		
		
	}

}
