package clothes;

import query.Query;

public class Like {
	public void addLike(String id) {
		Query q = new Query();
		String query = "update clothes set like_cnt = like_cnt + 1 where  id = '" + id + "';";
		q.send(query);
	}
}
