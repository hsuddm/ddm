package product;

public class Product {
	private int id;
	private String kind;
	private String silhouette;
	private String material;
	private String hashtag1;
	private String hashtag2;
	private String hashtag3;
	private String hashtag4;
	private String name;
	private int price;
	private int count;
	private String content;
	private byte discount_rate;
	private String shop_name;
	private String shop_email;
	private String reg_date;
	private int like_cnt;

	public Product() {
		id = 0;
		kind = "";

		silhouette = "";
		material = "";
		hashtag1 = "";
		hashtag2 = "";
		hashtag3 = "";
		hashtag4 = "";
		name = "";
		price = 0;
		count = 0;
		content = "";
		discount_rate = 0;
		shop_name = "";
		shop_email = "";
		reg_date = "";
		like_cnt = 0;
	}

	public Product(int id, String kind, String silhouette, String material, String hashtag1,
			String hashtag2, String hashtag3, String hashtag4, String name, int price, int cnt,
			String content, byte rate, String sname, String semail, String date, int lcnt) {
		this.id = id;
		this.kind = kind;

		this.silhouette = silhouette;
		this.material = material;
		this.hashtag1 = hashtag1;
		this.hashtag2 = hashtag2;
		this.hashtag1 = hashtag3;
		this.hashtag2 = hashtag4;

		this.name = name;
		this.price = price;
		this.count = cnt;
		this.content = content;
		this.discount_rate = rate;
		this.shop_name = sname;
		this.shop_email = semail;
		this.reg_date = date;
		this.like_cnt = lcnt;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getkind() {
		return kind;
	}

	public void setkind(String kind) {
		this.kind = kind;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getprice() {
		return price;
	}

	public void setprice(int price) {
		this.price = price;
	}

	public int getcount() {
		return count;
	}

	public void setcount(int count) {
		this.count = count;
	}

	public String getcontent() {
		return content;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	public byte getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(byte discount_rate) {
		this.discount_rate = discount_rate;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}

	public String getSilhouette() {
		return silhouette;
	}

	public void setSilhouette(String silhouette) {
		this.silhouette = silhouette;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getHashtag1() {
		return hashtag1;
	}

	public void setHashtag1(String hashtag1) {
		this.hashtag1 = hashtag1;
	}

	public String getHashtag2() {
		return hashtag2;
	}

	@Override
	public String toString() {
		return "<p>" + id + "," + kind + "," + silhouette + "," + material + "," + hashtag1 + ","
				+ hashtag2 + "," + hashtag3 + "," + hashtag4 + "," + name + "," + price + ","
				+ count + "," + content + "," + discount_rate + "," + shop_name + "," + shop_email
				+ "," + reg_date + "," + like_cnt + "</p>";
	}

	public void setHashtag2(String hashtag2) {
		this.hashtag2 = hashtag2;
	}

	public String getHashtag3() {
		return hashtag1;
	}

	public void setHashtag3(String hashtag1) {
		this.hashtag1 = hashtag1;
	}

	public String getHashtag4() {
		return hashtag1;
	}

	public void setHashtag4(String hashtag1) {
		this.hashtag1 = hashtag1;
	}

	public String getShop_email() {
		return shop_email;
	}

	public void setShop_email(String shop_email) {
		this.shop_email = shop_email;
	}

}
