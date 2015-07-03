package shop;

public class ShopTranslator {
	public static Shop queryToShop(String str) {
		Shop unit = new Shop();
		String shop = str.substring(3, str.length() - 4);
		String shopDetails[] = shop.split(",");

		unit.setShop_name(shopDetails[0]);
		unit.setName(shopDetails[1]);
		unit.setCrnumber(shopDetails[2]);
		unit.setPhone(shopDetails[3]);
		unit.setEmail(shopDetails[4]);
		unit.setPassword(shopDetails[5]);
		unit.setComplex(shopDetails[6]);
		unit.setFloor(shopDetails[7]);
		unit.setUnit(shopDetails[8]);
		unit.setShop_number(Integer.parseInt(shopDetails[9]));
		unit.setIntroduction(shopDetails[10]);
		return unit;
	}
}
