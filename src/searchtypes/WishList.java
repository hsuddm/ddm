package searchtypes;

import java.util.ArrayList;

import product.Product;
import product.ProductTranslator;
import query.Query;

public class WishList extends SearchType {
	String[] ids;

	public WishList(String[] ids) {
		super(null);
		this.ids = ids;
	}

	public ArrayList<Product> getProducts() {
		ArrayList<Product> result = new ArrayList<Product>();
		for (int i = 0; i < ids.length; i++) {
			String myQuery = query + "where id ='" + ids[i] + "';";

			String str = q.send(myQuery);
			if (str == null) {
				return null;
			}

			String tmp = Query.doParse(str);
			if (tmp == null) {
				System.out.println("Error : Category.java");
				return null;
			}

			Product p = ProductTranslator.queryToProduct(tmp);
			result.add(p);

		}

		return result;
	}
}
