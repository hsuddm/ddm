package searchtypes;

import java.util.ArrayList;
import product.Product;
import product.ProductTranslator;
import query.Query;

public class Keyword extends SearchType {
	public Keyword(String keyword) {
		super(keyword);
	}

	public ArrayList<Product> getProducts() {
		ArrayList<Product> result = new ArrayList<Product>();
		query += "where name like '%" + keyword + "%' or " + "kind like '%" + keyword
				+ "%' or material like  '%" + keyword + "%' or hashtag1 like '%" + keyword
				+ "%' or " + "hashtag2 like '%" + keyword + "%' or " + "hashtag3 like '%" + keyword
				+ "%' or " + "hashtag4 like '%" + keyword + "%' or content like '%" + keyword
				+ "%' or shop_name like '%" + keyword + "%';";

		System.out.println("보낼 쿼리>" + query);
		String str = q.send(query);
		if (str == null) {
			return null;
		}

		String tmp = Query.doParse(str);
		if (tmp == null) {
			System.out.println("Error : Keyword.java");
			return null;
		}

		String products[] = tmp.split("\n");
		Product p;
		for (int i = 0; i < products.length; i++) {
			p = ProductTranslator.queryToProduct(products[i]);
			result.add(p);
		}

		return result;
	}
}
