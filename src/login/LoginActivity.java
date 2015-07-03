package login;

import join.MemberJoinActivity;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongdong.R;

public class LoginActivity extends Activity {
	EditText et_idaddress, et_password;
	TextView tv_login_btn;
	ImageView iv_memjoin, iv_question, iv_dongdong;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	protected void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);

		et_idaddress = (EditText) findViewById(R.id.et_idaddress);
		et_password = (EditText) findViewById(R.id.et_password);
		tv_login_btn = (TextView) findViewById(R.id.tv_login_btn);
		iv_memjoin = (ImageView) findViewById(R.id.iv_memjoin);
		iv_question = (ImageView) findViewById(R.id.iv_question);
		iv_dongdong = (ImageView) findViewById(R.id.iv_dongdong);

		String fontpath = "fonts/yoonGothic310.ttf";
		Typeface tf = Typeface.createFromAsset(LoginActivity.this
				.getResources().getAssets(), fontpath);
		String fontpath1 = "fonts/yoonGothic320.ttf";
		Typeface tf1 = Typeface.createFromAsset(LoginActivity.this
				.getResources().getAssets(), fontpath1);

		et_idaddress.setTypeface(tf);
		et_password.setTypeface(tf);
		tv_login_btn.setTypeface(tf1);

		SharedPreferences pref = getSharedPreferences("pref",
				Activity.MODE_PRIVATE);

		String id = pref.getString("id", "empty");
		String pw = pref.getString("pw", "empty");
		if (!id.equals("empty") && !pw.equals("empty")) {
			String tmp = checkId(id, pw);
			if (tmp.equals("success")) {
				Intent i = new Intent(getApplicationContext(),
						main.MainActivity.class);
				i.putExtra("email", id);
				startActivity(i);
			}
		}

		iv_memjoin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						MemberJoinActivity.class);
				startActivity(i);
				// finish();
			}
		});

		tv_login_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				new AsyncTask<Void, Void, String>() {

					@Override
					protected void onPostExecute(String result) {
						super.onPostExecute(result);
						System.out.println(result);
						if (result.equals("id")) {
							Toast.makeText(getApplicationContext(), "아이디 확인!",
									Toast.LENGTH_LONG).show();
						}
						if (result.equals("password")) {
							Toast.makeText(getApplicationContext(), "비밀번호 확인!",
									Toast.LENGTH_LONG).show();
						}
						if (result.equals("success")) {
							Toast.makeText(getApplicationContext(), "접속!",
									Toast.LENGTH_LONG).show();
							Intent i = new Intent(getApplicationContext(),
									main.MainActivity.class);
							String id = et_idaddress.getText().toString();
							i.putExtra("email", id);
							startActivity(i);
						}

					}

					@Override
					protected String doInBackground(Void... params) {

						String id = et_idaddress.getText().toString();
						String password = et_password.getText().toString();
						return checkId(id, password);
					}

				}.execute();
			}
		});
		saveLoginData();
	}

	private String checkId(String id, String pw) {
		Login login = new Login();
		String tmp = login.getMemberAccess(id, pw);
		return tmp;
	}

	protected void onStart() {
		super.onStart();

		iv_dongdong.setImageResource(R.drawable.login_dongdong);
		et_idaddress.setBackgroundResource(R.drawable.login_blank);
		et_password.setBackgroundResource(R.drawable.login_blank);
		tv_login_btn.setBackgroundResource(R.drawable.login_button);
		iv_memjoin.setImageResource(R.drawable.member_join);
		iv_question.setImageResource(R.drawable.login_question);

	}

	@Override
	protected void onStop() {
		super.onStop();

		iv_dongdong.setImageDrawable(null);
		et_idaddress.setBackgroundDrawable(null);
		et_password.setBackgroundDrawable(null);
		tv_login_btn.setBackgroundDrawable(null);
		iv_memjoin.setImageDrawable(null);
		iv_question.setImageDrawable(null);

	}

	private void saveLoginData() {
		SharedPreferences pref = getSharedPreferences("pref",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();

		editor.putString("id", et_idaddress.getText().toString());
		editor.putString("pw", et_password.getText().toString());
		editor.apply();
	}
}
