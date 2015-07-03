package product;

public class ProductTranslator {
	public static String productToQuery(Product p) {
		String result = "insert into `ddmtest`.`clothes` (id,kind,silhouette,material,hashtag1,hashtag2,hashtag3,hashtag4,name,price,count,content,discount_rate,shop_name,shop_email,reg_date,like_cnt) "
				+ "VALUES (";

		String tmp;
		tmp = p.getid() + ",";
		result += tmp;
		tmp = p.getkind() + ",";
		result += tmp;

		tmp = p.getSilhouette() + ",";
		result += tmp;
		tmp = p.getMaterial() + ",";
		result += tmp;
		tmp = p.getHashtag1() + ",";
		result += tmp;
		tmp = p.getHashtag2() + ",";
		result += tmp;
		tmp = p.getHashtag3() + ",";
		result += tmp;
		tmp = p.getHashtag4() + ",";
		result += tmp;
		tmp = p.getname() + ",";
		result += tmp;
		tmp = p.getprice() + ",";
		result += tmp;
		tmp = p.getcount() + ",";
		result += tmp;
		tmp = p.getcontent() + ",";
		result += tmp;
		tmp = p.getDiscount_rate() + ",";
		result += tmp;
		tmp = p.getShop_name() + ",";
		result += tmp;
		tmp = p.getShop_email() + ",";
		result += tmp;
		tmp = p.getReg_date() + ",";
		result += tmp;
		tmp = p.getLike_cnt() + ");";

		return result;
	}

	public static Product queryToProduct(String str) {
		Product p = new Product();
		String product = str.substring(3, str.length() - 4); // <p>   </p>
		String[] productEntry = product.split(",");

		String id = productEntry[0];
		String kind = productEntry[1];

		String silhouette = productEntry[2];
		String material = productEntry[3];
		String hashtag1 = productEntry[4];
		String hashtag2 = productEntry[5];
		String hashtag3 = productEntry[6];
		String hashtag4 = productEntry[7];

		String name = productEntry[8];
		String price = productEntry[9];
		String count = productEntry[10];
		String content = productEntry[11];
		String discount_rate = productEntry[12];
		String shop_name = productEntry[13];
		String shop_email = productEntry[14];
		String reg_date = productEntry[15];
		String like_cnt = productEntry[16];

		p.setid(Integer.parseInt(id));
		p.setkind(kind);

		p.setSilhouette(silhouette);
		p.setMaterial(material);
		p.setHashtag1(hashtag1);
		p.setHashtag2(hashtag2);
		p.setHashtag3(hashtag3);
		p.setHashtag4(hashtag4);

		p.setname(name);
		p.setprice(Integer.parseInt(price));
		p.setcount(Integer.parseInt(count));
		p.setcontent(content);
		p.setDiscount_rate(Byte.parseByte(discount_rate));
		p.setShop_name(shop_name);
		p.setShop_email(shop_email);
		p.setReg_date(reg_date);
		p.setLike_cnt(Integer.parseInt(like_cnt));

		return p;
	}
}
