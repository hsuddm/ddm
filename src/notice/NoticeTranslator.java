package notice;

public class NoticeTranslator {
	static public Notice queryToNotice(String str){
		String tmp = str.substring(3, str.length() - 5);
		String[] noticeEntry = tmp.split(",");
		
		String title = noticeEntry[0];
		String content = noticeEntry[1];
		String date = noticeEntry[2];
		
		Notice nData = new Notice(title, content, date);
		
		return nData;
	}
	
	static public String NoticeToQuery(Notice notice){
		return "not implemented yet!";
	}
}
