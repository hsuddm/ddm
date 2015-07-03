package cunsumercenter;

import main.MainActivity;
import store.FavoriteProductListActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongdong.R;

public class MailActivity extends Activity {
	private TextView tvUserEmail, tvContent;
	private EditText etUserEmail, etMailContent;
	private String mailSubject = "오류문의";;
	private RadioButton btRadio1, btRadio2, btRadio3, btRadio4, btRadio5, btRadio6;
	private Button btMailSend;
	private ImageButton btTopBack, btDownBack, btHome, btProduct;
	
	private Typeface tf310;

	protected void onStart() {
		super.onStart();
		etUserEmail.setBackgroundResource(R.drawable.login_blank);
		etMailContent.setBackgroundResource(R.drawable.login_blank);
		btMailSend.setBackgroundResource(R.drawable.cscenter_btn_send);
	}

	@Override
	protected void onStop() {
		super.onStop();
		etUserEmail.setBackgroundDrawable(null);
		etMailContent.setBackgroundDrawable(null);
		btMailSend.setBackgroundDrawable(null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cunsumer_center);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		String font310Path = "fonts/yoonGothic330.ttf";
		String font330Path = "fonts/yoonGothic330.ttf";
		String font350Path = "fonts/yoonGothic330.ttf";
		tf310 = Typeface.createFromAsset(MailActivity.this.getResources().getAssets(), font310Path);
		Typeface tf330 = Typeface.createFromAsset(MailActivity.this.getResources().getAssets(), font330Path);
		Typeface tf350 = Typeface.createFromAsset(MailActivity.this.getResources().getAssets(), font350Path);

		tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);
		tvUserEmail.setTypeface(tf330);
		
		etUserEmail = (EditText) findViewById(R.id.etUserEmail);
		etUserEmail.setTypeface(tf330);
		
		tvContent = (TextView) findViewById(R.id.tvContent);
		tvContent.setTypeface(tf330);
		
		etMailContent = (EditText) findViewById(R.id.etMailContent);
		etMailContent.setTypeface(tf310);
		
		setRadioBtn();
		setImgBtn();

		btMailSend = (Button) findViewById(R.id.btMailSend);
		btMailSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				boolean mailFlag = true;

				final String userEmail = etUserEmail.getText().toString();
				if (userEmail.length() == 0) {
					mailFlag = false;
					Toast.makeText(MailActivity.this, "메일을 정확히 입력하세요", Toast.LENGTH_SHORT).show();
				}

				final String emailSubject = userEmail + "," + mailSubject;

				final String emailContent = etMailContent.getText().toString();
				if (emailContent.length() < 10) {
					mailFlag = false;
					Toast.makeText(MailActivity.this, "문의 내용은 10글자 이상 입력해주세요", Toast.LENGTH_SHORT).show();
				}

				if (mailFlag) {
					Thread mailThread = new Thread() {
						@Override
						public void run() {
							try {
								MailSender sender = new MailSender("parangsaecrew@gmail.com", "vkfkdtozmfn111");
								// 제목, 내용, 보내는 사람(안먹힘), 받는 사람
								sender.sendMail(emailSubject, emailContent, userEmail, "saehyun91@gmail.com");
								Toast.makeText(MailActivity.this, "문의 사항이 접수되었습니다", Toast.LENGTH_SHORT).show();
							} catch (Exception e) {
								Log.e("SendMail", e.getMessage(), e);
							}
						}
					};
					mailThread.run();
				}

			}
		});

	}

	private void setRadioBtn() {
		btRadio1 = (RadioButton) findViewById(R.id.btRadio1);
		btRadio2 = (RadioButton) findViewById(R.id.btRadio2);
		btRadio3 = (RadioButton) findViewById(R.id.btRadio3);
		btRadio4 = (RadioButton) findViewById(R.id.btRadio4);
		btRadio5 = (RadioButton) findViewById(R.id.btRadio5);
		btRadio6 = (RadioButton) findViewById(R.id.btRadio6);
		
		btRadio1.setTypeface(tf310);
		btRadio2.setTypeface(tf310);
		btRadio3.setTypeface(tf310);
		btRadio4.setTypeface(tf310);
		btRadio5.setTypeface(tf310);
		btRadio6.setTypeface(tf310);

		OnClickListener ocl = new OnClickListener() {
			@Override
			public void onClick(View view) {
				btRadio1.setChecked(false);
				btRadio2.setChecked(false);
				btRadio3.setChecked(false);
				btRadio4.setChecked(false);
				btRadio5.setChecked(false);
				btRadio6.setChecked(false);

				switch (view.getId()) {
				case R.id.btRadio1:
					btRadio1.setChecked(true);
					mailSubject = "오류문의";
					break;
				case R.id.btRadio2:
					btRadio2.setChecked(true);
					mailSubject = "회원정보 문의";
					break;
				case R.id.btRadio3:
					btRadio3.setChecked(true);
					mailSubject = "이벤트";
					break;
				case R.id.btRadio4:
					btRadio4.setChecked(true);
					mailSubject = "업소정보 문의";
					break;
				case R.id.btRadio5:
					btRadio5.setChecked(true);
					mailSubject = "제휴문의";
					break;
				case R.id.btRadio6:
					btRadio6.setChecked(true);
					mailSubject = "기타";
					break;
				}
			}
		};

		btRadio1.setOnClickListener(ocl);
		btRadio2.setOnClickListener(ocl);
		btRadio3.setOnClickListener(ocl);
		btRadio4.setOnClickListener(ocl);
		btRadio5.setOnClickListener(ocl);
		btRadio6.setOnClickListener(ocl);
	}


	private void setImgBtn(){
		btTopBack = (ImageButton) findViewById(R.id.btnMailTopBack);
		btTopBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btDownBack = (ImageButton) findViewById(R.id.btnMailDownBack);
		btDownBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		btHome = (ImageButton) findViewById(R.id.btnMailHome);
		btHome.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		
		btProduct = (ImageButton) findViewById(R.id.btnMailProduct);
		btProduct.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), FavoriteProductListActivity.class);				
				startActivity(intent);
			}
		});
	}
}
