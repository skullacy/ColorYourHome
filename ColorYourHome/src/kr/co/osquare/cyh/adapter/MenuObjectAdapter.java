package kr.co.osquare.cyh.adapter;

import java.util.ArrayList;
import java.util.List;

import kr.co.osquare.cyh.R;
import kr.co.osquare.cyh.model.MenuObject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuObjectAdapter extends BaseAdapter {

	private List<MenuObject> list = new ArrayList<MenuObject>();
	private Context context;
	private int openIndex = -1;
	
	public MenuObjectAdapter(Context context) {
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) 
			convertView = LayoutInflater.from(this.context).inflate(R.layout.menu_row, null);
		
		MenuObject object = this.getItem(position);
		
		if (object.isChildren()) {
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setVisibility(View.GONE);

			View space = (View) convertView.findViewById(R.id.row_space);
			space.setVisibility(View.VISIBLE);
			
			View container = (View) convertView.findViewById(R.id.row_container);
			container.setBackgroundResource(R.drawable.submenu_background);

			ImageView arrow = (ImageView) convertView.findViewById(R.id.row_arrow);
			arrow.setVisibility(View.VISIBLE);
			arrow.setImageResource(R.drawable.menu_parent_children);
		}
		else {
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).getIconRes());
			icon.setVisibility(View.VISIBLE);

			View space = (View) convertView.findViewById(R.id.row_space);
			space.setVisibility(View.GONE);

			View container = (View) convertView.findViewById(R.id.row_container);
			container.setBackgroundResource(android.R.color.transparent);
			
			ImageView arrow = (ImageView) convertView.findViewById(R.id.row_arrow);
			arrow.setVisibility((object.getChildrenSize() > 0) ? View.VISIBLE : View.GONE);
			arrow.setImageResource((position == this.openIndex) ? R.drawable.menu_parent_on : R.drawable.menu_parent_off);
		}
		
		TextView title = (TextView) convertView.findViewById(R.id.row_title);
		title.setText(getItem(position).getName());

		return convertView;
	}

	@Override
	public int getCount() {
		return list.size() + (openIndex >= 0 ? this.getItem(openIndex).getChildrenSize() : 0);
	}

	@Override
	public MenuObject getItem(int position) {
		
		if (openIndex >= 0 && position <= openIndex) {
			return list.get(position);
		}
		else if (openIndex >= 0 && position > openIndex + this.getItem(openIndex).getChildrenSize()) {
			return list.get(position - openIndex - this.getItem(openIndex).getChildrenSize() + 1);
		}
		else if (openIndex >= 0) {
			return list.get(openIndex).get(position - openIndex - 1);
		}
		else 
			return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void open(int index) {
		this.openIndex = index;
		this.notifyDataSetChanged();
	}

	public void add(MenuObject menuObject) {
		list.add(menuObject);
	}

	public int getOpenIndex() {
		return this.openIndex;
	}

}