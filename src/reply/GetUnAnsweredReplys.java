package reply;

import java.util.ArrayList;

import query.Query;

public class GetUnAnsweredReplys {
	String email;

	public GetUnAnsweredReplys(String email) {
		super();
		this.email = email;
	}

	public ArrayList<Reply> getData() {
		Query q = new Query();
		//쿼리문 수정하기 매장이름,등은 이제 받은걸로 교체해야함
		String query = "select id, user_id, star_cnt, content, reg_date, answer from reply "
				+ "where shop_name="
				+ "(select shop_name from sellers where email='" + email + "') "
				+ "and answer='0' order by reg_date;";

		String tmp = q.send(query);

		tmp = Query.doParse(tmp);

		if (tmp == null) {
			return null;
		}

		String forSplit[] = tmp.split("\n");
		Reply reply = null;
		ArrayList<Reply> replyData = new ArrayList<Reply>();
		for (int i = 0; i < forSplit.length; i++) {
			reply = ReplyTranslator.queryToReply(forSplit[i]);
			replyData.add(reply);
		}
		return replyData;
	}
}
