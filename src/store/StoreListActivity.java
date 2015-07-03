package store;

import main.MainActivity;
import shop.Shop;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dongdong.R;

public class StoreListActivity extends Activity {

	TextView title, floors;
	GridView gridView;
	private ImageButton btTopBack, btDownBack, btHome, btProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_list);
		title = (TextView) findViewById(R.id.storeListTitle);
		floors = (TextView) findViewById(R.id.floors);
		gridView = (GridView) findViewById(R.id.storeListGridView);
		ImageView nextFloor = (ImageView) findViewById(R.id.iv_nextfloor);
		ImageView preFloor = (ImageView) findViewById(R.id.iv_prefloor);
		
		setImgBtn();

		Intent intent = getIntent();
		String complex = intent.getStringExtra("complex");

		// setFont();
		final GetStoresData gsd = new GetStoresData(complex);
		// 임시로 GOOD for test
		final StorePageGridViewAdapter spAdapter = new StorePageGridViewAdapter("GOOD", this);
		gridView.setAdapter(spAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

				Intent intent = new Intent(getApplicationContext(), StoreInfoActivity.class);

				Shop shop = spAdapter.getShop(position);
				intent.putExtra("SHOP", shop);
				startActivity(intent);

			}
		});

		/*
		 * 층수 계산해서 해놓기!
		 */
		nextFloor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				spAdapter.setFloor(2);

			}
		});
		preFloor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				spAdapter.setFloor(1);

			}
		});
	}

	public void setFont() {
		Typeface yoonGodic350 = Typeface.createFromAsset(getAssets(), "fonts/yoonGothic350.ttf");

		title.setTypeface(yoonGodic350);
		floors.setTypeface(yoonGodic350);
	}

	private void setImgBtn() {
		btTopBack = (ImageButton) findViewById(R.id.btnStoreListTopBack);
		btTopBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btDownBack = (ImageButton) findViewById(R.id.btnStoreListDownBack);
		btDownBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		btHome = (ImageButton) findViewById(R.id.btnStoreListHome);
		btHome.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

		btProduct = (ImageButton) findViewById(R.id.btnStoreListProduct);
		btProduct.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), FavoriteProductListActivity.class);
				startActivity(intent);
			}
		});
	}

}
