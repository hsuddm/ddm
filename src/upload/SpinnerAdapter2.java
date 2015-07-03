package upload;

import java.util.ArrayList;

import upload.NewUploadActivity.SpinnerData;
import upload.NewUploadActivity.SpinnerData2;
import upload.SpinnerAdapter.ViewHolder;
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

public class SpinnerAdapter2 extends BaseAdapter {

	private Context context2 = null;
	private ArrayList<SpinnerData2> spinnerData2 = null;
	private LayoutInflater inflater2;
	String strColor="#ffffff";

	public SpinnerAdapter2(Context context2, ArrayList<SpinnerData2> spinnerData2) {
		this.context2 = context2;
		this.spinnerData2 = spinnerData2;

		inflater2 = (LayoutInflater) context2
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	
	



	public void setChangeData(ArrayList<SpinnerData2> spinnerData2) {
		this.spinnerData2 = spinnerData2;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (null != spinnerData2) {
			return spinnerData2.size();
		} else {

			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (null != spinnerData2) {
			return spinnerData2.get(position);
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
		SpinnerData2 data2 = spinnerData2.get(position);
		if (null == convertView) {
			convertView = inflater2
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
			tv.setText(data2.title);
			tv.setGravity(Gravity.CENTER);

			Typeface tf = Typeface.createFromAsset(context2.getAssets(),
					"fonts/yoonGothic310.ttf");
			tv.setTypeface(tf);
		}
		return convertView;
	}

	public View getDropDownView(int position, View convertView1,
			ViewGroup parent) {
		ViewHolder vh;
		SpinnerData2 data2 = spinnerData2.get(position);
		View SpinnerItem = convertView1;

		if (SpinnerItem == null) {
			SpinnerItem = inflater2.inflate(R.layout.spinner_dropdown_item,
					parent, false);
			vh = new ViewHolder();
			vh.tv = (TextView) SpinnerItem.findViewById(R.id.text1);
			Typeface tf = Typeface.createFromAsset(context2.getAssets(),
					"fonts/yoonGothic310.ttf");
			
			vh.tv.setTypeface(tf);
			vh.tv.setText(data2.title);
			SpinnerItem.setTag(vh);
		} else {
			vh = (ViewHolder) SpinnerItem.getTag();
			
			vh.tv.setText(data2.title);
		}

		return SpinnerItem;
	}

	class ViewHolder {
		TextView tv;
	}

}
