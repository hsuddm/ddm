package shop;

import java.util.ArrayList;

import product.Product;
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

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater inflater;
	private ArrayList<Product> itemList;
	private int height;

	public ImageAdapter(Context c, ArrayList<Product> itemList) {
		mContext = c;
		inflater = LayoutInflater.from(mContext);
		height = mContext.getResources().getDisplayMetrics().densityDpi;
		this.itemList = itemList;
	}

	// void add(String path) {
	// itemList.add(path);
	// }

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

	@SuppressLint("InflateParams")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		View cell = convertView;

		if (cell == null) { // if it's not recycled, initialize some
			holder = new ViewHolder();
			cell = inflater.inflate(R.layout.store_info_grid_item_layout, null);
			cell.setLayoutParams(new GridView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, height * 5 / 7));

			holder.image = (ImageView) cell.findViewById(R.id.productImage);
			holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);

			// holder.stars = (TextView) cell.findViewById(R.id.tv_starcnt);
			// holder.replys = (TextView) cell.findViewById(R.id.tv_replycnt);
			// holder.position = position;

			// String paths = resultPaths[position];
			// int end = paths.indexOf(".jpg");
			// String itemId = paths.substring(end + 4, paths.length());
			// Query q = new Query();
			// String itemData =
			// q.send("select id,kind,silhouette, material,hashtag1,hashtag2,name,price,count,content,discount_rate,shop_name,reg_date,like_cnt from clothes where id = '"
			// + itemId + "';");
			// itemData = Query.doParse(itemData);
			//
			// if (itemData.equals(null)) {
			// return new View(mContext);
			// }
			//
			// Product p = ProductTranslator.queryToProduct(itemData);
			// itemsData.add(p);
			// holder.id = String.valueOf(itemsData.get(position).getid());
			cell.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		new AsyncTask<ViewHolder, Void, Void>() {
			private ViewHolder v;

			// private int star = 0;
			// private int reply = 0;

			@Override
			protected Void doInBackground(ViewHolder... params) {
				this.v = params[0];
				// ArrayList<Reply> replyData = new ArrayList<Reply>();
				// Query q = new Query();
				// String tmp = q
				// .send("select id, user_id, star_cnt, content, reg_date, answer from reply where id='"
				// + v.id + "';");
				// tmp = Query.doParse(tmp);
				// if (tmp == null) {
				// this.star = 0;
				// this.reply = 0;
				// return null;
				// }
				// String forSplit[] = tmp.split("\n");
				// Reply reply = null;
				// int sCnt = 0;
				// for (int i = 0; i < forSplit.length; i++) {
				// reply = ReplyTranslator.queryToReply(forSplit[i]);
				// replyData.add(reply);
				// sCnt += replyData.get(i).getStarRating();
				// }
				//
				// if (replyData.size() >= 1) {
				// this.star = sCnt / replyData.size();
				// this.reply = replyData.size();
				// } else {
				// this.star = 0;
				// this.reply = 0;
				// }

				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// Image.loadBitmap(itemList.get(position), mContext, v.image);
				// v.stars.setText(this.star + "");
				// v.replys.setText(this.reply + "");
				v.image.setImageDrawable(mContext.getResources().getDrawable(
						R.drawable.test));
			}

		}.execute(holder);

		return cell;
	}

	class ViewHolder {
		ImageView image;
		TextView stars;
		TextView replys;
		int position;
		String id;
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

}
