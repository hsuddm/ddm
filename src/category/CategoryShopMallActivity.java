package category;

import java.util.regex.Pattern;

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

public class CategoryShopMallActivity extends Activity {

	LinearLayout linearlayout_shop_mall, ll_search_bar, ll_category_bg;
	EditText et_search_bar;
	ImageView iv_search_btn, iv_backpage, iv_home, iv_wish, iv_storelist_apm,
			iv_storelist_good, iv_storelist_mac, iv_storelist_migliore,
			iv_storelist_lotte, iv_storelist_ready;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_shopping_mall);

		ll_search_bar = (LinearLayout) findViewById(R.id.ll_search_bar);
		et_search_bar = (EditText) findViewById(R.id.et_search_bar);
		iv_search_btn = (ImageView) findViewById(R.id.iv_search_btn);
		ll_category_bg = (LinearLayout) findViewById(R.id.ll_category_bg);
		iv_backpage = (ImageView) findViewById(R.id.iv_backpage);
		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_wish = (ImageView) findViewById(R.id.iv_wish);
		iv_storelist_apm = (ImageView) findViewById(R.id.iv_storelist_apm);
		iv_storelist_good = (ImageView) findViewById(R.id.iv_storelist_good);
		iv_storelist_mac = (ImageView) findViewById(R.id.iv_storelist_mac);
		iv_storelist_migliore = (ImageView) findViewById(R.id.iv_storelist_migliore);
		iv_storelist_lotte = (ImageView) findViewById(R.id.iv_storelist_lotte);
		iv_storelist_ready = (ImageView) findViewById(R.id.iv_storelist_ready);
		linearlayout_shop_mall = (LinearLayout) findViewById(R.id.linearlayout_shop_mall);

		// et_search_bar.setPadding(100, 0, 0, 0);

		String fontpath = "fonts/yoonGothic310.ttf";
		Typeface tf = Typeface.createFromAsset(CategoryShopMallActivity.this
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

		iv_storelist_apm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						store.StoreListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("complex", "APM");
				startActivity(intent);
			}
		});
		iv_storelist_migliore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						store.StoreListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("complex", "MIGLIORE");
				startActivity(intent);
			}
		});
		iv_storelist_good.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						store.StoreListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("complex", "GOOD");
				startActivity(intent);
			}
		});
		iv_storelist_lotte.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						store.StoreListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("complex", "LOTTE");
				startActivity(intent);
			}
		});
		iv_storelist_mac.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						store.StoreListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("complex", "MAC");
				startActivity(intent);
			}
		});
		
	}

	protected void onStart() {
		super.onStart();
		et_search_bar.setBackgroundResource(R.drawable.gentle_bar_search1);
		iv_search_btn.setImageResource(R.drawable.gentle_btn_search1);
		iv_storelist_apm.setImageResource(R.drawable.storelist_apm);
		iv_storelist_migliore.setImageResource(R.drawable.storelist_migliore);
		iv_storelist_good.setImageResource(R.drawable.storelist_good);
		iv_storelist_lotte.setImageResource(R.drawable.storelist_lotte);
		iv_storelist_mac.setImageResource(R.drawable.storelist_mac);
		iv_storelist_ready.setImageResource(R.drawable.storelist_ready);
		ll_search_bar.setBackgroundResource(R.drawable.gentle_bar_searchbg);
		iv_backpage.setImageResource(R.drawable.upload_btn_back2);
		iv_home.setImageResource(R.drawable.upload_btn_home);
		iv_wish.setImageResource(R.drawable.upload_btn_qna);
	}

	protected void onStop() {
		super.onStop();
		et_search_bar.setBackground(null);
		iv_search_btn.setImageDrawable(null);
		iv_storelist_apm.setImageDrawable(null);
		iv_storelist_migliore.setImageDrawable(null);
		iv_storelist_good.setImageDrawable(null);
		iv_storelist_lotte.setImageDrawable(null);
		iv_storelist_mac.setImageDrawable(null);
		iv_storelist_ready.setImageDrawable(null);

		ll_search_bar.setBackground(null);

		iv_backpage.setImageDrawable(null);
		iv_home.setImageDrawable(null);
		iv_wish.setImageDrawable(null);

	}

	public void onWindowFocusChanged(boolean hasFocus) {

		double weight = linearlayout_shop_mall.getWidth();

		double height = ll_category_bg.getHeight();

		iv_storelist_apm.setPadding((int) (weight * 0.111),
				(int) (height * 0.053), (int) (weight * 0.037),
				(int) (height * 0.027));
		iv_storelist_good.setPadding((int) (weight * 0.111),
				(int) (height * 0.027), (int) (weight * 0.037),
				(int) (height * 0.027));
		iv_storelist_mac.setPadding((int) (weight * 0.111),
				(int) (height * 0.027), (int) (weight * 0.037),
				(int) (height * 0.053));
		iv_storelist_migliore.setPadding((int) (weight * 0.037),
				(int) (height * 0.053), (int) (weight * 0.111),
				(int) (height * 0.027));
		iv_storelist_lotte.setPadding((int) (weight * 0.037),
				(int) (height * 0.027), (int) (weight * 0.111),
				(int) (height * 0.027));
		iv_storelist_ready.setPadding((int) (weight * 0.037),
				(int) (height * 0.027), (int) (weight * 0.111),
				(int) (height * 0.053));

	}

}
