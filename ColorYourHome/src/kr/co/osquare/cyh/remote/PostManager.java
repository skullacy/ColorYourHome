package kr.co.osquare.cyh.remote;

import kr.co.osquare.cyh.CYHApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class PostManager {

	private static PostManager _instance = null;

	public static PostManager getInstance() {
		if (_instance == null)
			_instance = new PostManager();
		return _instance;
	}

	private JSONArray array;
	public JSONArray getJSONArray() {
		return this.array;
	}
	
	private OnPostLoadListener onPostLoadListener = null;

	public void setOnPostLoadListener(OnPostLoadListener listener) {
		onPostLoadListener = listener;
	}
	
	public void postLoadAsync() {
		RequestParams params = new RequestParams();

		AsyncHttpClient client = new AsyncHttpClient();
		client.setTimeout(10000);
		client.post(CYHApplication.API_URL + "/api/posts/list.json", params, onPostLoadCompleted);
	}

	public void postLoadAsync(int categoryId) {
		RequestParams params = new RequestParams();
		params.put("category", String.valueOf(categoryId));

		AsyncHttpClient client = new AsyncHttpClient();
		client.setTimeout(10000);
		client.post(CYHApplication.API_URL + "/api/posts/list.json", params, onPostLoadCompleted);
	}

	private JsonHttpResponseHandler onPostLoadCompleted = new JsonHttpResponseHandler() {

		@Override
		public void onSuccess(JSONObject result) {
			try {
				if (result != null && result.getBoolean("success")) {
					System.out.println(">>>" + result.getJSONArray("data"));
					PostManager.this.array = result.getJSONArray("data");
				}
				
				if (onPostLoadListener != null)
					onPostLoadListener.onPostLoadComplete(true, result);

			} catch (Exception ex) {
				ex.printStackTrace();
				if (onPostLoadListener != null)
					onPostLoadListener.onPostLoadComplete(false, result);
			}
		}

		@Override
		public void onFailure(Throwable arg0, String arg1) {
			System.out.println("onFailure : " + arg1);

			if (onPostLoadListener != null)
				onPostLoadListener.onPostLoadComplete(false, null);

		}
	};
	
	public interface OnPostLoadListener {
		public abstract void onPostLoadComplete(boolean success, JSONObject result);
	}

}
