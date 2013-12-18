package kr.co.osquare.cyh;

import kr.co.osquare.cyh.adapter.ImagePostAdapter;

import com.actionbarsherlock.app.SherlockFragment;

import com.skray.skraylibs.view.SkrayListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class UserFragment extends SherlockFragment {
	
	private SkrayListView listView;
	private ImagePostAdapter adapter;
	private int page;
	
	TabHost tabHost;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.user_fragment, container, false);
		
		tabHost = (TabHost)v.findViewById(R.id.tabHost);
		tabHost.setup();
		
		setupTab(R.id.tab1, "업로드", R.drawable.uploadedbtn);
		setupTab(R.id.tab2, "서랍", R.drawable.mydrawer);
		setupTab(R.id.tab3, "예뻐요", R.drawable.mylike);
		
		tabHost.getTabWidget().getChildAt(0).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				initTabImg();
				((ImageView)tabHost.getTabWidget().getChildAt(0).findViewById(R.id.indiv_tabs_img)).setImageResource(R.drawable.uploadedbtn);
				tabHost.setCurrentTab(0);
			}
			
		});
		tabHost.getTabWidget().getChildAt(1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initTabImg();
				((ImageView)tabHost.getTabWidget().getChildAt(1).findViewById(R.id.indiv_tabs_img)).setImageResource(R.drawable.nesuraps);
				tabHost.setCurrentTab(1);
			}
		});
		
		tabHost.getTabWidget().getChildAt(2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initTabImg();
				((ImageView)tabHost.getTabWidget().getChildAt(2).findViewById(R.id.indiv_tabs_img)).setImageResource(R.drawable.ebbuyo);
				tabHost.setCurrentTab(2);
			}
		});
		
		
		return v;
		
		
	}
	
	public void initTabImg(){
		((ImageView)tabHost.getTabWidget().getChildAt(0).findViewById(R.id.indiv_tabs_img)).setImageResource(R.drawable.upcount_nomal);
		((ImageView)tabHost.getTabWidget().getChildAt(1).findViewById(R.id.indiv_tabs_img)).setImageResource(R.drawable.nesurap);
		((ImageView)tabHost.getTabWidget().getChildAt(2).findViewById(R.id.indiv_tabs_img)).setImageResource(R.drawable.ebbu_nomal);
	}
	
	
	public void setupTab(int resId, String tag, int imgID){
		View tabview = createTabView(getActivity(), imgID);
		TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabview)
				.setContent(resId);
		
		tabHost.addTab(setContent);
		
	}
	
	private static View createTabView(final Context context, final int imgID){
		View view = LayoutInflater.from(context).inflate(R.layout.indiv_tabs_bg, null);
		ImageView iv = (ImageView)view.findViewById(R.id.indiv_tabs_img);
		iv.setImageResource(imgID);
		return view;
	}
}
