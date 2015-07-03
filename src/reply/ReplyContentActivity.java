package reply;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;

import com.example.dongdong.R;

public class ReplyContentActivity extends Activity{
	
	RatingBar mRating;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reply);

	
	mRating = (RatingBar) findViewById(R.id.rb_replyRatingbar);
	mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
			// TODO Auto-generated method stub

		}
	});
	}
}
