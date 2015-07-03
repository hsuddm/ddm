package clothes;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class Favorite {
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private final String PREFERNAME = "favorite";
	Context forToast;

	public Favorite(Context c) {
		pref = c.getSharedPreferences("pref", Activity.MODE_PRIVATE);
		editor = pref.edit();
		forToast = c;
	}

	public ArrayList<String> getFavorites() {
		String result = pref.getString(PREFERNAME, null);
		if (result != null) {
			return parseFavorite(result);
		}
		return null;
	}

	public void addFavorite(String id) {
		String favorite = pref.getString(PREFERNAME, null);

		//테스트 필요함
		if (favorite == null) {
			addPreference(favorite, id);
			return;
		}

		if (!favorite.contains(id)) {
			addPreference(favorite, id);
			return;
		}
	}

	private void addPreference(String favorite, String id) {
		//맨앞에 null들어감..
		editor.putString(PREFERNAME, favorite + "," + id);
		editor.apply();
		Toast.makeText(forToast, "저장됨! 관심상품", Toast.LENGTH_LONG).show();
		return;
	}

	public void removeFavorites() {
		editor.remove(PREFERNAME);
	}

	public void removeFavoriteItem(String id) {
		//특정 아이디 상품만 삭제..
		String tmp = pref.getString(PREFERNAME, null);
		if (tmp == null)
			return;

		//테스트 필요
		tmp.replace("," + id, "");

		editor.putString(PREFERNAME, tmp);
		editor.apply();
	}

	private ArrayList<String> parseFavorite(String str) {
		ArrayList<String> result = new ArrayList<String>();
		String[] tmp = null;
		tmp = str.split(",");
		//맨앞엔 무조건 null이라 1번째 것부터
		for (int i = 1; i < tmp.length; i++) {
			result.add(tmp[i]);
		}
		return result;
	}

}
