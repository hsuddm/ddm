package store;

import java.util.ArrayList;

import main.MainActivity;
import product.Product;
import product.ProductTranslator;
import query.Query;
import adapter.FavoritePageGridViewAdapter;
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
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import clothes.Favorite;
import clothes.NewDetailActivity;

import com.example.dongdong.R;

public class FavoriteProductListActivity extends Activity {
	TextView title;
	GridView gridView;
	private ImageButton btTop, btDown, btHome, btFav;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_product_list);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		title = (TextView) findViewById(R.id.favoriteProductTitle);
		gridView = (GridView) findViewById(R.id.favoriteGridView);

		setFont();
		setImgButton();

		final Favorite fv = new Favorite(this);
		final ArrayList<String> favoriteIDs = fv.getFavorites();

		final FavoritePageGridViewAdapter fpga = new FavoritePageGridViewAdapter(this, favoriteIDs);
		gridView.setAdapter(fpga);

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				System.out.println("pre");
				System.out.println(fv.getFavorites());
				System.out.println(fpga);

				fv.removeFavoriteItem(favoriteIDs.get(position));
				fpga.remove(position);

				System.out.println("post");
				System.out.println(fv.getFavorites());
				System.out.println(fpga);
				return true;
			}

		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parant, View view, int position, long arg3) {

				Intent intent = new Intent(getApplicationContext(), NewDetailActivity.class);

				SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

				String email = pref.getString("id", null);
				String producID = favoriteIDs.get(position);

				Query q = new Query();
				String tmp = q.send("select id,kind,silhouette,material,hashtag1,hashtag2,hashtag3,hashtag4," + "name,price,count,content,discount_rate,shop_name,shop_email,reg_date,like_cnt "
																								+ "from clothes where id='" + producID + "';");

				tmp = Query.doParse(tmp);

				Product p = ProductTranslator.queryToProduct(tmp);
				intent.putExtra("product", p.toString());
				intent.putExtra("id", p.getid());
				intent.putExtra("email", email);
				startActivity(intent);

			}

		});
	}

	public void setFont() {
		Typeface yoonGothic350 = Typeface.createFromAsset(getAssets(), "fonts/yoonGothic350.ttf");

		title.setTypeface(yoonGothic350);
	}

	private void setImgButton() {
		btTop = (ImageButton) findViewById(R.id.btnFavorUpBack);
		btTop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		btDown = (ImageButton) findViewById(R.id.btnFavorDownBack);
		btDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		btHome = (ImageButton) findViewById(R.id.btnFavorHome);
		btHome.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});

		btFav = (ImageButton) findViewById(R.id.btnFavorFav);
		btFav.setEnabled(false);
	}
}
