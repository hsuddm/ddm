package notice;

import java.util.ArrayList;

import query.Query;

public class GetNoticeData {
	private ArrayList<Notice> noticeData;
	
	public GetNoticeData(){
		this.noticeData = new ArrayList<Notice>();
	}
	
	public ArrayList<Notice> excute(){
		Query q = new Query();
		String tmp = q.send("select title, content, date from notice order by date DESC;");
		
		tmp = Query.doParse(tmp);
		
		if(tmp == null){
			return null;
		}
		
		String forSplit[] = tmp.split("\n");
		Notice notice = null;
		for(int i=0; i<forSplit.length; i++){
			notice = NoticeTranslator.queryToNotice(forSplit[i]);
			noticeData.add(notice);
		}
		
		return noticeData;
	}

}
