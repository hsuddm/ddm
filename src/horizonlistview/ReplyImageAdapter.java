package horizonlistview;

import image.Image;

import java.util.ArrayList;

import query.Server;
import reply.Reply;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dongdong.R;

public class ReplyImageAdapter extends BaseAdapter {
	private ArrayList<Reply> replyList;
	private Context c;

	public ReplyImageAdapter(ArrayList<Reply> replyList, Context c) {
		super();
		this.replyList = replyList;
		this.c = c;
	}

	public int getCount() {
		return replyList.size();
	}

	public void remove(int position) {
		replyList.remove(position);
		this.notifyDataSetChanged();
	}

	public Object getItem(int position) {
		return replyList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View retval = (View) LayoutInflater.from(c).inflate(R.layout.reply_hori_list_item,
				null);
		ImageView iv = (ImageView) retval.findViewById(R.id.image);

		iv.setLayoutParams(new LinearLayout.LayoutParams(180, 180));

		String tmp = replyList.get(position).getDate();
		String dateFolder = tmp.replace(":", "_");
		dateFolder = dateFolder.substring(0, dateFolder.length() - 2);
		String replyImgPath = "http://" + Server.ip + "/server/replyimg/"
				+ replyList.get(position).getId() + "/"
				+ replyList.get(position).getUserId() + "/" + dateFolder
				+ "/image1.jpg";

		System.out.println("Img Path>> " + replyImgPath);

		Image.loadReplyBitmap(replyImgPath, c, iv);
		return retval;
	}
}