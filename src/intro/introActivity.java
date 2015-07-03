package intro;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.dongdong.R;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class introActivity extends Activity{
	
	LinearLayout intro;

    private Handler mHandler;
    private Runnable mRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	 if (android.os.Build.VERSION.SDK_INT > 9) {
             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                   .permitAll().build();
             StrictMode.setThreadPolicy(policy);
          }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        intro=(LinearLayout)findViewById(R.id.intro);

        
        
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext()
                        , login.LoginActivity.class);
                startActivity(intent);
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 2000);

    }
    
    protected void onStart(){
    	super.onStart();
    	intro.setBackgroundResource(R.drawable.intro);
    }
    
    protected void onStop(){
    	super.onStop();
    	intro.setBackground(null);
    	
    }

    @Override
    protected void onDestroy() {
        Log.i("test", "onDestory()");
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
        
    }
}