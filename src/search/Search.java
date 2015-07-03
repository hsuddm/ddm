package search;

import query.Query;
import query.Server;

public class Search {

	static String[] searchItem(String keyword) {
		Query q = new Query();

		String query = "select id,shop_name,kind,name from clothes where name like '%" + keyword
				+ "%' or " + "kind like '%" + keyword + "%' or material like  '%" + keyword
				+ "%' or hashtag1 like '%" + keyword + "%' or " + "hashtag2 like '%" + keyword
				+ "%' or content like '%" + keyword + "%' or shop_name like '%" + keyword + "%';";

		String str = q.send(query);
		if (str == null) {
			return null;
		}

		String result = Query.doParse(str);

		if (result == null) {
			return null;
		}

		String forPaths[] = result.split("\n");
		String tmp;
		String tmps[];
		// <p> d,d,d,</p> p吏쒎뜝�룞�삕�뜝�룞�삕
		for (int i = 0; i < forPaths.length; i++) {
			tmp = forPaths[i].substring(3, forPaths[i].length() - 5);
			tmps = tmp.split(","); // shop_name, kind, name 3�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕//
									// id�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 1�뜝�룞�삕�뜝�룞�삕
			forPaths[i] = "http://" + Server.ip + "/server/img/" + tmps[1] + "/" + tmps[2] + "/"
					+ tmps[3] + "/front.jpg" + tmps[0];

		}
		return forPaths;
	}

	static String[] searchItem(String keyword, String category) {
		Query q = new Query();

		String query = "select id,shop_name,kind,name from clothes where kind='" + category
				+ "' and (name like '%" + keyword + "%' or " + "kind like '%" + keyword
				+ "%' or material like  '%" + keyword + "%' or hashtag1 like '%" + keyword
				+ "%' or " + "hashtag2 like '%" + keyword + "%' or content like '%" + keyword
				+ "%' or shop_name like '%" + keyword + "%');";

		String str = q.send(query);
		if (str == null) {
			return null;
		}

		String result = Query.doParse(str);

		if (result == null) {
			return null;
		}

		String forPaths[] = result.split("\n");
		String tmp;
		String tmps[];
		// <p> d,d,d,</p> p吏쒎뜝�룞�삕�뜝�룞�삕
		for (int i = 0; i < forPaths.length; i++) {
			tmp = forPaths[i].substring(3, forPaths[i].length() - 5);
			tmps = tmp.split(","); // shop_name, kind, name 3�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕//
									// id�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 1�뜝�룞�삕�뜝�룞�삕
			forPaths[i] = "http://" + Server.ip + "/server/img/" + tmps[1] + "/" + tmps[2] + "/"
					+ tmps[3] + "/front.jpg" + tmps[0];

		}
		return forPaths;
	}

	public static String[] searchImages(String id) {
		Query q = new Query();

		String query = "select id,shop_name,kind,name from clothes where id ='" + id + "';";
		String str = q.send(query);
		if (str == null) {
			return null;
		}

		String result = Query.doParse(str);

		if (result == null) {
			return null;
		}

		// <p>id,shopname,kind,name,</p>
		String tmp = result.substring(3, result.length() - 5);
		String tmps[] = tmp.split(",");

		String forPaths[] = new String[5];

		// <p> d,d,d,d,</p> p吏쒎뜝�룞�삕�뜝�룞�삕
		forPaths[0] = "http://" + Server.ip + "/server/img/" + tmps[1] + "/" + tmps[2] + "/"
				+ tmps[3] + "/front.jpg";
		forPaths[1] = "http://" + Server.ip + "/server/img/" + tmps[1] + "/" + tmps[2] + "/"
				+ tmps[3] + "/side.jpg";
		forPaths[2] = "http://" + Server.ip + "/server/img/" + tmps[1] + "/" + tmps[2] + "/"
				+ tmps[3] + "/back.jpg";
		forPaths[3] = "http://" + Server.ip + "/server/img/" + tmps[1] + "/" + tmps[2] + "/"
				+ tmps[3] + "/detail1.jpg";
		forPaths[4] = "http://" + Server.ip + "/server/img/" + tmps[1] + "/" + tmps[2] + "/"
				+ tmps[3] + "/detail2.jpg";

		return forPaths;
	}
}