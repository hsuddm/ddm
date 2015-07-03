package join;

import query.Query;


public class Join {
	Query q;
	String name;
	String email;
	String pw;
	String phone;

	public Join(String phone, String name, String pw, String email) {
		q = new Query();
		this.name = name;
		this.email = email;
		this.pw = pw;
		this.phone = phone;
	}

	public void execute() {
		String query = "insert into `ddmtest`.`members` VALUES('" + phone
				+ "','" + name + "','" + pw + "','" + email + "');";
		q.send(query);
	}

}