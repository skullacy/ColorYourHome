package kr.co.osquare.cyh.activity;

import kr.co.osquare.cyh.BaseActivity;
import kr.co.osquare.cyh.FourFragment;
import kr.co.osquare.cyh.R;
import kr.co.osquare.cyh.ThreeFragment;
import kr.co.osquare.cyh.TwoFragment;
import kr.co.osquare.cyh.R.id;
import kr.co.osquare.cyh.R.layout;
import kr.co.osquare.cyh.R.string;
import kr.co.osquare.cyh.fragment.CategoryPostsFragment;
import kr.co.osquare.cyh.fragment.PostsFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainFrameActivity extends BaseActivity {
	
	private static MainFrameActivity _instance;
	public static MainFrameActivity getInstance() {
		return _instance;
	}
	
	int mCurrentFragmentIndex;
	public final static int FRAGMENT_ONE = 0;
	public final static int FRAGMENT_TWO = 1;
	public final static int FRAGMENT_THREE = 2;
	public final static int FRAGMENT_FOUR = 3;

	public MainFrameActivity() {
		super(R.string.viewpager);
		_instance = this;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		setContentView(R.layout.view_container);
		fragmentReplace(0);
	}

	public void fragmentReplace(int reqNewFragmentIndex) {

		Fragment newFragment = null;

		newFragment = getFragment(reqNewFragmentIndex);

		// replace fragment
		final FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.replace(R.id.view_container, newFragment);

		// Commit the transaction
		transaction.commit();

	}
	
	public void fragmentReplace(Fragment newFragment) {
		final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.view_container, newFragment);
		transaction.commit();
	}

	public Fragment getFragment(int idx) {
		Fragment newFragment = null;

		switch (idx) {
		case FRAGMENT_ONE:
			newFragment = new PostsFragment();
			break;
		case FRAGMENT_TWO:
			newFragment = new TwoFragment();
			break;
		case FRAGMENT_THREE:
			newFragment = new ThreeFragment();
			break;
		case FRAGMENT_FOUR:
			newFragment = new FourFragment();
			break;

		default:
			break;
		}

		return newFragment;
	}
	
	public Fragment getCategoryFragment(int idx) {
		CategoryPostsFragment newFragment = new CategoryPostsFragment();
		newFragment.categoryId = idx;
		return newFragment;
	}

}
