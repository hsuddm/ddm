package adapter;

import image.Image;

import java.util.ArrayList;

import query.Query;
import query.Server;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.dongdong.R;

public class FavoritePageGridViewAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater inflater;
	private ArrayList<String> idList;
	private int height;

	public FavoritePageGridViewAdapter(Context c, ArrayList<String> idList) {
		mContext = c;
		inflater = LayoutInflater.from(mContext);
		height = mContext.getResources().getDisplayMetrics().densityDpi;
		this.idList = idList;
	}

	void clear() {
		idList.clear();
	}

	public void remove(int index) {
		idList.remove(index);
		this.notifyDataSetChanged();
	}
	
	public String toString() {
		
		return idList.toString();
	}

	@Override
	public int getCount() {
		return idList.size();
	}

	@Override
	public Object getItem(int position) {
		return idList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		View cell = convertView;
		String id = idList.get(position);

		if (cell == null) { // if it's not recycled, initialize some
			holder = new ViewHolder();

			cell = inflater.inflate(
					R.layout.favorite_product_list_grid_item_layout, null);
			cell.setLayoutParams(new GridView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, height * 5 / 7));

			holder.image = cell.findViewById(R.id.favoriteProductImage);
			cell.setTag(holder);

			

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Query q = new Query();
		String tmp = q
				.send("select shop_name, kind, name from clothes where id='"
						+ id + "';");
		System.out.println(tmp);
		if (tmp == null) {
			return cell;
		}
		String info = Query.doParse(tmp);
		System.out.println(info);
		// <p> �씠由�,醫낅쪟,�씠由� ,</p>
		String[] infos = info.split(",");

		if (infos.length < 4) {
			return cell;
		}
		// <p>�빐蹂쇰씪怨�,Dress,�뱶�젅�뒪,</p>

		String shopName = infos[0].substring(3, infos[0].length());
		String kind = infos[1].substring(0, infos[1].length());
		String name = infos[2].substring(0, infos[2].length());
		System.out.println(shopName + "," + kind + "," + name);
		// <p>id,shopname,kind,name,</p>
		String imgPath = "http://" + Server.ip + "/server/img/" + shopName
				+ "/" + kind + "/" + name + "/front.jpg";
		System.out.println("imgpath>> " + imgPath);

		Image.loadBitmap(imgPath, mContext, (ImageView) holder.image);
		
		return cell;
	}

	class ViewHolder {
		View image;
	}
}