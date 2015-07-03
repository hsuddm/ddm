package store;

import image.Image;
import product.Product;
import query.Server;
import searchtypes.SellerEmail;
import shop.Shop;
import shop.ShopPageGridViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import clothes.NewDetailActivity;

import com.example.dongdong.R;

public class StoreInfoActivity extends Activity {
	GridView gridView;
	TextView storeTitle, storeInfo, storeLocation, storeLocation2,
			storeProductNum, storeProductNum2, storeFavoriteNum,
			storeFavoriteNum2, storeTag;
	ImageButton backBtn;
	ImageView storeImageView;
	Shop shop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		setContentView(R.layout.activity_store_info);
		storeTitle = (TextView) findViewById(R.id.storeTitle);
		storeInfo = (TextView) findViewById(R.id.storeInfo);
		storeLocation = (TextView) findViewById(R.id.storeLocation);
		storeLocation2 = (TextView) findViewById(R.id.storeLocation2);
		storeProductNum = (TextView) findViewById(R.id.storeProductNum);
		storeProductNum2 = (TextView) findViewById(R.id.storeProductNum2);
		storeFavoriteNum = (TextView) findViewById(R.id.storeFavoriteNum);
		storeFavoriteNum2 = (TextView) findViewById(R.id.storeFavoriteNum2);
		storeTag = (TextView) findViewById(R.id.storeTag);

		backBtn = (ImageButton) findViewById(R.id.backBtn);

		storeImageView = (ImageView) findViewById(R.id.storeImage);

		setFont();

		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}

		});

		Intent intent = getIntent();
		shop = (Shop) intent.getSerializableExtra("SHOP");

		setStoreImage();

		gridView = (GridView) findViewById(R.id.gridView);

		final ShopPageGridViewAdapter spga = new ShopPageGridViewAdapter(
				new SellerEmail(shop.getEmail()), this);

		gridView.setAdapter(spga);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {

				Intent intent = new Intent(getApplicationContext(),
						NewDetailActivity.class);

				SharedPreferences pref = getSharedPreferences("pref",
						Activity.MODE_PRIVATE);
				String email = pref.getString("id", null);
				Product p = spga.getProduct(position);

				intent.putExtra("product", p.toString());
				intent.putExtra("id", p.getid());
				intent.putExtra("email", email);
				startActivity(intent);
			}

		});

		storeTitle.setText(shop.getShop_name());
		storeLocation.setText(shop.getFloor() + "-" + shop.getUnit());
		// storeInfo.setText(shop.getshopInfo()); //매장소개...내용 추가되면 넣기
		storeProductNum.setText(spga.getCount() + "");
		// storeFavoriteNum.setText("");
//		storeTag.setText("");

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	public void setFont() {
		Typeface yoonGodic310 = Typeface.createFromAsset(getAssets(),
				"fonts/yoonGothic310.ttf");
		Typeface yoonGodic330 = Typeface.createFromAsset(getAssets(),
				"fonts/yoonGothic330.ttf");
		Typeface yoonGodic350 = Typeface.createFromAsset(getAssets(),
				"fonts/yoonGothic350.ttf");

		storeTitle.setTypeface(yoonGodic350);
		storeTag.setTypeface(yoonGodic310);
		storeInfo.setTypeface(yoonGodic330);
		storeLocation.setTypeface(yoonGodic330);
		storeLocation2.setTypeface(yoonGodic330);
		storeProductNum.setTypeface(yoonGodic330);
		storeProductNum2.setTypeface(yoonGodic330);
		storeFavoriteNum.setTypeface(yoonGodic330);
		storeFavoriteNum2.setTypeface(yoonGodic330);

	}

	public void setStoreImage() {
		String imgPath = "http://" + Server.ip + "/server/store/"
				+ shop.getEmail() + "/main.jpg";
		storeImageView.setScaleType(ScaleType.CENTER_CROP);
		storeImageView.setAlpha(50);

		Image.loadBitmap(imgPath, this, storeImageView);
	}

}
