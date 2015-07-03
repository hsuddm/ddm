package reply;

import java.util.ArrayList;

import query.Query;

public class GetReplyData {
	private int id;
	private ArrayList<Reply> replyData;

	public GetReplyData(int id) {
		this.id = id;
		this.replyData = new ArrayList<Reply>();
	}

	public ArrayList<Reply> execute() {
		Query q = new Query();
		String tmp = q
				.send("select id, user_id, star_cnt, content, reg_date, answer from reply where id='"
						+ id + "' order by reg_date DESC;");

		tmp = Query.doParse(tmp);

		if (tmp == null) {
			return null;
		}

		String forSplit[] = tmp.split("\n");
		Reply reply = null;

		for (int i = 0; i < forSplit.length; i++) {
			reply = ReplyTranslator.queryToReply(forSplit[i]);
			replyData.add(reply);
		}

		return replyData;
	}

}
