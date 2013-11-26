package kr.co.osquare.cyh.adapter;

import java.util.ArrayList;
import java.util.List;

import kr.co.osquare.cyh.R;
import kr.co.osquare.cyh.model.ImagePost;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidhive.imagefromurl.ImageLoader;

public class ImagePostAdapter extends BaseAdapter {

	private List<ImagePost> list = new ArrayList<ImagePost>();
	private Context context;

	public ImagePostAdapter(Context context) {
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null)
			convertView = LayoutInflater.from(this.context).inflate(R.layout.post_row, null);
		
		ImageView thumbnail = (ImageView) convertView.findViewById(R.id.row_icon);
		
		ImageLoader imgLoader = new ImageLoader(this.context);
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, context.getResources().getDisplayMetrics());
		imgLoader.displayImage(getItem(position).getImageURL(), R.drawable.actionbar_background, thumbnail, px);
		
		TextView title = (TextView) convertView.findViewById(R.id.row_title);
		title.setText(getItem(position).getTitle());

		return convertView;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public ImagePost getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void add(ImagePost menuObject) {
		list.add(menuObject);
	}

	public void inputJSONObject(JSONObject result) {
		
		list = new ArrayList<ImagePost>();
		try {
			JSONArray array = result.getJSONArray("data");
			for(int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				
				ImagePost imagePost = new ImagePost();
				imagePost.setId(obj.getLong("id"));
				imagePost.setTitle(obj.getString("title"));
				imagePost.setImageId(obj.getLong("image"));
				list.add(imagePost);
			}
			
			this.notifyDataSetChanged();
		}
		catch(Exception ex) {}
	}


}