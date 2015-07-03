package search;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.MainActivity;
import product.Product;
import searchtypes.Category;
import searchtypes.Keyword;
import store.FavoriteProductListActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dongdong.R;

public class SearchViewActivity extends Activity {
	String[] resultPaths;
	String keyword, category;
	EditText et_search_bar;
	ImageView iv_search_btn;
	GridView gridView;
	LinearLayout ll_search_bar;
	ArrayList<Integer> itemsId;
	ArrayList<Product> itemsData;
	ArrayList<String> itemList = new ArrayList<String>();
	String userid;
	private ImageButton btDownBack, btHome, btProduct;

	protected void onStart() {
		super.onStart();
		ll_search_bar.setBackgroundResource(R.drawable.image_search_up);
		et_search_bar.setBackgroundResource(R.drawable.main_bar_search);
		iv_search_btn.setImageResource(R.drawable.image_search_bar_button);
	}

	protected void onStop() {
		super.onStop();
		ll_search_bar.setBackground(null);
		et_search_bar.setBackground(null);
		iv_search_btn.setImageDrawable(null);
		category = null; // 테스트해봐야됨 카테고리 정했다가 모두검색으로 할때 카테고리 안남아있게 하려고..
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_view);

		setImgBtn();
		Intent intent = getIntent();
		keyword = intent.getExtras().getString("keyword");
		category = intent.getExtras().getString("category", null);
		userid = intent.getExtras().getString("email");
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //

		itemsId = new ArrayList<Integer>();
		itemsData = new ArrayList<Product>();

		gridView = (GridView) findViewById(R.id.gridView);
		et_search_bar = (EditText) findViewById(R.id.et_search_bar);
		iv_search_btn = (ImageView) findViewById(R.id.iv_search_btn);
		ll_search_bar = (LinearLayout) findViewById(R.id.ll_search_bar);

		et_search_bar.setPadding(100, 0, 0, 0);

		String fontpath = "fonts/yoonGothic310.ttf";
		Typeface tf = Typeface.createFromAsset(SearchViewActivity.this.getResources().getAssets(), fontpath);

		et_search_bar.setTypeface(tf);

		et_search_bar.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					// search();

					String getTxt = et_search_bar.getText().toString();
					if (getTxt.getBytes().length <= 0) {
						Toast.makeText(getApplicationContext(), "검색어를 입력해 주세요", Toast.LENGTH_LONG).show();

					} else if (Namevalidate(getTxt) == false) {
						{
							Toast.makeText(getApplicationContext(), "검색어를 확인해 주세요", Toast.LENGTH_LONG).show();
						}
					} else {
						String keyword = et_search_bar.getText().toString();

						Intent intent = new Intent(getApplicationContext(), search.SearchViewActivity.class);
						intent.putExtra("keyword", keyword);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					}
				}
				return false;
			}
		});
		// !!
		search();

		gridView.setOnItemClickListener(myOnItemClickListener);

		iv_search_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String getTxt = et_search_bar.getText().toString();
				if (Namevalidate(getTxt) == false) {
					Toast.makeText(getApplicationContext(), "검색어를 확인해 주세요", Toast.LENGTH_LONG).show();
					return;
				}
				if (getTxt.getBytes().length <= 0) {
					Toast.makeText(getApplicationContext(), "검색어를 입력해 주세요", Toast.LENGTH_LONG).show();

				} else {
					// TODO Auto-generated method stub
					String keyword = et_search_bar.getText().toString();

					Intent intent = new Intent(getApplicationContext(), search.SearchViewActivity.class);
					intent.putExtra("keyword", keyword);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
			}
		});

	}

	private static final String Name_PATTERN = "^(?=.*[가-힣a-zA-Z0-9])(?=[\\S]+$).{0,10}$";

	public boolean Namevalidate(final String hex) {
		Pattern pattern = Pattern.compile(Name_PATTERN);
		Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent = new Intent(getApplicationContext(), clothes.NewDetailActivity.class);
			Product p = (Product) parent.getItemAtPosition(position);
			intent.putExtra("id", p.getid());
			intent.putExtra("product", p.toString());
			intent.putExtra("email", userid);
			startActivity(intent);
		}
	};

	private void search() {
		if (category != null) {
			SearchPageGridViewAdapter ba = new SearchPageGridViewAdapter(new Category(category), getApplicationContext());
			gridView.setAdapter(ba);
			gridView.setOnItemClickListener(myOnItemClickListener);

			category = null; // 계속 남아있나해서..? 온스탑에 넣어야하나!?
		} else {
			SearchPageGridViewAdapter ba = new SearchPageGridViewAdapter(new Keyword(keyword), getApplicationContext());
			gridView.setAdapter(ba);
			gridView.setOnItemClickListener(myOnItemClickListener);
		}

	}

	private void setImgBtn() {
		btDownBack = (ImageButton) findViewById(R.id.btnSearchDownBack);
		btDownBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		btHome = (ImageButton) findViewById(R.id.btnSearchHome);
		btHome.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

		btProduct = (ImageButton) findViewById(R.id.btnSearchProduct);
		btProduct.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), FavoriteProductListActivity.class);
				startActivity(intent);
			}
		});
	}

}