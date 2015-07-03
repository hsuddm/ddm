package reply;

import android.content.Context;
import android.media.Image;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.dongdong.R;

public class ReplyImgClickListner implements OnClickListener {
	Context c;
	String path;
	View v;
	ImageView iv;

	public ReplyImgClickListner(Context c, String path, View v) {
		this.c = c;
		this.path = path;
		this.v = v;
	}

	@Override
	public void onClick(View v) {
		LayoutInflater layoutInflater = LayoutInflater.from(c);
		View popupView = layoutInflater.inflate(R.layout.reply_popupimage, null);
		
		  WindowManager wm = (WindowManager)c.getSystemService(c.WINDOW_SERVICE);

	        Display display = wm.getDefaultDisplay();

	        int width = display.getWidth();
	        int height = display.getHeight();
		
		final PopupWindow popupWindow = new PopupWindow(popupView, width-100,
				height-400);
		iv = (ImageView) popupView.findViewById(R.id.iv_popupimg);

		image.Image.loadOriginalBitmap(path, c, iv);
		popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
		popupView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//bmp.recycle();
				popupWindow.dismiss();
				iv = null; //�븣�븘�꽌�릺硫� 類댁＜湲�
			}
		});

	}

}