package category;

import java.util.regex.Pattern;

import search.SearchViewActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dongdong.R;

public class CategoryManActivity extends Activity {

	LinearLayout linearlayout_man;
	LinearLayout ll_search_bar;
	LinearLayout ll_category_bg;
	EditText et_search_bar;
	ImageView iv_search_btn;
	ImageView iv_coat;
	ImageView iv_top;
	ImageView iv_pants;
	ImageView iv_bag;
	ImageView iv_accessory;
	ImageView iv_shoes;
	ImageView iv_backpage, iv_home, iv_wish;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_man);
		ll_search_bar = (LinearLayout) findViewById(R.id.ll_search_bar);
		et_search_bar = (EditText) findViewById(R.id.et_search_bar);
		iv_search_btn = (ImageView) findViewById(R.id.iv_search_btn);
		ll_category_bg = (LinearLayout) findViewById(R.id.ll_category_bg);
		iv_backpage = (ImageView) findViewById(R.id.iv_backpage);
		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_wish = (ImageView) findViewById(R.id.iv_wish);
		iv_coat = (ImageView) findViewById(R.id.iv_coat);
		iv_top = (ImageView) findViewById(R.id.iv_top);
		iv_pants = (ImageView) findViewById(R.id.iv_pants);
		iv_bag = (ImageView) findViewById(R.id.iv_bag);
		iv_accessory = (ImageView) findViewById(R.id.iv_accessory);
		iv_shoes = (ImageView) findViewById(R.id.iv_shoes);

		et_search_bar.setPadding(100, 0, 0, 0);

		String fontpath = "fonts/yoonGothic310.ttf";
		Typeface tf = Typeface.createFromAsset(CategoryManActivity.this
				.getResources().getAssets(), fontpath);

		et_search_bar.setTypeface(tf);

		et_search_bar.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					// search();
					String getTxt = et_search_bar.getText().toString();
					if (getTxt.getBytes().length <= 0) {
						Toast.makeText(getApplicationContext(),
								"寃��깋�뼱瑜� �엯�젰�븯�꽭�슂", Toast.LENGTH_LONG)
								.show();
					} else {
						String keyword = et_search_bar.getText().toString();

						Intent intent = new Intent(getApplicationContext(),
								search.SearchViewActivity.class);
						intent.putExtra("keyword", keyword);
						startActivity(intent);
					}
				}
				return false;
			}
		});

		iv_search_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String getTxt = et_search_bar.getText().toString();
				if (getTxt.getBytes().length <= 0) {
					Toast.makeText(getApplicationContext(),
							"寃��깋�뼱瑜� �엯�젰�븯�꽭�슂", Toast.LENGTH_LONG).show();
				} else {
					// TODO Auto-generated method stub
					String keyword = et_search_bar.getText().toString();

					Intent intent = new Intent(getApplicationContext(),
							search.SearchViewActivity.class);
					intent.putExtra("keyword", keyword);
					startActivity(intent);
				}
			}
		});

		TextWatcher watcher = new TextWatcher() {
			String txt;

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				txt = s.toString();
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				int length = s.toString().length();
				if (length > 0) {
					Pattern ps = Pattern
							.compile("^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\u318D\u119E\u11A2\u2022\u2025a\u00B7\uFE55]+$");
					if (!ps.matcher(s).matches()) {
						et_search_bar.setText(txt);
						et_search_bar.setSelection(et_search_bar.length());
					}
				}
			}
		};
		et_search_bar.addTextChangedListener(watcher);
		iv_backpage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		iv_home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						main.MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

		iv_coat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SearchViewActivity.class);
				intent.putExtra("category", "Dress");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		iv_top.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SearchViewActivity.class);
				intent.putExtra("category", "Dress");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		iv_pants.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SearchViewActivity.class);
				intent.putExtra("category", "Dress");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		iv_bag.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SearchViewActivity.class);
				intent.putExtra("category", "Dress");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		iv_accessory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SearchViewActivity.class);
				intent.putExtra("category", "Dress");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		iv_shoes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SearchViewActivity.class);
				intent.putExtra("category", "Dress");
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	protected void onStart() {
		super.onStart();
		et_search_bar.setBackgroundResource(R.drawable.gentle_bar_search);
		iv_search_btn.setImageResource(R.drawable.gentle_btn_search);
		iv_coat.setImageResource(R.drawable.gentle_btn_outer);
		iv_top.setImageResource(R.drawable.gentle_btn_tops);
		iv_pants.setImageResource(R.drawable.gentle_btn_bottoms);
		iv_bag.setImageResource(R.drawable.gentle_btn_bags);
		iv_accessory.setImageResource(R.drawable.gentle_btn_acc);
		iv_shoes.setImageResource(R.drawable.gentle_btn_shoes);
		ll_search_bar.setBackgroundResource(R.drawable.gentle_bar_searchbg);
		iv_backpage.setImageResource(R.drawable.upload_btn_back2);
		iv_home.setImageResource(R.drawable.upload_btn_home);
		iv_wish.setImageResource(R.drawable.upload_btn_qna);
	}

	protected void onStop() {
		super.onStop();
		et_search_bar.setBackground(null);
		iv_search_btn.setImageDrawable(null);

		iv_coat.setImageDrawable(null);
		iv_top.setImageDrawable(null);
		iv_pants.setImageDrawable(null);
		iv_bag.setImageDrawable(null);
		iv_accessory.setImageDrawable(null);
		iv_shoes.setImageDrawable(null);
		ll_search_bar.setBackground(null);

		iv_backpage.setImageDrawable(null);
		iv_home.setImageDrawable(null);
		iv_wish.setImageDrawable(null);

	}

	public void onWindowFocusChanged(boolean hasFocus) {
		linearlayout_man = (LinearLayout) findViewById(R.id.linearlayout_man);

		double weight = linearlayout_man.getWidth();

		double height = ll_category_bg.getHeight();

		iv_coat.setPadding((int) (weight * 0.111), (int) (height * 0.053),
				(int) (weight * 0.037), (int) (height * 0.027));
		iv_pants.setPadding((int) (weight * 0.111), (int) (height * 0.027),
				(int) (weight * 0.037), (int) (height * 0.027));
		iv_bag.setPadding((int) (weight * 0.111), (int) (height * 0.027),
				(int) (weight * 0.037), (int) (height * 0.053));
		iv_top.setPadding((int) (weight * 0.037), (int) (height * 0.053),
				(int) (weight * 0.111), (int) (height * 0.027));
		iv_shoes.setPadding((int) (weight * 0.037), (int) (height * 0.027),
				(int) (weight * 0.111), (int) (height * 0.027));
		iv_accessory.setPadding((int) (weight * 0.037), (int) (height * 0.027),
				(int) (weight * 0.111), (int) (height * 0.053));

	}

}