package kr.co.osquare.cyh.remote;

import kr.co.osquare.cyh.CYHApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class CategoryManager {

	private static CategoryManager _instance = null;

	public static CategoryManager getInstance() {
		if (_instance == null)
			_instance = new CategoryManager();
		return _instance;
	}

	private JSONArray array;
	public JSONArray getJSONArray() {
		return this.array;
	}
	
	private OnCategoryLoadListener onCategoryLoadListener = null;

	public void setOnCategoryLoadListener(OnCategoryLoadListener listener) {
		onCategoryLoadListener = listener;
	}
	
	public void categoryLoadAsync() {
		RequestParams params = new RequestParams();

		AsyncHttpClient client = new AsyncHttpClient();
		client.setTimeout(10000);
		client.post(CYHApplication.API_URL + "/api/category/list.json", params, onCategoryLoadCompleted);
	}

	private JsonHttpResponseHandler onCategoryLoadCompleted = new JsonHttpResponseHandler() {

		@Override
		public void onSuccess(JSONObject result) {
			try {
				if (result != null && result.getBoolean("success")) {
					System.out.println(result.getJSONArray("data"));
					CategoryManager.this.array = result.getJSONArray("data");
				}
				
				if (onCategoryLoadListener != null)
					onCategoryLoadListener.onCategoryLoadComplete(true, result);

			} catch (Exception ex) {
				ex.printStackTrace();
				if (onCategoryLoadListener != null)
					onCategoryLoadListener.onCategoryLoadComplete(false, result);
			}
		}

		@Override
		public void onFailure(Throwable arg0, String arg1) {
			System.out.println("onFailure : " + arg1);

			if (onCategoryLoadListener != null)
				onCategoryLoadListener.onCategoryLoadComplete(false, null);

		}
	};
	
	public interface OnCategoryLoadListener {
		public abstract void onCategoryLoadComplete(boolean success, JSONObject result);
	}

}
