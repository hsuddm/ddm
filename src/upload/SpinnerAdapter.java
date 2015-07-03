package upload;

import java.util.ArrayList;

import upload.NewUploadActivity.SpinnerData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dongdong.R;

public class SpinnerAdapter extends BaseAdapter {

	private Context context = null;
	private ArrayList<SpinnerData> spinnerData = null;
	private LayoutInflater inflater;
	String strColor="#ffffff";

	public SpinnerAdapter(Context context, ArrayList<SpinnerData> spinnerData) {
		this.context = context;
		this.spinnerData = spinnerData;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	
	



	public void setChangeData(ArrayList<SpinnerData> spinnerData) {
		this.spinnerData = spinnerData;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (null != spinnerData) {
			return spinnerData.size();
		} else {

			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (null != spinnerData) {
			return spinnerData.get(position);
		} else {
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SpinnerData data = spinnerData.get(position);
		if (null == convertView) {
			convertView = inflater
					.inflate(R.layout.spinner_item, parent, false);

			// convertView.setOnClickListener(new View.OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// SpinnerData spinnerData1=data.title;
			//
			// }
			// });

			TextView tv = (TextView) convertView.findViewById(R.id.tv_spitem);
			
			tv.setTextColor(Color.parseColor(strColor));
			tv.setText(data.title);
			tv.setGravity(Gravity.CENTER);
			float size = 15;
			tv.setTextSize(size);
			Typeface tf = Typeface.createFromAsset(context.getAssets(),
					"fonts/yoonGothic310.ttf");
			tv.setTypeface(tf);
		}
		return convertView;
	}

	public View getDropDownView(int position, View convertView1,
			ViewGroup parent) {
		ViewHolder vh;
		SpinnerData data = spinnerData.get(position);
		View SpinnerItem = convertView1;

		if (SpinnerItem == null) {
			SpinnerItem = inflater.inflate(R.layout.spinner_dropdown_item,
					parent, false);
			vh = new ViewHolder();
			vh.tv = (TextView) SpinnerItem.findViewById(R.id.text1);
			Typeface tf = Typeface.createFromAsset(context.getAssets(),
					"fonts/yoonGothic310.ttf");
			
			vh.tv.setTypeface(tf);
			vh.tv.setText(data.title);
			SpinnerItem.setTag(vh);
		} else {
			vh = (ViewHolder) SpinnerItem.getTag();
			
			vh.tv.setText(data.title);
		}

		return SpinnerItem;
	}

	class ViewHolder {
		TextView tv;
	}

}
