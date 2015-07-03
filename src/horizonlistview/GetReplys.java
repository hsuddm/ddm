package horizonlistview;

import java.util.ArrayList;

import query.Query;
import reply.Reply;
import reply.ReplyTranslator;


public class GetReplys {
	private int id;
	private ArrayList<Reply> replyData;

	public GetReplys(int id) {
		this.id = id;
		this.replyData = new ArrayList<Reply>();
	}

	public ArrayList<Reply> execute() {
		Query q = new Query();
		String tmp = q.send("select id, user_id, star_cnt, content, reg_date, answer from reply where id='"
						+ id + "' order by reg_date DESC;");

		if (tmp == null) {
			System.out.println("쿼리값 안나옴");
			return null;
		}

		tmp = Query.doParse(tmp);
		
		String forSplit[] = tmp.split("\n");
		Reply reply = null;

		for (int i = 0; i < forSplit.length; i++) {
			reply = ReplyTranslator.queryToReply(forSplit[i]);
			ReplyTranslator.queryToReply(forSplit[i]);
			replyData.add(reply);
		}

		return replyData;
	}

}
