package query;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.util.Log;

public class Query {

	public String send(String query) {
		String result = "";
		try {
			String data = URLEncoder.encode("query", "UTF-8") + "="
					+ URLEncoder.encode(query, "UTF-8");

			URL url = new URL(Server.queryAddress);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			String str = "";
			String queryType = query.split(" ")[0]; // select or insert or
													// delete...
			System.out.println("Query type is " + queryType);

			if (queryType.equals("select")) {
				while ((line = rd.readLine()) != null) {
					str = str + line;
				}
				rd.close();
				System.out.println(str);
				result = str;
			}
			rd.close();
			wr.close();

		} catch (Exception e) {
			System.out.println("send fail!");
			e.printStackTrace();
			result = null;
		}

		return result;
	}

	static public String doParse(String str) {
		String result = "";

		if (!str.contains("<p>")) {

			return null;
		}
		Document doc = Jsoup.parse(str);
		Elements e = doc.select("p");
		result = e.toString();

		return result;
	}
}
