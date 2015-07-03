package store;

import java.util.ArrayList;

import query.Query;
import shop.Shop;
import shop.ShopTranslator;

public class GetStoresData {
	private String complex;
	private ArrayList<Shop> shopData;
	private int floor;

	// 해당 건물 매장 전부
	public GetStoresData(String complex) {
		this.complex = complex;
		this.shopData = new ArrayList<Shop>();
		this.floor = 0;
	}

	// 해당 건물 층수별 매장
	public GetStoresData(String complex, int floor) {
		this.floor = floor;
		this.complex = complex;
		this.shopData = new ArrayList<Shop>();
	}

	public ArrayList<Shop> execute() {
		Query q = new Query();
		String tmp;
		if (floor == 0) {
			tmp = q.send("select shop_name,name,crnumber,phone,email,password,complex,floor,unit,shop_number,introduction from sellers where complex='"
					+ complex + "';");
		} else {
			tmp = q.send("select shop_name,name,crnumber,phone,email,password,complex,floor,unit,shop_number,introduction from sellers "
					+ "where complex='" + complex + "' and floor ='" + floor + "';");
		}

		tmp = Query.doParse(tmp);

		if (tmp == null) {
			return null;
		}

		String forSplit[] = tmp.split("\n");
		Shop unit = null;

		for (int i = 0; i < forSplit.length; i++) {
			unit = ShopTranslator.queryToShop(forSplit[i]);
			shopData.add(unit);
		}
		return shopData;
	}
}
