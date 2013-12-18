package kr.co.osquare.cyh.activity;

import com.actionbarsherlock.app.ActionBar;
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
public class DetailActivity extends Activity {

	/** Called when the activity is first created. */
	@SuppressLint("NewApi")
	public SlidingDrawer drawer;
	
	Context context;
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.detail_activity);
	    // TODO Auto-generated method stub
	    
	   android.app.ActionBar actionBar = getActionBar();
	   actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
	   actionBar.setIcon(getResources().getDrawable(R.drawable.actionbar_button_menu));
	   View v = getLayoutInflater().inflate(R.layout.actionbar_header, null);
	   actionBar.setHomeButtonEnabled(true);
	   actionBar.setCustomView(v, new android.app.ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.MATCH_PARENT,android.app.ActionBar.LayoutParams.MATCH_PARENT));
	   actionBar.setDisplayShowCustomEnabled(true);
	   
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

}
