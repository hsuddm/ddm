package reply;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import query.Query;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongdong.R;

public class ReplyActivity extends Activity {
	TextView reply;
	TextView user_id;
	EditText reply_content;
	TextView complete;
	TextView cancel;
	ImageView image1;
	ImageView image2;
	ImageView image3;
	Bitmap Image_photo1;
	Bitmap Image_photo2;
	Bitmap Image_photo3;
	RatingBar mRating;
	TextView tv01;
	Uri u_photo1;
	Uri u_photo2;
	Uri u_photo3;
	String sdcard1;
	ImageView backpage;
	ImageView home;
	ImageView cart;
	ImageView favorite;
	private int REQ_CODE_PICK_PICTURE = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reply);

		reply = (TextView) findViewById(R.id.reply);
		user_id = (TextView) findViewById(R.id.user_id);
		reply_content = (EditText) findViewById(R.id.reply_content);
		complete = (TextView) findViewById(R.id.complete);
		cancel = (TextView) findViewById(R.id.cancel);
		image1 = (ImageView) findViewById(R.id.image1);
		image2 = (ImageView) findViewById(R.id.image2);
		image3 = (ImageView) findViewById(R.id.image3);
		mRating = (RatingBar) findViewById(R.id.ratingbar);
		backpage = (ImageView) findViewById(R.id.backpage);
		home = (ImageView) findViewById(R.id.home);
		favorite = (ImageView) findViewById(R.id.favorite);
		cart = (ImageView) findViewById(R.id.cart);

		backpage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		ImageView home = (ImageView) findViewById(R.id.home);
		home.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), main.MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

			}
		});
		mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, // rating
																			// :
																			// �뜝�룞�삕�뜝�룞�삕
																			// �뜝�룞�삕�뜝�룞�삕(�뜝�룞�삕�뜝�룞�삕)
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});

		String fontpath = "fonts/aoldshower.ttf";
		Typeface tf = Typeface.createFromAsset(ReplyActivity.this.getResources().getAssets(),
				fontpath);
		String fontpath1 = "fonts/yoonGothic310.ttf";
		Typeface tf1 = Typeface.createFromAsset(ReplyActivity.this.getResources().getAssets(),
				fontpath1);
		reply.setTypeface(tf);
		complete.setTypeface(tf);
		cancel.setTypeface(tf);
		user_id.setTypeface(tf1);
		reply_content.setTypeface(tf1);

		complete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(image1.getDrawable() == null || image2.getDrawable() == null || image3.getDrawable() ==null) {
					Toast toast = Toast.makeText(getApplicationContext(), "洹몃┝ �뾾�떎",
							Toast.LENGTH_SHORT);
					toast.show();
					finish();
					return;
				}
					
					
				Query q = new Query();
				Intent intent = getIntent();
				int id = intent.getExtras().getInt("id2");

				float star_cnt = mRating.getRating();

				String r_user_id = user_id.getText().toString();
				String content = reply_content.getText().toString();

				// INSERT INTO `ddmtest`.`reply` (`user_id`, `star_cnt`,
				// `content`, `reg_date`) VALUES ('1', 2, '3', '2015-05-10
				// 18:21:48');
				q.send("insert into `ddmtest`.`reply` (`id`, `user_id`, `star_cnt`, `content`, `reg_date`) values ('"
						+ id
						+ "', '"
						+ r_user_id
						+ "', '"
						+ star_cnt
						+ "', '"
						+ content
						+ "', now());");

				String sendImg = sdcard1 + "/replyimg/image1.jpg";
				

				image.Image.sendReplyImages(sendImg);

				Toast toast = Toast.makeText(getApplicationContext(), "�뙎湲� �벑濡앹씠 �셿猷� �릺�뿀�뒿�땲�떎.",
						Toast.LENGTH_SHORT);
				toast.show();
				finish();

			}

		});

		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		image1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				REQ_CODE_PICK_PICTURE = 0;
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_PICTURE);

			}
		});

		image2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				REQ_CODE_PICK_PICTURE = 1;
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_PICTURE);
			}
		});
		image3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				REQ_CODE_PICK_PICTURE = 2;
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_PICTURE);
			}
		});
	}

	protected void onStart() {
		super.onStart();
		image1.setBackgroundResource(R.drawable.photo1);
		image2.setBackgroundResource(R.drawable.photo2);
		image3.setBackgroundResource(R.drawable.photo3);
		complete.setBackgroundResource(R.drawable.complete);
		cancel.setBackgroundResource(R.drawable.blank);
		backpage.setImageResource(R.drawable.back);
		home.setImageResource(R.drawable.home);
		cart.setImageResource(R.drawable.cart);
		favorite.setImageResource(R.drawable.favorite);
	}

	protected void onStop() {
		super.onStop();
		image1.setBackground(null);
		image2.setBackground(null);
		image3.setBackground(null);
		complete.setBackground(null);
		cancel.setBackground(null);
		backpage.setImageDrawable(null);
		home.setImageDrawable(null);
		cart.setImageDrawable(null);
		favorite.setImageDrawable(null);
	}

	@SuppressWarnings("deprecation")
	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		startManagingCursor(cursor);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		requestCode = REQ_CODE_PICK_PICTURE;

		BitmapFactory.Options option = new BitmapFactory.Options();
		option.inSampleSize = 4;
		sdcard1 = Environment.getExternalStorageDirectory().getAbsolutePath();

		File cfile = new File(sdcard1 + "/replyimg");
		cfile.mkdirs();

		if (requestCode == 0) {
			if (resultCode == Activity.RESULT_OK) {
				try {
					Image_photo1 = BitmapFactory.decodeStream(
							getContentResolver().openInputStream(data.getData()), null, option);
					SaveBitmapToFileCache(Image_photo1, sdcard1 + "/replyimg/image1.jpg");
					image1.setImageBitmap(Image_photo1);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				try {
					Image_photo2 = BitmapFactory.decodeStream(
							getContentResolver().openInputStream(data.getData()), null, option);
					SaveBitmapToFileCache(Image_photo2, sdcard1 + "/replyimg/image2.jpg");
					image2.setImageBitmap(Image_photo2);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (requestCode == 2) {
			if (resultCode == Activity.RESULT_OK) {
				try {
					Image_photo3 = BitmapFactory.decodeStream(
							getContentResolver().openInputStream(data.getData()), null, option);
					SaveBitmapToFileCache(Image_photo3, sdcard1 + "/replyimg/image3.jpg");
					image3.setImageBitmap(Image_photo3);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void SaveBitmapToFileCache(Bitmap bitmap, String strFilePath) {

		File fileCacheItem = new File(strFilePath);
		OutputStream out = null;

		try {
			fileCacheItem.createNewFile();
			out = new FileOutputStream(fileCacheItem);

			bitmap.compress(CompressFormat.JPEG, 100, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
