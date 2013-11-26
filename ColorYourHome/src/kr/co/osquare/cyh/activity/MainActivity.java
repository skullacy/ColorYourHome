package kr.co.osquare.cyh.activity;

import kr.co.osquare.cyh.R;
import kr.co.osquare.cyh.remote.CategoryManager;
import kr.co.osquare.cyh.remote.CategoryManager.OnCategoryLoadListener;

import org.OpenUDID.OpenUDID_manager;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity implements OnCategoryLoadListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		
		OpenUDID_manager.sync(this.getApplicationContext());

		Handler mHandler = new Handler();
		mHandler.postDelayed(mRunnable, 2000);
	}

	Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
			CategoryManager cManager = CategoryManager.getInstance();
			cManager.setOnCategoryLoadListener(MainActivity.this);
			cManager.categoryLoadAsync();
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCategoryLoadComplete(boolean success, JSONObject result) {

		System.out.println("asdf");
		Intent intent = new Intent(getApplicationContext(), MainFrameActivity.class);
		startActivity(intent);
		
		finish();
		
	}

}
