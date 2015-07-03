package searchtypes;

import java.util.ArrayList;

import product.Product;
import query.Query;

public abstract class SearchType {
	String keyword;
	Query q;
	static String query;

	public SearchType(String keyword) {
		this.keyword = keyword;
		this.q = new Query();
		query = "select id,kind,silhouette,material,hashtag1,hashtag2,hashtag3,hashtag4,name,price,count,content,discount_rate,shop_name,shop_email,reg_date,like_cnt from clothes ";
	}

	abstract public ArrayList<Product> getProducts();

}
