package upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import query.Query;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongdong.R;

public class NewUploadActivity extends Activity {

	ImageView iv_back_btn, iv_front, iv_back, iv_side, iv_detail1, iv_detail2,
			iv_upload_btn, iv_backpage, iv_home, iv_wish;
	TextView tv_hash, tv_title;
	private Spinner sp_category = null;
	private Spinner sp_material = null;

	EditText et_p_name, et_price, et_hash1, et_hash2, et_hash3, et_hash4;
	private int REQ_CODE_PICK_PICTURE = 0;
	Bitmap image_front, image_side, image_back, image_detail1, image_detail2;
	String sdcard1;

	private ArrayList<SpinnerData> data = new ArrayList<SpinnerData>();
	private ArrayList<SpinnerData2> data2 = new ArrayList<SpinnerData2>();
	private SpinnerAdapter spinnerAdapter = null;
	private SpinnerAdapter spinnerAdapter2 = null;
	private Context context = null;
	private Context context2 = null;
	private int position, position2;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	protected void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_upload_page);

		context = this;
		context2 = this;
		sp_category = (Spinner) findViewById(R.id.sp_category);
		data.add(new SpinnerData("# 카테고리 찾기", "0"));
		data.add(new SpinnerData("# Outer", "1"));
		data.add(new SpinnerData("# Jeans", "2"));
		data.add(new SpinnerData("# Dress", "3"));
		data.add(new SpinnerData("# Coat", "4"));
		setSpinnerAdapter(data);

		sp_material = (Spinner) findViewById(R.id.sp_material);
		data2.add(new SpinnerData2("# 소재 찾기", "0"));
		data2.add(new SpinnerData2("# 나일론", "1"));
		data2.add(new SpinnerData2("# 합성", "2"));
		data2.add(new SpinnerData2("# 모직", "3"));
		setSpinnerAdapter2(data2);

		iv_back_btn = (ImageView) findViewById(R.id.iv_back_btn);
		iv_front = (ImageView) findViewById(R.id.iv_front);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_side = (ImageView) findViewById(R.id.iv_side);
		iv_detail1 = (ImageView) findViewById(R.id.iv_detail1);
		iv_detail2 = (ImageView) findViewById(R.id.iv_detail2);
		iv_upload_btn = (ImageView) findViewById(R.id.iv_upload_btn);
		iv_backpage = (ImageView) findViewById(R.id.iv_backpage);
		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_wish = (ImageView) findViewById(R.id.iv_wish);

		tv_hash = (TextView) findViewById(R.id.tv_hash);

		tv_title = (TextView) findViewById(R.id.tv_title);

		et_p_name = (EditText) findViewById(R.id.et_p_name);
		et_price = (EditText) findViewById(R.id.et_price);
		et_hash1 = (EditText) findViewById(R.id.et_hash1);
		et_hash2 = (EditText) findViewById(R.id.et_hash2);
		et_hash3 = (EditText) findViewById(R.id.et_hash3);
		et_hash4 = (EditText) findViewById(R.id.et_hash4);

		String fontpath = "fonts/yoonGothic310.ttf";
		Typeface tf = Typeface.createFromAsset(NewUploadActivity.this
				.getResources().getAssets(), fontpath);
		String fontpath1 = "fonts/yoonGothic330.ttf";
		Typeface tf1 = Typeface.createFromAsset(NewUploadActivity.this
				.getResources().getAssets(), fontpath1);

		tv_title.setTypeface(tf1);
		tv_hash.setTypeface(tf);

		et_p_name.setTypeface(tf);
		et_price.setTypeface(tf);
		et_hash1.setTypeface(tf);
		et_hash2.setTypeface(tf);
		et_hash3.setTypeface(tf);
		et_hash4.setTypeface(tf);
		iv_backpage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		iv_back_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		iv_home.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						main.MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		

		iv_front.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				REQ_CODE_PICK_PICTURE = 0;
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_PICTURE);
			}
		});
		iv_side.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				REQ_CODE_PICK_PICTURE = 1;
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_PICTURE);
			}
		});
		iv_back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				REQ_CODE_PICK_PICTURE = 2;
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_PICTURE);
			}
		});

		iv_detail1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				REQ_CODE_PICK_PICTURE = 3;
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_PICTURE);
			}
		});

		iv_detail2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				REQ_CODE_PICK_PICTURE = 4;
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_PICTURE);
			}
		});
		et_hash4.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				position = sp_category.getSelectedItemPosition();
				position2 = sp_material.getSelectedItemPosition();
				SpinnerData sd_category_item = (SpinnerData) sp_category
						.getAdapter().getItem(position);
				SpinnerData2 sd_material_item = (SpinnerData2) sp_material
						.getAdapter().getItem(position2);

				if (keyCode == event.KEYCODE_ENTER) {

					tv_hash.setText(sd_category_item.title + "  "
							+ sd_material_item.title + "  #"
							+ et_p_name.getText() + "  #" + et_price.getText()
							+ "  #" + et_hash1.getText() + "  #"
							+ et_hash2.getText() + "  #" + et_hash3.getText()
							+ "  #" + et_hash4.getText());
				}

				return false;
			}
		});

		iv_upload_btn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// TODO Auto-generated method stub

				if (image_front != null && image_side != null
						&& image_back != null && image_detail1 != null
						&& image_detail2 != null
						&& et_p_name.getText().length() != 0
						&& et_price.getText().length() != 0
						&& et_hash1.getText().length() != 0
						&& et_hash2.getText().length() != 0
						&& et_hash3.getText().length() != 0
						&& et_hash4.getText().length() != 0)

				{
					Query q = new Query();
					String result = q
							.send("select name from clothes where name=`"
									+ et_p_name.getText().toString() + "`;");
					// 媛숈� �긽�뭹�씠由꾩쑝濡� �긽�뭹�씠 �벑濡앸릺�엳�쑝硫� �뾽濡쒕뱶 �븞�릺�룄濡�!
					if (result.contains("<p>")) {
						Toast toast = Toast.makeText(getApplicationContext(),
								"媛숈� �씠由꾩쓽 �긽�뭹�씠 �씠誘� �벑濡앸릺�뼱 �엳�뒿�땲�떎.",
								Toast.LENGTH_SHORT);
						toast.show();
						return;
					}

					position = sp_category.getSelectedItemPosition();
					position2 = sp_material.getSelectedItemPosition();
					SpinnerData sd_category_item = (SpinnerData) sp_category
							.getAdapter().getItem(position);
					SpinnerData2 sd_material_item = (SpinnerData2) sp_material
							.getAdapter().getItem(position2);
					String kind = sd_category_item.title;
					kind = kind.substring(2, kind.length());
					String mat = sd_material_item.title;
					mat = mat.substring(2, mat.length());
					String p_price = et_price.getText().toString();

					String hashtag1 = et_hash1.getText().toString();
					String hashtag2 = et_hash2.getText().toString();
					String name = et_p_name.getText().toString();

					q.send("insert into `ddmtest`.`clothes` (`kind`, `price`, `material`, `hashtag1`, `hashtag2`, `name`, `silhouette`, `content`, `shop_name`, `reg_date`) "
							+ "VALUES ('"
							+ kind
							+ "', '"
							+ p_price
							+ "', '"
							+ mat
							+ "', '"
							+ hashtag1
							+ "', '"
							+ hashtag2
							+ "', '"
							+ name
							+ "', 5000, '吏깆쥕��諛붿�', '以��썝', now());");

					String[] sendImg = new String[5];

					sendImg[0] = sdcard1 + "/img/front.jpg";
					sendImg[1] = sdcard1 + "/img/side.jpg";
					sendImg[2] = sdcard1 + "/img/back.jpg";
					sendImg[3] = sdcard1 + "/img/detail1.jpg";
					sendImg[4] = sdcard1 + "/img/detail2.jpg";
					image.Image.sendImages(sendImg);

					Toast toast = Toast.makeText(getApplicationContext(),
							"�벑濡앹씠 �셿猷� �릺�뿀�뒿�땲�떎.", Toast.LENGTH_SHORT);
					toast.show();
					// Intent intent = new Intent(getApplicationContext(),
					// MainPageActivity.class);
					// startActivity(intent);
					finish();
				} else {
					Toast toast = Toast.makeText(getApplicationContext(),
							"�씠誘몄��� �긽�뭹�젙蹂대�� 紐⑤몢 �엯�젰�빐 二쇱꽭�슂.",
							Toast.LENGTH_SHORT);
					toast.show();
				}

			}
		});
	}

	private void setSpinnerAdapter2(ArrayList<SpinnerData2> data2) {
		// TODO Auto-generated method stub
		if (null != data2) {
			if (null == spinnerAdapter2) {
				spinnerAdapter2 = new upload.SpinnerAdapter2(context2, data2);
				sp_material.setAdapter(spinnerAdapter2);
				sp_material
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								// SpinnerData

							}

							public void onNothingSelected(AdapterView<?> arg0) {
							}

						});
			} else {
				((upload.SpinnerAdapter2) spinnerAdapter2).setChangeData(data2);
			}
		}

	}

	private void setSpinnerAdapter(ArrayList<SpinnerData> data) {
		// TODO Auto-generated method stub
		if (null != data) {
			if (null == spinnerAdapter) {
				spinnerAdapter = new upload.SpinnerAdapter(context, data);
				sp_category.setAdapter(spinnerAdapter);
				sp_category
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								// SpinnerData
								// spinnerData=(SpinnerData)arg0.getAdapter().getItem(position);
							}

							public void onNothingSelected(AdapterView<?> arg0) {
							}

						});
			} else {
				((upload.SpinnerAdapter) spinnerAdapter).setChangeData(data);
			}
		}

	}

	public class SpinnerData {
		public String title = "";
		public String num = "";

		public SpinnerData(String title, String num) {
			this.title = title;
			this.num = num;
		}
	}

	public class SpinnerData2 {
		public String title = "";
		public String num = "";

		public SpinnerData2(String title, String num) {
			this.title = title;
			this.num = num;
		}
	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		startManagingCursor(cursor);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		requestCode = REQ_CODE_PICK_PICTURE;

		BitmapFactory.Options option = new BitmapFactory.Options();
		option.inSampleSize = 2;
		sdcard1 = Environment.getExternalStorageDirectory().getAbsolutePath();

		File cfile = new File(sdcard1 + "/img");

		cfile.mkdirs();
		int iv_width, iv_height;
		// 揶쏉옙嚥∽옙>占쎄쉭嚥∽옙 => return, 揶쏉옙嚥∽옙<占쎄쉭嚥∽옙 => 占쎌돳占쎌읈

		if (requestCode == 0) {
			if (resultCode == Activity.RESULT_OK) {
				try {

					image_front = BitmapFactory.decodeStream(
							getContentResolver()
									.openInputStream(data.getData()), null,
							option);

					SaveBitmapToFileCache(image_front, sdcard1
							+ "/img/front.jpg");

					iv_front.setImageBitmap(image_front);
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
					image_side = BitmapFactory.decodeStream(
							getContentResolver()
									.openInputStream(data.getData()), null,
							option);
					SaveBitmapToFileCache(image_side, sdcard1 + "/img/side.jpg");
					iv_side.setImageBitmap(image_side);
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
					image_back = BitmapFactory.decodeStream(
							getContentResolver()
									.openInputStream(data.getData()), null,
							option);
					SaveBitmapToFileCache(image_back, sdcard1 + "/img/back.jpg");
					iv_back.setImageBitmap(image_back);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (requestCode == 3) {
			if (resultCode == Activity.RESULT_OK) {
				try {
					image_detail1 = BitmapFactory.decodeStream(
							getContentResolver()
									.openInputStream(data.getData()), null,
							option);
					SaveBitmapToFileCache(image_detail1, sdcard1
							+ "/img/detail1.jpg");
					iv_detail1.setImageBitmap(image_detail1);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (requestCode == 4) {
			if (resultCode == Activity.RESULT_OK) {
				try {
					image_detail2 = BitmapFactory.decodeStream(
							getContentResolver()
									.openInputStream(data.getData()), null,
							option);
					SaveBitmapToFileCache(image_detail2, sdcard1
							+ "/img/detail2.jpg");
					iv_detail2.setImageBitmap(image_detail2);
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
