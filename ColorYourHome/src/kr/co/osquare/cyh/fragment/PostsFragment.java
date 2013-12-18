package kr.co.osquare.cyh.fragment;

import kr.co.osquare.cyh.R;
import kr.co.osquare.cyh.activity.DetailActivity;
import kr.co.osquare.cyh.adapter.ImagePostAdapter;
import kr.co.osquare.cyh.model.ImagePost;
import kr.co.osquare.cyh.model.ImagePostToTransfer;
import kr.co.osquare.cyh.model.MenuObject;
import kr.co.osquare.cyh.remote.PostManager;
import kr.co.osquare.cyh.remote.PostManager.OnPostLoadListener;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.skray.skraylibs.view.SkrayArcMenu;
import com.skray.skraylibs.view.SkrayListView;

public class PostsFragment extends SherlockFragment implements
		OnItemClickListener, OnPostLoadListener, OnPullEventListener<ListView> {

	private SkrayListView listView;
	private ImagePostAdapter adapter;
	private int page;
	
	private static final int[] ITEM_DRAWABLES = {
		R.drawable.align_button, R.drawable.align_button, R.drawable.align_button};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.post_fragment, container, false);
		
		SkrayArcMenu arcMenu = (SkrayArcMenu) view.findViewById(R.id.arc_menu);
		
		initArcMenu(arcMenu, ITEM_DRAWABLES);
		

		listView = (SkrayListView) view.findViewById(R.id.pull_to_refresh_listview);
		listView.getLoadingLayoutProxy().setLoadingDrawable(getResources().getDrawable(R.drawable.indicator_arrow));
		listView.setOnItemClickListener(PostsFragment.this);
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new GetDataTask().execute();
			}
		});

		page = 1;
		adapter = new ImagePostAdapter(getActivity());
		
		listView.setListViewAnimation(false);
		listView.setAdapter(adapter); 

		PostManager mPost = PostManager.getInstance();
		mPost.setOnPostLoadListener(this);
		mPost.postLoadAsync();

		return view;
	}
	
	

	private void initArcMenu(final SkrayArcMenu arcMenu, final int[] itemDrawables) {
		// TODO Auto-generated method stub
		final int itemCount = itemDrawables.length;
		for (int i = 0; i < itemCount; i++) {
			final ImageView item = new ImageView(getActivity());
			item.setImageResource(itemDrawables[i]);
			
			final int position = i;
			arcMenu.addItem(item, new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "position:" + position, Toast.LENGTH_SHORT).show();
					arcMenu.changePlanetImage(itemDrawables[position]);
				}
			});
		}
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			listView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		Log.e("onItemClick", "ItemClick");
//		MenuObject o = (MenuObject) arg0.getItemAtPosition(position);
		Intent intent = new Intent(getActivity(), DetailActivity.class);
		System.out.println(position);
		System.out.println(id);
		System.out.println(arg0);
				
		ImagePost imgdata = (ImagePost) arg0.getItemAtPosition(position);
		ImagePostToTransfer.setImagePost(imgdata);
		
		intent.putExtra("id", id);
		intent.putExtra("position", position);
		
		
		startActivity(intent);
		
		
		

	}

	public void onPostLoadComplete(boolean success, JSONObject result) {
		adapter.inputJSONObject(result);
	}

	@Override
	public void onPullEvent(PullToRefreshBase<ListView> refreshView,
			State state, Mode direction) {
		// TODO Auto-generated method stub

	}
}

