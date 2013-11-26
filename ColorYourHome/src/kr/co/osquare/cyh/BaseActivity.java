package kr.co.osquare.cyh;

import kr.co.osquare.cyh.fragment.MenuObjectsFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity {

	private int mTitleRes;
	protected ListFragment mFrag;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(mTitleRes);

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new MenuObjectsFragment();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}
	
		

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
		getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.actionbar_button_menu));
		getSupportActionBar().setHomeButtonEnabled(true);
		
		
		ActionBar bar = getSupportActionBar();
		View v = getLayoutInflater().inflate(R.layout.actionbar_header,null);
		bar.setCustomView(v, new ActionBar.LayoutParams(
				ActionBar.LayoutParams.MATCH_PARENT,
				ActionBar.LayoutParams.MATCH_PARENT));
		
		
		getSupportActionBar().setDisplayShowCustomEnabled(true);
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        
        //�׼ǹ� write �޴�
        MenuItem item2 = menu.add(0, 1, 0, "");
        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item2.setIcon(R.drawable.actionbar_button_write);
        
        
        
       
        return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		case 1:
			return true;
		}
		
      
		
		return super.onOptionsItemSelected((android.view.MenuItem) item);
	}

}
