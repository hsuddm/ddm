package mypage;

import query.Query;
import store.FavoriteProductListActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import clothes.Favorite;

import com.example.dongdong.R;

import cunsumercenter.MailActivity;

public class MyPageActivity extends Activity {
   private TextView nickName, welcome, point, point2, favoriteNum,
         favoriteNum2, menu1, menu2, menu3, menu4;
   private SharedPreferences pref;
   private final String PREFERNAME = "favorite";

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_my_page);

      if (android.os.Build.VERSION.SDK_INT > 9) {
         StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
               .permitAll().build();
         StrictMode.setThreadPolicy(policy);
      }

      Intent intent = getIntent();
      String email = intent.getStringExtra("");
      // 인텐트 받은 이메일로 댓글수 등가져오는 함수 수정해서 사용해야함.

      nickName = (TextView) findViewById(R.id.nickname);
      welcome = (TextView) findViewById(R.id.welcome);
      point = (TextView) findViewById(R.id.myPoint);
      point2 = (TextView) findViewById(R.id.myPoint2);
      favoriteNum = (TextView) findViewById(R.id.favoriteProductNum);
      favoriteNum2 = (TextView) findViewById(R.id.favoriteProductNum2);
      menu1 = (TextView) findViewById(R.id.myPageMenu1);
      menu2 = (TextView) findViewById(R.id.myPageMenu2);
      menu3 = (TextView) findViewById(R.id.myPageMenu3);
      menu4 = (TextView) findViewById(R.id.myPageMenu4);

      setFont();
      setPoint();
      // setFavoriteTestCode();
      setFavorite();

      // 관심상품
      menu1.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View arg0) {
            Favorite f = new Favorite(getApplicationContext());
            
            //관심상품 등록 기능이 완성안되서 수동으로 158번하나 넣어준거임..고쳐야됨
            f.addFavorite("158");
            f.addFavorite("159");
            f.addFavorite("160");
            f.addFavorite("161");
            f.addFavorite("162");
            f.addFavorite("163");
            f.addFavorite("164");
            
            
            Intent intent = new Intent(getApplicationContext(),
                  FavoriteProductListActivity.class);
            startActivity(intent);
         }

      });

      // 고객센터
      menu2.setOnClickListener(new OnClickListener() {

        
         @Override
			public void onClick(View arg0) {
				Intent mailIntent = new Intent(getApplicationContext(), MailActivity.class);
				startActivity(mailIntent);
			}

      });

      // 나의 Q&A
      menu3.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View arg0) {

         }

      });

      // 회원정보 수정
      menu4.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View arg0) {

         }

      });
   }

   public void setFont() {

      Typeface yoonGodic330 = Typeface.createFromAsset(getAssets(),
            "fonts/yoonGothic330.ttf");
      Typeface yoonGodic350 = Typeface.createFromAsset(getAssets(),
            "fonts/yoonGothic350.ttf");

      nickName.setTypeface(yoonGodic350);
      welcome.setTypeface(yoonGodic330);
      point.setTypeface(yoonGodic330);
      point2.setTypeface(yoonGodic330);
      favoriteNum.setTypeface(yoonGodic330);
      favoriteNum2.setTypeface(yoonGodic330);
      menu1.setTypeface(yoonGodic350);
      menu2.setTypeface(yoonGodic350);
      menu3.setTypeface(yoonGodic350);
      menu4.setTypeface(yoonGodic350);
   }

   public void setPoint() {
      Query q = new Query();
      String tmp = q
            .send("select count(*) from reply where id='email@gmail.com';");
      System.out.println(tmp);
      if (tmp == null) {
         point.setText("fail");
         return;
      }
      String info = Query.doParse(tmp);
      System.out.println(info);

      String value = info.substring(3, info.length() - 5);
      System.out.println(point);

      point.setText(value);
   }

   public void setFavorite() {

      favoriteNum.setText(getFavoritesSize() + "");
   }

   public int getFavoritesSize() {
      pref = this.getApplicationContext().getSharedPreferences("pref",
            Activity.MODE_PRIVATE);
      String result = pref.getString(PREFERNAME, null);
//      System.out.println(result);
      if (result != null) {
         String[] tmp = null;
         tmp = result.split(",");
         return tmp.length - 1;
      }
      return 0;

   }

   // public void setFavoriteTestCode() {
   // pref = this.getApplicationContext().getSharedPreferences("pref",
   // Activity.MODE_PRIVATE);
   // SharedPreferences.Editor editor;
   // editor = pref.edit();
   //
   // editor.putString(PREFERNAME,
   // "dd,ff,33,ww,ff,gg,rr,hh,df,d,f,d,s,df,we,f,qe,f,asd,f,asd,asdv");
   // editor.apply();
   // }
}