package kr.co.osquare.cyh.activity;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.androidhive.imagefromurl.ImageLoader;


import kr.co.osquare.cyh.*;

import kr.co.osquare.cyh.adapter.ImagePostAdapter;
import kr.co.osquare.cyh.model.ImagePost;
import kr.co.osquare.cyh.model.ImagePostToTransfer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class DetailActivity extends BaseActivity {
	
	private static DetailActivity _instance;
	public static DetailActivity getInstance() {
		return _instance;
	}
	
	public DetailActivity() {
		super(R.string.viewpager, false);
		_instance = this;
	}

	@SuppressLint("NewApi")
	public SlidingDrawer drawer;
	
	Context context;
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.detail_activity);
	    // TODO Auto-generated method stub
	    
	   ImageView image = (ImageView)findViewById(R.id.detail_image);
	   Log.e("DetailActivity", image.toString());
	   Intent i = getIntent();
	   Bundle b=i.getExtras();
	   int position = b.getInt("position");
	   
	   ImagePost imgdata = ImagePostToTransfer.getImagePost();
	   Log.e("DetailActivity", imgdata.getImageURL());
	   ImageLoader imgLoader = new ImageLoader(this);
	   int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
	   imgLoader.displayImage(imgdata.getImageURL(), R.drawable.actionbar_background, image, px);
	   
	   ImageButton drawer_btn = (ImageButton)findViewById(R.id.handle);
	   drawer_btn.setBackgroundDrawable(null);
	   
	   ImageButton likebtn = (ImageButton)findViewById(R.id.like);
	   likebtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Toast liketoast = Toast.makeText(getApplicationContext(), "like", 0);
				ImageView biglike = new ImageView(getApplicationContext());
				biglike.setImageResource(R.drawable.biglike);
				liketoast.setView(biglike);
				liketoast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL,Gravity.CENTER_VERTICAL);
				liketoast.show();
			}
	   });
	   
	   ImageButton sharebtn = (ImageButton)findViewById(R.id.share);
	   sharebtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Toast liketoast = Toast.makeText(getApplicationContext(), "like", 0);
				ImageView biglike = new ImageView(getApplicationContext());
				biglike.setImageResource(R.drawable.bigsave);
				liketoast.setView(biglike);
				liketoast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL,Gravity.CENTER_VERTICAL);
				liketoast.show();
			}
	   });
	   
	  
	   
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
    	MenuItem item2 = menu.add(0, 1, 0, "");
        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item2.setIcon(R.drawable.actionbar_button_blank);
        
       
        return true;
	}
	

}
