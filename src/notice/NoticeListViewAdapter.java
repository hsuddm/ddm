package notice;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dongdong.R;


public class NoticeListViewAdapter extends BaseAdapter{
	Context mContext;
	ArrayList<Notice> mData;
	LayoutInflater mLayoutInflater;
	
	
	Typeface tf;
	
	
	
	public NoticeListViewAdapter(Context context, ArrayList<Notice> data){
		this.mContext = context;
		this.mData = data;
		mLayoutInflater = LayoutInflater.from(mContext);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View noticeCell = convertView;
		ViewHolder viewHolder = null;
		String fontpath = "fonts/yoonGothic310.ttf";
		Typeface tf = Typeface.createFromAsset(mContext.getAssets(), fontpath);
		String fontpath1 = "fonts/yoonGothic330.ttf";
		Typeface tf1 = Typeface.createFromAsset(mContext.getAssets(), fontpath1);
		
		if(noticeCell == null){
			viewHolder = new ViewHolder();
			noticeCell = mLayoutInflater.inflate(R.layout.notice_cell, null);
			
			viewHolder.iv_notice_event = (ImageView) noticeCell.findViewById(R.id.iv_notice_event);
			viewHolder.tv_notice_event = (TextView) noticeCell.findViewById(R.id.tv_notice_event);
			viewHolder.tv_notice_event_content = (TextView) noticeCell.findViewById(R.id.tv_notice_event_content);
			viewHolder.tv_reg_date = (TextView) noticeCell.findViewById(R.id.reg_date);
			noticeCell.setTag(viewHolder);
			
			viewHolder.tv_notice_event.setTypeface(tf1);
			viewHolder.tv_notice_event_content.setTypeface(tf);
			viewHolder.tv_reg_date.setTypeface(tf);
			
		} else {
			viewHolder = (ViewHolder) noticeCell.getTag();
		}
		
		viewHolder.tv_notice_event.setText(mData.get(position).getTitle());
		viewHolder.tv_notice_event_content.setText(mData.get(position).getContent());
		String date = mData.get(position).getDate();
		date = date.substring(0, date.length() - 2);
		viewHolder.tv_reg_date.setText(date);
		
		viewHolder.iv_notice_event.setImageResource(R.drawable.notice);
		
	
		
		
		
		return noticeCell;
	}
	class ViewHolder{
		ImageView iv_notice_event;
		TextView tv_notice_event;
		TextView tv_notice_event_content;
		TextView tv_reg_date;
	}

}
