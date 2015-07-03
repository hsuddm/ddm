package shop;

import image.Image;

import java.util.ArrayList;

import product.Product;
import query.Server;
import searchtypes.SearchType;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dongdong.R;

public class ShopPageGridViewAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater inflater;
	private ArrayList<Product> itemList;
	private int height;

	public ShopPageGridViewAdapter(SearchType st, Context c) {
		mContext = c;
		inflater = LayoutInflater.from(mContext);
		itemList = st.getProducts();
		height = mContext.getResources().getDisplayMetrics().densityDpi;
	}

	public Product getProduct(int position) {
		return itemList.get(position);
	}

	void clear() {
		itemList.clear();
	}

	void remove(int index) {
		itemList.remove(index);
	}

	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		View cell = convertView;
		Product p = itemList.get(position);

		if (cell == null) { // if it's not recycled, initialize some
			holder = new ViewHolder();
			cell = inflater.inflate(R.layout.grid_item_layout, null);
			cell.setLayoutParams(new GridView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, height * 5 / 7));

			holder.image = (ImageView) cell.findViewById(R.id.productImage);
			holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
			cell.setTag(holder);

			// <p>id,shopname,kind,name,</p>
			String imgPath = "http://" + Server.ip + "/server/img/" + p.getShop_name() + "/"
					+ p.getkind() + "/" + p.getname() + "/front.jpg";

			Image.loadBitmap(imgPath, mContext, (ImageView) cell);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return cell;
	}

	class ViewHolder {
		ImageView image;
	}
}