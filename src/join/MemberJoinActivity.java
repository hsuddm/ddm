package join;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import query.Query;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongdong.R;

public class MemberJoinActivity extends Activity {

	private Spinner sp_e_mail = null;
	Spinner sp_personcheck;
	TextView tv_join_title;
	EditText et_name;
	EditText et_e_mail;
	EditText et_password;
	TextView tv_warning;
	EditText et_repassword;
	EditText et_phone;
	TextView tv_join;
	ImageView backpage;
	ImageView home;
	ImageView cart;
	ImageView favorite;
	ImageView iv_back_btn, iv_gray_line;
	TextView tv_agreement1, tv_agreement2, tv_agreement3, tv_agreement4;
	ArrayAdapter<CharSequence> adapter2;
	CheckBox check;
	private ArrayList<SpinnerData> data = new ArrayList<SpinnerData>();
	private SpinnerAdapter spinnerAdapter = null;
	private Context context = null;
	private int position;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.member_join);
		check = (CheckBox) findViewById(R.id.check);
		context = this;
		sp_e_mail = (Spinner) findViewById(R.id.sp_e_mail);
		data.add(new SpinnerData("직접입력", "0"));
		data.add(new SpinnerData("@naver.com", "1"));
		data.add(new SpinnerData("@hanmail.net", "2"));
		data.add(new SpinnerData("@nate.com", "3"));
		data.add(new SpinnerData("@hotmail.com", "4"));
		data.add(new SpinnerData("@yahoo.com", "5"));
		data.add(new SpinnerData("@paran.com", "6"));
		setSpinnerAdapter(data);

		iv_gray_line = (ImageView) findViewById(R.id.iv_gray_line);
		iv_back_btn = (ImageView) findViewById(R.id.iv_back_btn);
		tv_join_title = (TextView) findViewById(R.id.tv_join_title);
		et_name = (EditText) findViewById(R.id.et_name);
		et_e_mail = (EditText) findViewById(R.id.et_e_mail);
		et_password = (EditText) findViewById(R.id.et_password);
		tv_warning = (TextView) findViewById(R.id.tv_warning);
		et_repassword = (EditText) findViewById(R.id.et_repassword);
		et_phone = (EditText) findViewById(R.id.et_phone);
		tv_join = (TextView) findViewById(R.id.tv_join);
		backpage = (ImageView) findViewById(R.id.backpage);
		home = (ImageView) findViewById(R.id.home);
		favorite = (ImageView) findViewById(R.id.favorite);
		cart = (ImageView) findViewById(R.id.cart);
		tv_agreement1 = (TextView) findViewById(R.id.tv_agreement1);
		tv_agreement2 = (TextView) findViewById(R.id.tv_agreement2);
		tv_agreement3 = (TextView) findViewById(R.id.tv_agreement3);
		tv_agreement4 = (TextView) findViewById(R.id.tv_agreement4);

		String fontpath = "fonts/aoldshower.ttf";
		Typeface tf = Typeface.createFromAsset(MemberJoinActivity.this
				.getResources().getAssets(), fontpath);
		String fontpath1 = "fonts/yoonGothic310.ttf";
		Typeface tf1 = Typeface.createFromAsset(MemberJoinActivity.this
				.getResources().getAssets(), fontpath1);
		String fontpath2 = "fonts/yoonGothic320.ttf";
		Typeface tf2 = Typeface.createFromAsset(MemberJoinActivity.this
				.getResources().getAssets(), fontpath2);
		String fontpath3 = "fonts/yoonGothic330.ttf";
		Typeface tf3 = Typeface.createFromAsset(MemberJoinActivity.this
				.getResources().getAssets(), fontpath3);

		tv_join_title.setTypeface(tf);
		et_name.setTypeface(tf1);
		et_e_mail.setTypeface(tf1);
		et_password.setTypeface(tf1);
		tv_warning.setTypeface(tf1);
		et_repassword.setTypeface(tf1);
		et_phone.setTypeface(tf1);
		tv_join.setTypeface(tf2);
		tv_agreement1.setTypeface(tf3);
		tv_agreement2.setTypeface(tf1);
		tv_agreement3.setTypeface(tf3);
		tv_agreement4.setTypeface(tf1);

		sp_personcheck = (Spinner) findViewById(R.id.sp_personcheck);

		adapter2 = ArrayAdapter.createFromResource(this, R.array.check,
				R.layout.spinner_item);

		adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);

		sp_personcheck.setAdapter(adapter2);

		sp_personcheck
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent, View v,
							int position, long id) {
						String fontpath1 = "fonts/yoonGothic310.ttf";
						Typeface tf1 = Typeface.createFromAsset(
								MemberJoinActivity.this.getResources()
										.getAssets(), fontpath1);
						((TextView) parent.getChildAt(0)).setTypeface(tf1);
					}

					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

		tv_join.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name, email, pw, rpw, phone, sub;
				position = sp_e_mail.getSelectedItemPosition();
				SpinnerData  sp_e_mail_item = (SpinnerData)sp_e_mail.getAdapter().getItem(position);
				name = et_name.getText().toString();
				email = et_e_mail.getText().toString();
				pw = et_password.getText().toString();
				phone = et_phone.getText().toString();
				rpw = et_repassword.getText().toString();
				sub = sp_e_mail_item.title;
				Query q = new Query();
				String result;
				if (sub.toString().equals("직접입력")) {
					result = q.send("select email from members where email='"
							+ email + "';");
				} else {
					result = q.send("select email from members where email='"
							+ email + sub + "';");
				}

				if (result.contains("<p>")) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"중복 e_mail이 있습니다.", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}

				if (name.length() < 2) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"이름을 입력해 주세요.", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				if (Namevalidate(name) == false) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"이름을 한글로 입력해 주세요.", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				if (email.length() < 3) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"e_mail을 입력해 주세요.", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				if (Emailvalidate(email) == true) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"e_mail형식을 확인해 주세요.", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				if (pw.length() < 5 || !pw.equals(rpw)) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				if (Passwrodvalidate(pw) == false) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"비밀번호를 영문,숫자 조합으로 입력해 주세요", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				if (phone.length() < 9) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"휴대폰 번호를 입력해 주세요.", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				if (check.isChecked() == false) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"약관에 동의해 주세요", Toast.LENGTH_SHORT);
					toast.show();
					return;
				}
				// 양식확인..
				if (sub.toString().equals("직접입력")) {
					Join join = new Join(phone, name, pw, email);
					join.execute();
				} else {
					Join join = new Join(phone, name, pw, email + sub);
					join.execute();
				}
				Intent intent = new Intent(getApplicationContext(),
						login.LoginActivity.class); // 로그인 페이지로 이동
				Toast toast = Toast.makeText(getApplicationContext(),
						"가입이 완료 되었습니다.", Toast.LENGTH_SHORT);
				toast.show();
				startActivity(intent);
			}
		});
	}

	private void setSpinnerAdapter(ArrayList<SpinnerData> data) {
		// TODO Auto-generated method stub
		if (null != data) {
			if (null == spinnerAdapter) {
				spinnerAdapter = new join.SpinnerAdapter(context, data);
				sp_e_mail.setAdapter(spinnerAdapter);
				sp_e_mail
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
				((join.SpinnerAdapter) spinnerAdapter).setChangeData(data);
			}
		}

	}

	protected void onStart() {
		super.onStart();
		iv_back_btn.setBackgroundResource(R.drawable.back_btn);
		tv_join_title.setBackgroundResource(R.drawable.under_line);
		iv_gray_line.setImageResource(R.drawable.gray_line);
		tv_join.setBackgroundResource(R.drawable.join_button);

		backpage.setImageResource(R.drawable.back);
		home.setImageResource(R.drawable.home);
		cart.setImageResource(R.drawable.cart);
		favorite.setImageResource(R.drawable.favorite);

	}

	@SuppressWarnings("deprecation")
	protected void onStop() {
		super.onStop();

		iv_back_btn.setBackgroundDrawable(null);
		tv_join_title.setBackgroundDrawable(null);
		iv_gray_line.setImageDrawable(null);
		tv_join.setBackgroundDrawable(null);
		backpage.setImageDrawable(null);
		home.setImageDrawable(null);
		cart.setImageDrawable(null);
		favorite.setImageDrawable(null);

	}

	private static final String Passwrod_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=[\\S]+$).{6,16}$";

	public boolean Passwrodvalidate(final String hex) {
		Pattern pattern = Pattern.compile(Passwrod_PATTERN);
		Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	private static final String Name_PATTERN = "^(?=.*[가-힣])(?=[\\S]+$).{0,5}$";

	public boolean Namevalidate(final String hex) {
		Pattern pattern = Pattern.compile(Name_PATTERN);
		Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	private static final String Email_PATTERN = "^(?=.*[가-힣ㄱ-ㅎㅏ-ㅣ])(?=[\\S]+$).{0,16}$";

	public boolean Emailvalidate(final String hex) {
		Pattern pattern = Pattern.compile(Email_PATTERN);
		Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	public class SpinnerData {
		public String title = "";
		public String num = "";

		public SpinnerData(String title, String num) {
			this.title = title;
			this.num = num;
		}
	}

	private static final String password_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=[\\S]+$).{6,16}$";

	public boolean passwordvalidate(final String hex) {
		Pattern pattern = Pattern.compile(password_PATTERN);
		Matcher matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}
