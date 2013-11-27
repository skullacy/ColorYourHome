package kr.co.osquare.cyh.fragment;

import kr.co.osquare.cyh.R;
import kr.co.osquare.cyh.adapter.ImagePostAdapter;
import kr.co.osquare.cyh.model.MenuObject;
import kr.co.osquare.cyh.remote.PostManager;
import kr.co.osquare.cyh.remote.PostManager.OnPostLoadListener;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.skray.skraylibs.view.SkrayListView;

public class PostsFragment extends SherlockListFragment implements
		OnItemClickListener, OnPostLoadListener, OnPullEventListener<ListView> {

	private SkrayListView listView;
	private ImagePostAdapter adapter;
	private int page;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.post_fragment, container, false);

		listView = (SkrayListView) view.findViewById(R.id.pull_to_refresh_listview);
		listView.getLoadingLayoutProxy().setLoadingDrawable(getResources().getDrawable(R.drawable.indicator_arrow));
		listView.setOnItemClickListener(this);
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new GetDataTask().execute();
			}
		});

		page = 1;
		adapter = new ImagePostAdapter(getActivity());
		listView.setAdapter(adapter);

		PostManager mPost = PostManager.getInstance();
		mPost.setOnPostLoadListener(this);
		mPost.postLoadAsync();

		return view;
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
		MenuObject o = (MenuObject) arg0.getItemAtPosition(position);

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
