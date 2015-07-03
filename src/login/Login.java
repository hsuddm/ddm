package login;

import query.Query;


public class Login {
	Query q;

	public Login() {
		q = new Query();
	}

	public String getMemberAccess(String email, String pw) {
		String acc = "success";
		if (!checkEmail(email)) {
			return "id";
		}
		if (!checkPassword(email, pw)) {
			return "password";
		}
		return acc;
	}

	public String getSellerAccess(String email, String pw) {
		String acc = "success";
		if (!checkSellerEmail(email)) {
			return "id";
		}
		if (!checkSellerPassword(email, pw)) {
			return "password";
		}
		return acc;
	}

	private boolean checkEmail(String email) {
		String tmp;

		tmp = q.send("select email from members where email = '" + email + "';");
		if (Query.doParse(tmp) == null) {
			return false;
		}

		return true;
	}

	private boolean checkPassword(String email, String pw) {
		String tmp;
		tmp = q.send("select password from members where email = '" + email
				+ "';");
		tmp = Query.doParse(tmp);

		tmp = Query.doParse(tmp).substring(3, tmp.length() - 5); // <p>1231231,</p>

		System.out.println("pw >> " + tmp + " pw >> " + pw);
		if (!tmp.equals(pw)) {
			return false;
		}
		return true;
	}

	private boolean checkSellerEmail(String email) {
		String tmp;
		tmp = q.send("select email from seller where email = '" + email
				+ "';");
		if (Query.doParse(tmp) == null) {
			return false;
		}
		return true;
	}

	private boolean checkSellerPassword(String email, String pw) {
		String tmp;
		tmp = q.send("select password from seller where email = '"
				+ email + "';");
		tmp = Query.doParse(tmp).substring(3, 5); // <p>1231231,</p>
		if (!tmp.equals(pw)) {
			return false;
		}
		return true;
	}

}