package query;

public class Server {
	public static final String ip = "223.194.128.171:80";
//	public static final String ip = "1.255.55.52:8080";
	
	static final String queryAddress = "http://" + Server.ip
			+ "/server/jsp/query.jsp";
	public static final String imgAddress = "http://" + Server.ip + "/server/jsp/imgtest.jsp";
	public static final String replyimgAddress = "http://" + Server.ip + "/server/jsp/replyimg.jsp";
	public static final String storeimgAddress = "http://" + Server.ip + "/server/jsp/StoreMainImage.jsp";
}
