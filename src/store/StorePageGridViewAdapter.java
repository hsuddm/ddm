package store;

import image.Image;

import java.util.ArrayList;

import query.Server;
import shop.Shop;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dongdong.R;

public class StorePageGridViewAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater inflater;
	private ArrayList<Shop> storeList;
	private int height;
	private String complex;
	Typeface yoonGodic350;

	public StorePageGridViewAdapter(String complex, Context c) {
		mContext = c;
		inflater = LayoutInflater.from(mContext);
		storeList = new GetStoresData(complex).execute();
		height = mContext.getResources().getDisplayMetrics().densityDpi;
		this.complex = complex;
		yoonGodic350 = Typeface.createFromAsset(mContext.getAssets(),
				"fonts/yoonGothic350.ttf");
	}

	public Shop getShop(int position) {
		return storeList.get(position);
	}

	public void setFloor(int floor) {
		GetStoresData gsd = new GetStoresData(complex, floor);
		storeList = gsd.execute();
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return storeList.size();
	}

	@Override
	public Object getItem(int position) {
		return storeList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder;
		View cell = convertView;
		Shop unit = storeList.get(position);
		if (cell == null) {
			holder = new ViewHolder();
			cell = inflater.inflate(R.layout.store_list_grid_item_layout, null);

			holder.frame = (FrameLayout) cell.findViewById(R.id.fram);
			holder.image = (ImageView) cell.findViewById(R.id.storeImage);
			holder.shopName = (TextView) cell.findViewById(R.id.storeName);

			holder.shopName.setText(unit.getShop_name());
			String imgPath = "http://" + Server.ip + "/server/store/"
					+ unit.getEmail() + "/main.jpg";

			holder.shopName.setTypeface(yoonGodic350);

			Image.loadBitmap(imgPath, mContext, holder.image);
			holder.image.setAlpha(50);
			holder.shopName.setAlpha(20);

			cell.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return cell;
	}

	class ViewHolder {
		FrameLayout frame;
		ImageView image;
		TextView shopName;
	}
}
