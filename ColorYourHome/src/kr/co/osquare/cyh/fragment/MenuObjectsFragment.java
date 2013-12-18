package kr.co.osquare.cyh.fragment;

import kr.co.osquare.cyh.R;
import kr.co.osquare.cyh.activity.MainFrameActivity;
import kr.co.osquare.cyh.adapter.MenuObjectAdapter;
import kr.co.osquare.cyh.model.MenuObject;
import kr.co.osquare.cyh.remote.CategoryManager;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MenuObjectsFragment extends ListFragment {

	MenuObjectAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		adapter.add(new MenuObject("�̿���", R.drawable.slide_left_profile_icon, 0));
//		adapter.add(new MenuObject("알림", R.drawable.slide_left_signal_icon, 2));

		CategoryManager category = CategoryManager.getInstance();
		adapter = new MenuObjectAdapter(getActivity());
		
		adapter.add(new MenuObject("이연희",R.drawable.userimg, 0));
		adapter.add(new MenuObject("홈", R.drawable.slide_left_home_icon, 1));
		
		MenuObject categories = new MenuObject("카테고리", R.drawable.slide_left_category_icon, 3);
		for (int i = 0; i < category.getJSONArray().length(); i++) {
			try {
				JSONObject c = category.getJSONArray().getJSONObject(i);
				categories.put(new MenuObject(c.getString("name"), R.drawable.slide_left_category_icon, 100 + c.getInt("id"), true));
			} catch (JSONException e) {
			}
		}
		adapter.add(categories);
		
		adapter.add(new MenuObject("설정", R.drawable.slide_left_setting_icon, 4));

		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		MenuObject o = (MenuObject) this.getListAdapter().getItem(position);
		MainFrameActivity frame = MainFrameActivity.getInstance();
		if (o.getChildrenSize() > 0) {
			adapter.open(adapter.getOpenIndex() == position ? -1 : position);
		}
		else {
			if (o.getId() < 100) {
				switch(o.getId()) {
				case 0:
					frame.fragmentReplace(4);
					break;
				case 1:
					frame.fragmentReplace(0);
					break;
				case 2:
					frame.fragmentReplace(1);
					break;
				case 4:
					frame.fragmentReplace(3);
					break;
				}
			}
			else {
				System.out.println(o.getId());
				frame.fragmentReplace(frame.getCategoryFragment(o.getId() - 100));
			}
			frame.getSlidingMenu().showContent();
		}

	}

}
