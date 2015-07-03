package clothes;

import horizonlistview.GetReplys;
import horizonlistview.ReplyImageAdapter;
import image.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import product.Product;
import product.ProductTranslator;
import query.Query;
import reply.GetReplyData;
import reply.Reply;
import reply.ReplyListViewAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongdong.R;

import customview.BackPressEditText;
import customview.BackPressEditText.OnBackPressListener;
import customview.HorizontalListView;
import encode.EncodeUtil;

public class NewDetailActivity extends Activity {

	ImageView iv_front, iv_side, iv_back, iv_detail1, iv_detail2;
	BitmapDrawable bd_bitmap1, bd_bitmap2, bd_bitmap3, bd_bitmap4, bd_bitmap5;
	Bitmap Image_photo1;
	ImageView iv_reply_btn, iv_like_btn, iv_gostore_btn, iv_photo_upload;
	ImageView iv_back_btn, iv_share_btn, iv_next_btn, iv_etc_btn, iv_reply;
	TextView tv_title, tv_hash1, tv_hash2, tv_hash3, tv_reply_cnt,
			tv_reply_upload;

	BackPressEditText et_reply_content;
	HorizontalListView replyListView;

	RatingBar rb_reply;
	ListView lv_reply;
	LinearLayout topLayout, newDatailTitleBar, ll_reply_btn, ll_hash, ll_image,
			ll_menu, ll_reply, ll_reply_image;
	Product product;
	Context c;
	int id, numReplys, numStars;
	private int count = 0;
	private int x = 0;
	String sdcard1;
	InputMethodManager imm;
	private int REQ_CODE_PICK_PICTURE = 0;

	public void onCreate(Bundle savedInstanceState) {

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_detail_page);

		c = this.getApplicationContext();
		Intent intent = getIntent();
		String pstr = intent.getExtras().getString("product");
		String userid = intent.getExtras().getString("email");
		id = intent.getExtras().getInt("id");
		// pstr = pstr + ",</p>";

		Log.i(">>>>>>>>>>>>>>pstr", "" + pstr);

		product = ProductTranslator.queryToProduct(pstr);

		rb_reply = (RatingBar) findViewById(R.id.rb_reply);
		tv_reply_upload = (TextView) findViewById(R.id.tv_reply_upload);

		iv_front = (ImageView) findViewById(R.id.iv_front);
		iv_side = (ImageView) findViewById(R.id.iv_side);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_detail1 = (ImageView) findViewById(R.id.iv_detail1);
		iv_detail2 = (ImageView) findViewById(R.id.iv_detail2);

		iv_reply_btn = (ImageView) findViewById(R.id.iv_reply_btn);
		iv_like_btn = (ImageView) findViewById(R.id.iv_like_btn);
		iv_gostore_btn = (ImageView) findViewById(R.id.iv_gostore_btn);
		iv_back_btn = (ImageView) findViewById(R.id.iv_back_btn);
		iv_share_btn = (ImageView) findViewById(R.id.iv_share_btn);
		iv_next_btn = (ImageView) findViewById(R.id.iv_next_btn);
		iv_etc_btn = (ImageView) findViewById(R.id.iv_etc_btn);
		// iv_reply = (ImageView) findViewById(R.id.iv_reply);
		iv_photo_upload = (ImageView) findViewById(R.id.iv_photo_upload);

		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_hash1 = (TextView) findViewById(R.id.tv_hash1);
		tv_hash2 = (TextView) findViewById(R.id.tv_hash2);
		tv_hash3 = (TextView) findViewById(R.id.tv_hash3);
		tv_reply_cnt = (TextView) findViewById(R.id.tv_reply_cnt);

		lv_reply = (ListView) findViewById(R.id.lv_reply);
		topLayout = (LinearLayout) findViewById(R.id.topLayout);
		newDatailTitleBar = (LinearLayout) findViewById(R.id.newDatailTitleBar);

		ll_reply_btn = (LinearLayout) findViewById(R.id.ll_reply_btn);
		ll_hash = (LinearLayout) findViewById(R.id.ll_hash);
		ll_image = (LinearLayout) findViewById(R.id.ll_image);
		ll_menu = (LinearLayout) findViewById(R.id.ll_menu);
		ll_reply = (LinearLayout) findViewById(R.id.ll_reply);
		ll_reply_image = (LinearLayout) findViewById(R.id.ll_reply_img);

		et_reply_content = (BackPressEditText) findViewById(R.id.et_reply_content);

		replyListView = (HorizontalListView) findViewById(R.id.replyListView);

		iv_front.setOnClickListener(imgClick);
		iv_side.setOnClickListener(imgClick);
		iv_back.setOnClickListener(imgClick);
		iv_detail1.setOnClickListener(imgClick);
		iv_detail2.setOnClickListener(imgClick);
		imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

		setReplyListView();

		et_reply_content.setOnBackPressListener(new OnBackPressListener() {

			@Override
			public void onBackPress() {
				onBackPressed();
			}

		});

		iv_like_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Favorite fv = new Favorite(getApplicationContext());
				Like lk = new Like();

				fv.addFavorite(id + "");
				lk.addLike(id + "");
			}
		});

		rb_reply.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});

		iv_photo_upload.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				REQ_CODE_PICK_PICTURE = 0;
				Intent i = new Intent(Intent.ACTION_PICK);
				i.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				i.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, REQ_CODE_PICK_PICTURE);
			}
		});

		iv_share_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogSelectOption();
			}
		});

		ll_reply_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ll_hash.setVisibility(View.GONE);
				ll_image.setVisibility(View.GONE);
				ll_menu.setVisibility(View.GONE);
				ll_reply_image.setVisibility(View.GONE);
				ll_reply.setVisibility(View.VISIBLE);

				LinearLayout.LayoutParams position;

				position = new LinearLayout.LayoutParams(
						android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0,
						250);

				newDatailTitleBar.setLayoutParams(position);

				position = new LinearLayout.LayoutParams(
						android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0,
						1120);

				lv_reply.setLayoutParams(position);

				position = new LinearLayout.LayoutParams(
						android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0,
						550);

				ll_reply.setLayoutParams(position);

				et_reply_content.requestFocus();
				imm.showSoftInput(et_reply_content, 0);

			}

		});

		setItemDetail(product);
		setItemImages(String.valueOf(id));

		iv_back_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		String fontpath = "fonts/yoonGothic330.ttf";
		Typeface tf = Typeface.createFromAsset(NewDetailActivity.this
				.getResources().getAssets(), fontpath);
		String fontpath1 = "fonts/yoonGothic320.ttf";
		Typeface tf1 = Typeface.createFromAsset(NewDetailActivity.this
				.getResources().getAssets(), fontpath1);

		tv_title.setTypeface(tf);
		tv_hash1.setTypeface(tf1);
		tv_hash2.setTypeface(tf1);
		tv_hash3.setTypeface(tf1);
		tv_reply_cnt.setTypeface(tf1);
		tv_reply_upload.setTypeface(tf1);
		et_reply_content.setTypeface(tf1);

		tv_reply_upload.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Query q = new Query();
				Intent intent = getIntent();
				int id = intent.getExtras().getInt("id");
				String user_id = intent.getExtras().getString("email");
				float star_cnt = rb_reply.getRating();
				String content = et_reply_content.getText().toString();

				// INSERT INTO `ddmtest`.`reply` (`user_id`, `star_cnt`,
				// `content`, `reg_date`) VALUES ('1', 2, '3', '2015-05-10
				// 18:21:48');
				q.send("insert into `ddmtest`.`reply` (`id`, `user_id`, `star_cnt`, `content`, `reg_date`) values ('"
						+ id + "', '" + user_id

						+ "', '" + star_cnt + "', '" + content + "', now());");

				String sendImg = sdcard1 + "/replyimg/image1.jpg";

				image.Image.sendReplyImages(sendImg);

				Toast toast = Toast.makeText(getApplicationContext(),
						"댓글이등록되었습니다", Toast.LENGTH_SHORT);
				toast.show();
				setReplys();
				onBackPressed();
			}

		});
	}

	public void setReplyListView() {
		Query q = new Query();
		q.send("select id, kind, silhouette, meterial, hashtag1, hashtag2, hashtag3, hashtag4, name, prive, count, content, discount_rate, shop_name, reg_date, like_cnt from clothes where ");

		GetReplys gr = new GetReplys(158);
		ArrayList<Reply> replyData = gr.execute();

		for (int i = 0; i < replyData.size(); i++) {
			System.out.println(replyData.get(i));
		}

		final ReplyImageAdapter ha = new ReplyImageAdapter(replyData,
				getApplicationContext());

		replyListView.setAdapter(ha);
	}

	// **************************************************************************
	public void onBackPressed() {

		if (ll_reply.getVisibility() == View.GONE) {
			finish();
		} else {
			ll_hash.setVisibility(View.VISIBLE);
			ll_image.setVisibility(View.VISIBLE);
			ll_menu.setVisibility(View.VISIBLE);
			ll_reply_image.setVisibility(View.VISIBLE);
			ll_reply.setVisibility(View.GONE);
			et_reply_content.setText("");

			topLayout.setWeightSum(1920);

			LinearLayout.LayoutParams position;

			position = new LinearLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0, 160);

			newDatailTitleBar.setLayoutParams(position);

			position = new LinearLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0, 625);

			lv_reply.setLayoutParams(position);

			position = new LinearLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0, 350);

			ll_reply.setLayoutParams(position);
			imm.hideSoftInputFromWindow(et_reply_content.getWindowToken(), 0);
		}
	}

	private void setReplys() {
		GetReplyData grd = new GetReplyData(id);
		ArrayList<Reply> replyData = grd.execute();

		if (replyData != null) {
			lv_reply.setAdapter(new ReplyListViewAdapter(NewDetailActivity.this
					.getApplicationContext(), replyData, id));
			numReplys = replyData.size();
			numStars = getStars(replyData) / numReplys;
		} else {
			numReplys = 0;
			numStars = 0;

		}

	}

	protected void onStart() {
		super.onStart();
		setReplys();
		setItemDetail(product);
	}

	private int getStars(ArrayList<Reply> replyData) {
		int result = 0;
		for (int i = 0; i < replyData.size(); i++) {
			result += replyData.get(i).getStarRating();
		}

		return result;
	}

	private void setItemDetail(Product p) {
		String txt1 = "#" + p.getname();
		String txt2 = "#" + p.getkind() + "  #" + p.getMaterial();
		String txt3 = "#" + p.getHashtag1() + "  #" + p.getHashtag2();
		String txt4 = "#" + p.getprice();

		tv_reply_cnt.setText(numReplys + "");
		tv_title.setText(txt1);
		tv_hash1.setText(txt2);
		tv_hash2.setText(txt3);
		tv_hash3.setText(txt4);
	}

	private void setItemImages(String id) {
		String imgPaths[] = search.Search.searchImages(id);

		if (imgPaths == null) {
			return;
		}

		String tmpPath;

		tmpPath = EncodeUtil.encodeIfNeed(imgPaths[0]);
		Image.loadOriginalBitmap(tmpPath, c, iv_front);
		// front.setImageBitmap(img);

		tmpPath = EncodeUtil.encodeIfNeed(imgPaths[1]);
		Image.loadOriginalBitmap(tmpPath, c, iv_side);
		// side.setImageBitmap(img);

		tmpPath = EncodeUtil.encodeIfNeed(imgPaths[2]);
		Image.loadOriginalBitmap(tmpPath, c, iv_back);
		// back.setImageBitmap(img);

		tmpPath = EncodeUtil.encodeIfNeed(imgPaths[3]);
		Image.loadOriginalBitmap(tmpPath, c, iv_detail1);
		// detail1.setImageBitmap(img);

		tmpPath = EncodeUtil.encodeIfNeed(imgPaths[4]);
		Image.loadOriginalBitmap(tmpPath, c, iv_detail2);
		// detail2.setImageBitmap(img);

	}

	public View.OnClickListener imgClick = new View.OnClickListener() {

		public void onClick(View v) {
			Drawable t1 = iv_front.getDrawable();

			Bitmap tmpBitmap = ((BitmapDrawable) t1).getBitmap();

			Drawable t3 = iv_side.getDrawable();

			Bitmap tmpBitmap2 = ((BitmapDrawable) t3).getBitmap();

			Drawable t5 = iv_back.getDrawable();

			Bitmap tmpBitmap4 = ((BitmapDrawable) t5).getBitmap();

			Drawable t7 = iv_detail1.getDrawable();

			Bitmap tmpBitmap6 = ((BitmapDrawable) t7).getBitmap();

			Drawable t9 = iv_detail2.getDrawable();

			Bitmap tmpBitmap8 = ((BitmapDrawable) t9).getBitmap();

			if (v == iv_front) {

				showImage(tmpBitmap);
			}
			if (v == iv_side) {
				if (count == 0) {
					iv_front.setImageBitmap(tmpBitmap2);
					iv_side.setImageBitmap(tmpBitmap);
					count++;
				} else {
					iv_front.setImageBitmap(tmpBitmap);
					iv_side.setImageBitmap(tmpBitmap2);
					count--;
				}
			} else if (v == iv_back) {
				if (count == 0) {
					iv_front.setImageBitmap(tmpBitmap4);
					iv_back.setImageBitmap(tmpBitmap);
					count++;
				} else {
					iv_front.setImageBitmap(tmpBitmap);
					iv_back.setImageBitmap(tmpBitmap4);
					count--;
				}

			} else if (v == iv_detail1) {
				if (count == 0) {
					iv_front.setImageBitmap(tmpBitmap6);
					iv_detail1.setImageBitmap(tmpBitmap);
					count++;
				} else {
					iv_front.setImageBitmap(tmpBitmap);
					iv_detail1.setImageBitmap(tmpBitmap6);
					count--;
				}
			} else if (v == iv_detail2) {
				if (count == 0) {
					iv_front.setImageBitmap(tmpBitmap8);
					iv_detail2.setImageBitmap(tmpBitmap);
					count++;
				} else {
					iv_front.setImageBitmap(tmpBitmap);
					iv_detail2.setImageBitmap(tmpBitmap8);
					count--;
				}
			}
		}
	};

	public void showImage(Bitmap bm) {
		WindowManager wm = (WindowManager) c.getSystemService(c.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		final Dialog builder = new Dialog(this);
		builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
		builder.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
			}
		});
		ImageView imageView = new ImageView(this);
		imageView.setImageBitmap(bm);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				builder.dismiss();
			}
		});
		builder.addContentView(imageView, new LinearLayout.LayoutParams(
				width - 100, height * 1 / 2));
		builder.show();
	}

	private void DialogSelectOption() {
		final String items[] = { "BlueTooth", "카카오톡", "카카오스토리", "FaceBook" };
		Drawable t1 = iv_front.getDrawable();
		final Bitmap bm_front = ((BitmapDrawable) t1).getBitmap();

		AlertDialog.Builder ab = new AlertDialog.Builder(NewDetailActivity.this);
		// final Uri u = Uri.fromFile(new File(path));

		ab.setTitle("Select SNS");
		ab.setSingleChoiceItems(items, 0,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						x = whichButton;
					}
				})
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						if (x == 0) {
							Intent imageIntent = new Intent();
							imageIntent.setAction(Intent.ACTION_SEND);
							imageIntent.setType("image/*");
							imageIntent
									.setClassName("com.android.bluetooth",
											"com.android.bluetooth.opp.BluetoothOppLauncherActivity");
							imageIntent.putExtra(Intent.EXTRA_STREAM,
									getImageUri(c, bm_front));
							try {
								startActivity(imageIntent);
							} catch (Exception e) {
								e.printStackTrace();
							}

						} else if (x == 1) {
							Intent sendIntent = new Intent();
							sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
							sendIntent.setType("image/*");
							// sendIntent.putExtra(Intent.EXTRA_STREAM,
							// getImageUri(c, bm));
							sendIntent.putExtra(Intent.EXTRA_STREAM,
									getImageUri(c, bm_front));
							sendIntent.setPackage("com.kakao.talk");
							try {
								startActivity(sendIntent);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (x == 2) {
							Intent sendIntent = new Intent();
							sendIntent.setAction(Intent.ACTION_SEND);
							sendIntent.setType("image/*");
							sendIntent.putExtra(Intent.EXTRA_STREAM,
									getImageUri(c, bm_front));
							sendIntent.setPackage("com.kakao.story");
							try {
								startActivity(sendIntent);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (x == 3) {
							Intent sendIntent = new Intent();
							sendIntent.setAction(Intent.ACTION_SEND);
							sendIntent.putExtra(Intent.EXTRA_STREAM,
									getImageUri(c, bm_front));
							sendIntent.setType("image/*");
							sendIntent.setPackage("com.facebook.katana");
							try {
								startActivity(sendIntent);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}).setNegativeButton("Cancel", null);
		ab.show();
	}

	public Uri getImageUri(Context inContext, Bitmap inImage) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		String path = MediaStore.Images.Media.insertImage(
				inContext.getContentResolver(), inImage, "Title", null);
		return Uri.parse(path);
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
							getContentResolver()
									.openInputStream(data.getData()), null,
							option);
					SaveBitmapToFileCache(Image_photo1, sdcard1
							+ "/replyimg/image1.jpg");

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
