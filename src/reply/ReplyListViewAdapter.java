package reply;

import image.Image;

import java.util.ArrayList;

import query.Server;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dongdong.R;

public class ReplyListViewAdapter extends BaseAdapter {
	int id;
	Context context;
	ArrayList<Reply> data;
	LayoutInflater inflater;

	public ReplyListViewAdapter(Context context, ArrayList<Reply> data, int id) {
		this.context = context;
		this.data = data;
		inflater = LayoutInflater.from(context);
		this.id = id;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View replyCell = convertView;
		ViewHolder viewHolder = null;
		String fontpath = "fonts/yoonGothic310.ttf";
		Typeface tf = Typeface.createFromAsset(context.getAssets(), fontpath);
		String fontpath1 = "fonts/yoonGothic330.ttf";
		Typeface tf1 = Typeface.createFromAsset(context.getAssets(), fontpath1);

		if (replyCell == null) {
			viewHolder = new ViewHolder();
			// replyCell = inflater.inflate(R.layout.reply_cell, null);
			replyCell = inflater.inflate(R.layout.new_reply_cell, null);

			viewHolder.rb_star = (RatingBar) replyCell
					.findViewById(R.id.rb_replyRatingbar);
			viewHolder.tv_content = (TextView) replyCell
					.findViewById(R.id.tv_content);
			viewHolder.tv_userId = (TextView) replyCell
					.findViewById(R.id.tv_user_id);
			viewHolder.tv_date = (TextView) replyCell
					.findViewById(R.id.reg_date);
			viewHolder.img1 = (ImageView) replyCell.findViewById(R.id.iv_img1);

			replyCell.setTag(viewHolder);

			viewHolder.tv_content.setTypeface(tf);
			viewHolder.tv_userId.setTypeface(tf1);
			viewHolder.tv_date.setTypeface(tf);

		} else {
			viewHolder = (ViewHolder) replyCell.getTag();
		}

		viewHolder.rb_star.setRating(data.get(position).getStarRating());
		viewHolder.tv_content.setText(data.get(position).getContent());
		viewHolder.tv_userId.setText(data.get(position).getUserId());
		// 디비에서 reg_date읽어오면 뒤에 .0붙어옴
		String date = data.get(position).getDate();
		date = date.substring(0, date.length() - 2);
		viewHolder.tv_date.setText(date);

		String tmp = data.get(position).getDate();
		String dateFolder = tmp.replace(":", "_");
		dateFolder = dateFolder.substring(0, dateFolder.length() - 2);

		String replyImgPath = "http://" + Server.ip + "/server/replyimg/" + id
				+ "/" + data.get(position).getUserId() + "/" + dateFolder
				+ "/image1.jpg";

		replyImgPath = replyImgPath.replace(" ", "%20");

		Image.loadReplyBitmap(replyImgPath, context, viewHolder.img1);

		viewHolder.img1.setOnClickListener(new ReplyImgClickListner(context,
				replyImgPath, viewHolder.tv_date));// tv_date기준으로 열림 위치;;

		return replyCell;
	}

	class ViewHolder {
		RatingBar rb_star;
		TextView tv_userId;
		TextView tv_date;
		TextView tv_content;
		ImageView img1;
	}

}