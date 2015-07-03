package searchtypes;

import java.util.ArrayList;
import product.Product;
import product.ProductTranslator;
import query.Query;

public class Category extends SearchType {
	public Category(String keyword) {
		super(keyword);
	}

	public ArrayList<Product> getProducts() {
		ArrayList<Product> result = new ArrayList<Product>();

		query += "where kind ='" + keyword + "';";

		String str = q.send(query);
		if (str == null) {
			return null;
		}

		String tmp = Query.doParse(str);
		if (tmp == null) {
			System.out.println("Error : Category.java");
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
