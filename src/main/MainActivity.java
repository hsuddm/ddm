package main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mypage.MyPageActivity;
import notice.GetNoticeData;
import notice.Notice;
import notice.NoticeListViewAdapter;
import product.Product;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.dongdong.R;

/**
 * Created by jin gi on 2015-04-22.
 */

public class MainActivity extends Activity implements OnTouchListener {
	public static int countIndexes = 6;
	ViewFlipper ll_flipper;
	ImageView[] indexButtons;
	View[] views;
	ViewFlipper m_viewFlipper;
	float downX;
	float upX;
	Context context;

	private int m_nPreTouchPosX = 0;
	TextView hidebar;
	TextView hidebar1;
	TextView hidebar2;

	Animation t_up;
	Animation t_down;

	LinearLayout ll_search;

	EditText et_search_bar;
	TextView listviewfont;
	ImageView iv_notice_btn;
	ImageView iv_search_btn;
	ImageView iv_man_btn;
	ImageView iv_mypage_btn;
	ImageView iv_woman_btn;
	ImageView iv_category_btn;

	// ImageView view1, view2, view3, view4, view5, view6;
	ImageView iv_like_btn;
	ImageView backpage;
	ImageView home;
	ImageView cart;

	LinearLayout ll_notice;

	ListView lv_notice_list = null;
	ListView lv_notice_main;
//	NoticeAdapter mNoticeAdapter = null;
//	NoticeAdapter mNoticeAdapter1 = null;
//	ArrayList<Notice> mData = null;
//	ArrayList<Notice> mData1 = null;

	LinearLayout ll_category;

	int numNotices;
	Product product;
	boolean isPageOpen = true;
	boolean guestPageOpen = true;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		context = this.getApplicationContext();
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.main_page, null, true);
		ll_flipper = (ViewFlipper) findViewById(R.id.ll_flipper);
		m_viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		m_viewFlipper.setOnTouchListener(this);
		ll_flipper.setOnTouchListener(this);
		iv_notice_btn = (ImageView) findViewById(R.id.iv_notice_btn);

		Intent intent = getIntent();
		final String id = intent.getStringExtra("email");
		Toast.makeText(this, id, Toast.LENGTH_LONG).show();

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);

		indexButtons = new ImageView[countIndexes];
		views = new ImageView[countIndexes];

		for (int i = 0; i < countIndexes; i++) {
			indexButtons[i] = new ImageView(context);
			ImageView curView = new ImageView(context);
			views[i] = curView;
			m_viewFlipper.addView(views[i], param);
			ll_flipper.addView(indexButtons[i], params);
		}

		m_viewFlipper.startFlipping();
		ll_flipper.startFlipping();

//		mData = new ArrayList<Notice>();

//		for (int i = 0; i < 7; i++) {
//			Notice notice = new Notice();
//			notice.mNotice = "[공지사항]   " + i;
//			mData.add(notice);
//		}
//
//		mNoticeAdapter = new NoticeAdapter(this, mData);
//		lv_notice_list = (ListView) findViewById(R.id.lv_notice_list);
//		lv_notice_list.setAdapter(mNoticeAdapter);
//		lv_notice_list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		lv_notice_list.setDivider(new ColorDrawable(Color.BLACK));
//		lv_notice_list.setDividerHeight(1);
//
//		mData1 = new ArrayList<Notice>();

//		for (int i = 0; i < 2; i++) {
//			Notice notice = new Notice();
//			notice.mNotice = "[공지사항]   " + i;
//			mData1.add(notice);
//		}
//		mNoticeAdapter1 = new NoticeAdapter(this, mData1);
//		lv_notice_main = (ListView) findViewById(R.id.lv_notice_main);
//		lv_notice_main.setAdapter(mNoticeAdapter1);
//		lv_notice_main.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		lv_notice_main.setDivider(new ColorDrawable(Color.BLACK));
//		lv_notice_main.setDividerHeight(1);
//
		ll_notice = (LinearLayout) findViewById(R.id.ll_notice);

		ll_category = (LinearLayout) findViewById(R.id.ll_category);
		ll_search = (LinearLayout) findViewById(R.id.ll_search);

		iv_woman_btn = (ImageView) findViewById(R.id.iv_woman_btn);
		iv_mypage_btn = (ImageView) findViewById(R.id.iv_mypage_btn);
		iv_category_btn = (ImageView) findViewById(R.id.iv_category_btn);
		iv_man_btn = (ImageView) findViewById(R.id.iv_man_btn);

		et_search_bar = (EditText) findViewById(R.id.et_search_bar);
		iv_search_btn = (ImageView) findViewById(R.id.iv_search_btn);
		backpage = (ImageView) findViewById(R.id.backpage);
		home = (ImageView) findViewById(R.id.home);

		cart = (ImageView) findViewById(R.id.cart);

		t_up = AnimationUtils.loadAnimation(this, R.anim.translate_up);
		t_down = AnimationUtils.loadAnimation(this, R.anim.translate_down);

		et_search_bar.setPadding(100, 0, 0, 0);

		String fontpath1 = "fonts/yoonGothic310.ttf";
		Typeface tf1 = Typeface.createFromAsset(MainActivity.this
				.getResources().getAssets(), fontpath1);

		et_search_bar.setTypeface(tf1);

		iv_man_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						category.CategoryManActivity.class);
				startActivity(intent);

			}
		});

		iv_category_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						category.CategoryShopMallActivity.class);
				startActivity(intent);

			}
		});

		iv_woman_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						category.CategoryWomanActivity.class);
				startActivity(intent);
			}
		});

		et_search_bar.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					// search();

					String getTxt = et_search_bar.getText().toString();
					if (getTxt.getBytes().length <= 0) {
						Toast.makeText(getApplicationContext(), "검색어를 입력해 주세요",
								Toast.LENGTH_LONG).show();

					} else if (Namevalidate(getTxt) == false) {
						{
							Toast.makeText(getApplicationContext(),
									"검색어를 확인해 주세요", Toast.LENGTH_LONG).show();
						}
					} else {
						String keyword = et_search_bar.getText().toString();

						Intent intent = new Intent(getApplicationContext(),
								search.SearchViewActivity.class);
						intent.putExtra("keyword", keyword);
						intent.putExtra("email", id);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					}
				}
				return false;
			}
		});

		iv_notice_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isPageOpen == true) {
					lv_notice_list.startAnimation(t_up);
					ll_category.setVisibility(View.GONE);
					lv_notice_main.setVisibility(View.GONE);
					ll_notice.setVisibility(View.VISIBLE);
					isPageOpen = false;
				} else {
					lv_notice_list.startAnimation(t_down);
					ll_notice.setVisibility(View.GONE);
					lv_notice_main.setVisibility(View.VISIBLE);
					ll_category.setVisibility(View.VISIBLE);
					isPageOpen = true;

				}
			}
		});

		ImageView backpage = (ImageView) findViewById(R.id.backpage);
		backpage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"�룯�뜃由� 占쎌넅筌롳옙 占쎌뿯占쎈빍占쎈뼄", Toast.LENGTH_LONG).show();
			}
		});
		ImageView home = (ImageView) findViewById(R.id.home);
		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "占쎌냳 占쎌넅筌롳옙 占쎌뿯占쎈빍占쎈뼄",
						Toast.LENGTH_LONG).show();
			}
		});

		iv_search_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String getTxt = et_search_bar.getText().toString();
				if (Namevalidate(getTxt) == false) {
					Toast.makeText(getApplicationContext(), "검색어를 확인해 주세요",
							Toast.LENGTH_LONG).show();
					return;
				}
				if (getTxt.getBytes().length <= 0) {
					Toast.makeText(getApplicationContext(), "검색어를 입력해 주세요",
							Toast.LENGTH_LONG).show();

				} else {
					// TODO Auto-generated method stub
					String keyword = et_search_bar.getText().toString();

					Intent intent = new Intent(getApplicationContext(),
							search.SearchViewActivity.class);
					intent.putExtra("keyword", keyword);
					intent.putExtra("email", id);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
			}
		});
		iv_mypage_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(),
						MyPageActivity.class);
				startActivity(intent);

			}
		});

		if (!isNetworkConnected(this)) {
			new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("占쎄퐬占쎈뱜占쎌뜖占쎄쾿 占쎈염野껓옙 占쎌궎�몴占�")
					.setMessage(
							"占쎄퐬占쎈뱜占쎌뜖占쎄쾿 占쎈염野껓옙 占쎄맒占쎄묶 占쎌넇占쎌뵥 占쎌뜎 占쎈뼄占쎈뻻 占쎈뻻占쎈즲占쎈퉸 雅뚯눘�뼏占쎈뻻占쎌뒄.")
					.setPositiveButton("占쎌넇占쎌뵥",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								}
							}).show();
		}
	}
	
//	private void setNotice(){
//		GetNoticeData gnd = new GetNoticeData();
//		ArrayList<Notice> noticeData = gnd.excute();
//		
//		lv_notice_main.setAdapter(new NoticeListViewAdapter(MainActivity.this
//				.getApplicationContext(), noticeData));
//		
//	}

	private static final String Name_PATTERN = "^(?=.*[가-힣a-zA-Z0-9])(?=[\\S]+$).{0,10}$";

	public boolean Namevalidate(final String hex) {
		Pattern pattern = Pattern.compile(Name_PATTERN);
		Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	@Override
	protected void onStart() {

		super.onStart();
//		setNotice();
		views[0].setBackgroundResource(R.drawable.main_banner_1);
		views[1].setBackgroundResource(R.drawable.main_banner_2);
		views[2].setBackgroundResource(R.drawable.main_banner_3);
		views[3].setBackgroundResource(R.drawable.main_banner_4);
		views[4].setBackgroundResource(R.drawable.main_banner_5);
		views[5].setBackgroundResource(R.drawable.main_banner_6);
		indexButtons[0].setBackgroundResource(R.drawable.step1);
		indexButtons[1].setBackgroundResource(R.drawable.step2);
		indexButtons[2].setBackgroundResource(R.drawable.step3);
		indexButtons[3].setBackgroundResource(R.drawable.step4);
		indexButtons[4].setBackgroundResource(R.drawable.step5);
		indexButtons[5].setBackgroundResource(R.drawable.step6);
		et_search_bar.setBackgroundResource(R.drawable.main_bar_search);
		iv_search_btn.setImageResource(R.drawable.main_btn_search);
		iv_man_btn.setImageResource(R.drawable.main_btn_gentle);
		iv_woman_btn.setImageResource(R.drawable.main_btn_lady);
		iv_mypage_btn.setImageResource(R.drawable.main_btn_mypage);
		iv_category_btn.setImageResource(R.drawable.main_btn_category);
		iv_notice_btn.setImageResource(R.drawable.main_btn_noticedot);
		backpage.setImageResource(R.drawable.upload_btn_back2);
		backpage.setScaleType(ScaleType.FIT_CENTER);
		home.setImageResource(R.drawable.upload_btn_home);
		home.setScaleType(ScaleType.FIT_CENTER);
		cart.setImageResource(R.drawable.upload_btn_qna);
		cart.setScaleType(ScaleType.FIT_CENTER);
		ll_search.setBackgroundResource(R.drawable.main_bar_searchbg);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onStop() {
		super.onStop();
		views[0].setBackground(null);
		views[1].setBackground(null);
		views[2].setBackground(null);
		views[3].setBackground(null);
		views[4].setBackground(null);
		views[5].setBackground(null);
		indexButtons[0].setBackground(null);
		indexButtons[1].setBackground(null);
		indexButtons[2].setBackground(null);
		indexButtons[3].setBackground(null);
		indexButtons[4].setBackground(null);
		indexButtons[5].setBackground(null);
		et_search_bar.setBackground(null);
		iv_search_btn.setImageDrawable(null);
		iv_man_btn.setImageDrawable(null);
		iv_woman_btn.setImageDrawable(null);
		iv_mypage_btn.setImageDrawable(null);
		iv_category_btn.setImageDrawable(null);
		ll_search.setBackgroundDrawable(null);
		backpage.setImageDrawable(null);
		home.setImageDrawable(null);
		cart.setImageDrawable(null);

	}

	public void onWindowFocusChanged(boolean hasFocus) {

		double height = ll_search.getHeight();
		double width = ll_search.getWidth();
		double ll_height = ll_category.getHeight();
		ll_search
				.setPadding(0, (int) (height * 0.21), 0, (int) (height * 0.21));

		iv_man_btn.setPadding((int) (width * 0.10), (int) (ll_height * 0.11),
				(int) (width * 0.04), (int) (ll_height * 0.04));
		iv_category_btn.setPadding((int) (width * 0.10),
				(int) (ll_height * 0.04), (int) (width * 0.04),
				(int) (ll_height * 0.11));
		iv_woman_btn.setPadding((int) (width * 0.04), (int) (ll_height * 0.11),
				(int) (width * 0.10), (int) (ll_height * 0.04));
		iv_mypage_btn.setPadding((int) (width * 0.04),
				(int) (ll_height * 0.04), (int) (width * 0.10),
				(int) (ll_height * 0.11));

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			new AlertDialog.Builder(this)
					.setTitle("�넫�굝利�")
					.setMessage("�넫�굝利� 占쎈릭占쎈뻻野껋쥙�뮸占쎈빍繹먲옙?")
					.setPositiveButton("占쎌굙",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									moveTaskToBack(true);
									finish();
									android.os.Process
											.killProcess(android.os.Process
													.myPid());
								}
							}).setNegativeButton("占쎈툡占쎈빍占쎌궎", null).show();
			return false;
		default:
			return false;
		}
	}

	public boolean isNetworkConnected(Context context) {
		boolean isConnected = false;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mobile.isConnected() || wifi.isConnected()) {
			isConnected = true;
		} else {
			isConnected = false;
		}
		return isConnected;
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (v != m_viewFlipper)
			return false;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			downX = event.getX();
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			upX = event.getX();
			if (upX < downX) {
				m_viewFlipper.stopFlipping();
				ll_flipper.stopFlipping();
				m_viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
						context, R.anim.push_left_in));
				m_viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
						context, R.anim.push_left_out));
				m_viewFlipper.showPrevious();
				ll_flipper.showPrevious();
			} else if (upX > downX) {
				m_viewFlipper.stopFlipping();
				ll_flipper.stopFlipping();
				m_viewFlipper.setInAnimation(AnimationUtils.loadAnimation(
						context, R.anim.push_right_in));
				m_viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(
						context, R.anim.push_right_out));
				m_viewFlipper.showPrevious();
				ll_flipper.showPrevious();
			}
		}
		m_viewFlipper.startFlipping();
		ll_flipper.startFlipping();
		return true;
	}
}